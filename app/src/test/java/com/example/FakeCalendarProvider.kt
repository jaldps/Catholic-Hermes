package com.example

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.pm.ProviderInfo
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.provider.CalendarContract
import org.robolectric.shadows.ShadowContentResolver

/** A contract-level SQLite provider: selections/projections are executed, not guessed by tests. */
class FakeCalendarProvider:ContentProvider() {
    lateinit var database:SQLiteDatabase
    override fun onCreate():Boolean {
        database=SQLiteDatabase.create(null)
        database.execSQL("CREATE TABLE calendars (_id INTEGER PRIMARY KEY, calendar_displayName TEXT, account_name TEXT, calendar_access_level INTEGER)")
        database.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT, calendar_id INTEGER, deleted INTEGER NOT NULL DEFAULT 0, title TEXT, description TEXT, dtstart INTEGER, duration TEXT, eventTimezone TEXT, rrule TEXT, customAppPackage TEXT, customAppUri TEXT)")
        database.execSQL("CREATE TABLE reminders (_id INTEGER PRIMARY KEY AUTOINCREMENT, event_id INTEGER, minutes INTEGER, method INTEGER)")
        return true
    }
    private fun where(uri:Uri,selection:String?,args:Array<out String>?):Pair<String?,Array<String>?> {
        val id=uri.pathSegments.getOrNull(1)?.toLongOrNull()
        if(id==null)return selection to args?.map{it}.orEmpty().toTypedArray()
        val sql=if(selection.isNullOrBlank())"_id = ?" else "($selection) AND _id = ?"
        return sql to (args?.map{it}.orEmpty()+id.toString()).toTypedArray()
    }
    override fun query(uri:Uri,projection:Array<out String>?,selection:String?,selectionArgs:Array<out String>?,sortOrder:String?):Cursor {
        val (sql,args)=where(uri,selection,selectionArgs)
        return database.query(uri.pathSegments.first(),projection,sql,args,null,null,sortOrder)
    }
    override fun insert(uri:Uri,values:ContentValues?):Uri = ContentUris.withAppendedId(uri,database.insertOrThrow(uri.pathSegments.first(),null,requireNotNull(values)))
    override fun update(uri:Uri,values:ContentValues?,selection:String?,selectionArgs:Array<out String>?):Int {
        val (sql,args)=where(uri,selection,selectionArgs)
        return database.update(uri.pathSegments.first(),requireNotNull(values),sql,args)
    }
    override fun delete(uri:Uri,selection:String?,selectionArgs:Array<out String>?):Int {
        val (sql,args)=where(uri,selection,selectionArgs)
        return database.delete(uri.pathSegments.first(),sql,args)
    }
    override fun getType(uri:Uri):String="vnd.android.cursor.dir/calendar"
    fun count(table:String)=database.rawQuery("SELECT COUNT(*) FROM $table",null).use {it.moveToFirst();it.getInt(0)}
    companion object {
        fun install(context:Context):FakeCalendarProvider = FakeCalendarProvider().also { provider ->
            provider.attachInfo(context,ProviderInfo().apply{authority=CalendarContract.AUTHORITY})
            ShadowContentResolver.registerProviderInternal(CalendarContract.AUTHORITY,provider)
            provider.database.execSQL("INSERT INTO calendars VALUES (1,'Work','work@example.test',700),(2,'Personal','personal@example.test',500),(3,'Read only','read@example.test',200)")
        }
    }
}
