package aga.android.migrations

import aga.android.migrations.SchemaHelper.toSchema
import org.junit.Assert.assertEquals
import org.junit.Test

class SchemaHelperTest {
    @Test
    fun testColumnInfoExtraction() {
        val expected = Schema(
            setOf(
                Table(
                    name = "Parent",
                    columns = (
                        setOf(
                            Column.Builder()
                                .setName("id")
                                .setType(Affinity.INTEGER)
                                .setIsNullable(false)
                                .setIsPrimaryKey(true)
                                .build(),
                            Column.Builder()
                                .setName("name")
                                .setDefaultValue("\'John Doe\'")
                                .setType(Affinity.TEXT)
                                .setIsNullable(true)
                                .build()
                        )
                    )
                )
            )
        )

        val actual = toSchema(listOf(PARENT_TABLE_DEFINITION))
        assertEquals(expected, actual)
    }

    @Test
    fun testForeignKeyInfoExtraction() {
        val expected = Schema(
            tables = setOf(
                Table(
                    name = "Child",
                    columns = setOf(
                        Column.Builder()
                            .setName("id")
                            .setType(Affinity.INTEGER)
                            .setIsNullable(false)
                            .setIsPrimaryKey(true)
                            .build(),
                        Column.Builder()
                            .setName("name")
                            .setType(Affinity.TEXT)
                            .setIsNullable(true)
                            .build(),
                        Column.Builder()
                            .setName("parentId")
                            .setType(Affinity.INTEGER)
                            .setIsNullable(false)
                            .build()
                        ),
                    foreignKeys = setOf(
                        ForeignKey.Builder()
                            .setKeyName("parentId")
                            .setReferenceTable("Parent")
                            .setReferenceField("id")
                            .setOnDelete(ForeignKeyAction.NO_ACTION)
                            .setOnUpdate(ForeignKeyAction.CASCADE)
                            .build()
                    )
                )
            )
        )

        val actual = toSchema(listOf(CHILDREN_TABLE_DEFINITION))
        assertEquals(expected, actual)
    }

    @Test
    fun testLowerAndUpperCaseKeyWordsDoNoAffectSchema() {
        val lowerCaseSchema = toSchema(listOf(PARENT_TABLE_DEFINITION))
        val upperCaseSchema = toSchema(listOf(PARENT_TABLE_DEFINITION_CAPS))
        assertEquals(lowerCaseSchema, upperCaseSchema)
    }

    @Test
    fun testKeywordOrderDoesNotAffectSchema() {
        val schema1 = toSchema(listOf(PARENT_TABLE_DEFINITION))
        val schema2 = toSchema(listOf(PARENT_TABLE_DEFINITION_DIFFERENT_ORDER))
        assertEquals(schema1, schema2)
    }

    companion object {

        private const val PARENT_TABLE_DEFINITION = "create table Parent (" +
            " id integer primary key not null," +
            " name varchar(255) default \'John Doe\'" +
            ");"

        private const val PARENT_TABLE_DEFINITION_DIFFERENT_ORDER = "create table Parent (" +
            " id integer not null primary key," +
            " name varchar(255) default \'John Doe\'" +
            ");"

        private const val PARENT_TABLE_DEFINITION_CAPS = "CREATE TABLE Parent (" +
            " id INTEGER PRIMARY KEY NOT NULL," +
            " name VARCHAR(255) DEFAULT \'John Doe\'" +
            ");"

        private const val CHILDREN_TABLE_DEFINITION = "create table Child (" +
            " id integer primary key not null," +
            " name text," +
            " parentId integer not null," +
            " foreign key (parentId) references Parent (id) on update cascade on delete no action" +
            ");"
    }
}