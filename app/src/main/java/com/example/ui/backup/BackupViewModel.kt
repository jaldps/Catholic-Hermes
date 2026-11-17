package com.example.ui.backup

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.backup.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class BackupPage { HOME, EXPORT, IMPORT, SUMMARY, SUCCESS }
data class BackupUiState(
    val page: BackupPage = BackupPage.HOME,
    val busy: Boolean = false,
    val error: BackupError? = null,
    val summary: BackupSummary? = null,
    val mode: RestoreMode = RestoreMode.MERGE,
    val exportReady: Boolean = false,
    val restored: Boolean = false
)

/** Passwords are never in SavedStateHandle, preferences, logs or files. Only encrypted bytes survive rotation. */
class BackupViewModel(private val service: BackupService, private val resolver: ContentResolver): ViewModel() {
    private val _state=MutableStateFlow(BackupUiState())
    val state=_state.asStateFlow()
    private var encrypted: ByteArray?=null
    private var opened: PreparedBackup?=null
    private var importUri: Uri?=null
    private var exportPickerPending=false
    fun startExport() { if(!_state.value.busy) { clearPrivate();_state.value=BackupUiState(page=BackupPage.EXPORT) } }
    fun imported(uri: Uri?) {
        if(uri==null) return
        clearPrivate();importUri=uri;_state.value=BackupUiState(page=BackupPage.IMPORT)
    }
    fun pickerFailed() { clearPrivate();error(java.io.IOException("Document picker unavailable"),BackupPage.HOME) }
    fun selectMode(mode: RestoreMode) { if(!_state.value.busy) _state.value=_state.value.copy(mode=mode) }
    fun reset() { if(!_state.value.busy) { clearPrivate();_state.value=BackupUiState() } }
    private fun clearPrivate() { encrypted?.fill(0);encrypted=null;opened=null;importUri=null;exportPickerPending=false }
    private fun error(e: Exception, page: BackupPage) {
        if(e is CancellationException) throw e
        _state.value=_state.value.copy(page=page,busy=false,error=(e as? BackupException)?.reason ?: BackupError.STORAGE,exportReady=false)
    }
    fun export(password: CharArray) {
        if(_state.value.busy) {password.fill('\u0000');return}
        _state.value=_state.value.copy(busy=true,error=null)
        viewModelScope.launch {
            try {
                encrypted=service.export(password)
                _state.value=_state.value.copy(busy=false,exportReady=true)
            } catch(e:Exception) { error(e,BackupPage.EXPORT) }
        }.invokeOnCompletion {password.fill('\u0000')}
    }
    /** Mark before launching SAF, so rotation cannot launch a second chooser. */
    fun claimExportPicker(): Boolean {
        if(encrypted==null||exportPickerPending)return false
        exportPickerPending=true
        return true
    }
    fun save(uri: Uri?) {
        val bytes=encrypted ?: return
        if(uri==null) { clearPrivate();_state.value=BackupUiState();return }
        _state.value=_state.value.copy(busy=true,exportReady=false,error=null)
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val stream=resolver.openOutputStream(uri,"wt") ?: throw java.io.IOException("Unavailable destination")
                    stream.use { it.write(bytes);it.flush() }
                }
                clearPrivate();_state.value=BackupUiState(page=BackupPage.SUCCESS)
            } catch(e:Exception) {clearPrivate();error(e,BackupPage.EXPORT)}
        }
    }
    fun preview(password: CharArray) {
        val uri=importUri
        if(uri==null||_state.value.busy) {password.fill('\u0000');return}
        _state.value=_state.value.copy(busy=true,error=null)
        viewModelScope.launch {
            var bytes:ByteArray?=null
            try {
                bytes=withContext(Dispatchers.IO) {
                    resolver.openInputStream(uri)?.use(BackupCodec::read) ?: throw java.io.IOException("Unavailable source")
                }
                opened=service.preview(bytes,password)
                importUri=null
                _state.value=BackupUiState(page=BackupPage.SUMMARY,summary=opened!!.summary)
            } catch(e:Exception) {error(e,BackupPage.IMPORT)}
            finally {bytes?.fill(0)}
        }.invokeOnCompletion {password.fill('\u0000')}
    }
    fun apply() {
        val prepared=opened ?: return
        if(_state.value.busy)return
        val mode=_state.value.mode
        _state.value=_state.value.copy(busy=true,error=null)
        viewModelScope.launch {
            try {
                service.apply(prepared,mode)
                clearPrivate();_state.value=BackupUiState(page=BackupPage.SUCCESS,restored=true)
            } catch(e:Exception) {error(e,BackupPage.SUMMARY)}
        }
    }
    override fun onCleared() { clearPrivate() }
}
