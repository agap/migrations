package aga.android.migrations

import android.database.sqlite.SQLiteDatabase

class AndroidDatabaseSchema {

    fun extract(database: SQLiteDatabase): Schema {
        return database.rawQuery("select * from sqlite_master where type='table'", null).use {
            generateSequence { if (it.moveToNext()) it else null }
                .map {
                    it.getString(it.getColumnIndex("sql"))
                }
                .run {
                    SchemaHelper.toSchema(toList())
                }
        }
    }

    fun cleanup(database: SQLiteDatabase) {
        database.rawQuery("select name from sqlite_master where type='table'", null).use {
            while (it.moveToNext()) {
                val tableName = it.getString(it.getColumnIndex("name"))

                database.execSQL("drop table if exists $tableName")
            }
        }
    }
}