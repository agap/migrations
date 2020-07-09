package aga.android.migrations.sample

import aga.android.migrations.AndroidDatabaseSchema
import aga.android.migrations.sample.cache.Database.Companion.DB_VERSION
import aga.android.migrations.sample.cache.Database.Companion.build
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.requery.android.sqlite.DatabaseSource
import io.requery.sql.SchemaModifier
import io.requery.sql.TableCreationMode
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class DatabaseMigrationTest {
    private val schemaExtractor = AndroidDatabaseSchema()

    @Test
    fun testDbMigration() {

        // given
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val testContext = InstrumentationRegistry.getInstrumentation().context
        val database: DatabaseSource = build(appContext)

        SchemaModifier(
            database.configuration
        ).createTables(TableCreationMode.DROP_CREATE)

        // when
        val schemaForNewUsers = schemaExtractor.extract(database.readableDatabase)

        schemaExtractor.cleanup(database.writableDatabase)

        recreateTheInitialDbState(testContext, database.writableDatabase)

        database.onUpgrade(database.writableDatabase, 1, DB_VERSION)

        val schemaForMigratedUsers = schemaExtractor.extract(database.readableDatabase)

        // then
        assertEquals(
            schemaForMigratedUsers.diffWith(schemaForNewUsers),
            schemaForNewUsers,
            schemaForMigratedUsers
        )
    }

    private fun recreateTheInitialDbState(context: Context, database: SQLiteDatabase) {
        val initialDbStatePath = "initial-db-state"

        context
            .assets
            ?.list(initialDbStatePath)
            ?.map {
                context.resources.assets.open("$initialDbStatePath/$it").bufferedReader()
            }
            ?.forEach { sqlStatementReader ->
                sqlStatementReader.use {
                    database.execSQL(it.readText())
                }
            }
    }
}