package aga.android.migrations.sample.cache

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.requery.android.sqlite.DatabaseSource
import io.requery.meta.EntityModel
import io.requery.meta.EntityModelBuilder
import java.io.File

class Database(
    private val context: Context,
    model: EntityModel,
    name: String,
    version: Int
) : DatabaseSource(context, model, name, version) {

    companion object {
        private const val DB_NAME = "test.db"
        const val DB_VERSION = 3

        @JvmStatic
        fun build(context: Context): Database {
            val entityModel = EntityModelBuilder(DB_NAME)
                .addType(Parent.`$TYPE`)
                .addType(Child.`$TYPE`)
                .build()

            return Database(context, entityModel, DB_NAME, DB_VERSION)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        (oldVersion until newVersion).forEach {
            val migrationProceduresRoot = "migrations/v$it"

            context
                .assets
                ?.list(migrationProceduresRoot)
                ?.map {
                    context
                        .assets
                        .open("$migrationProceduresRoot/$it")
                        .bufferedReader()
                }
                ?.forEach { migrationProcedureReader ->
                    migrationProcedureReader.use {
                        db.execSQL(it.readText())
                    }
                }
        }
    }
}