package com.example.data.network

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.ByteArrayOutputStream
import java.io.IOException
import kotlin.coroutines.resume

/** Bounded response body, real call cancellation, and response ownership on every path. */
internal suspend fun OkHttpClient.awaitText(request: Request, maxBytes: Int = 2 * 1024 * 1024): String? =
    suspendCancellableCoroutine { continuation ->
        val call = newCall(request)
        continuation.invokeOnCancellation { call.cancel() }
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, error: IOException) { continuation.resume(null) }
            override fun onResponse(call: Call, response: Response) {
                val text = try {
                    response.use {
                        val body = it.body
                        if (!it.isSuccessful || body == null || body.contentLength() > maxBytes) null
                        else body.byteStream().use streamUse@{ stream ->
                            val buffer = ByteArray(8192)
                            val output = ByteArrayOutputStream()
                            while (true) {
                                val read = stream.read(buffer, 0, minOf(buffer.size, maxBytes + 1 - output.size()))
                                if (read < 0) break
                                output.write(buffer,0,read)
                                if (output.size() > maxBytes) return@streamUse null
                            }
                            output.toByteArray().toString(body.contentType()?.charset(Charsets.UTF_8) ?: Charsets.UTF_8)
                        }
                    }
                } catch (_: IOException) { null }
                continuation.resume(text)
            }
        })
    }
