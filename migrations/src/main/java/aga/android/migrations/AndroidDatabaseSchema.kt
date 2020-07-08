package aga.android.migrations

import android.database.sqlite.SQLiteDatabase

class AndroidDatabaseSchema {

    private companion object {
        const val TABLE_SELECTION_QUERY =
            "select " +
                "name, sql " +
            "from " +
                "sqlite_master " +
            "where " +
                "type='table' and " +
                "name not like '%sqlite%' and " +
                "name <> 'android_metadata'"
    }

    fun extract(database: SQLiteDatabase): Schema {
        return database.rawQuery(TABLE_SELECTION_QUERY, null).use {
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
        database.rawQuery(TABLE_SELECTION_QUERY, null).use {
            while (it.moveToNext()) {
                val tableName = it.getString(it.getColumnIndex("name"))

                database.execSQL("drop table if exists $tableName")
            }
        }
    }
}