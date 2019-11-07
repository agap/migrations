package aga.android.migrations;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static aga.android.migrations.SchemaHelper.toSchema;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class SchemaHelperTest {

    private static final String PARENT_TABLE_DEFINITION = "create table Parent (" +
    " id integer primary key not null," +
    " name varchar(255) default \'John Doe\'" +
    ");";

    private static final String PARENT_TABLE_DEFINITION_DIFFERENT_ORDER =
    "create table Parent (" +
    " id integer not null primary key," +
    " name varchar(255) default \'John Doe\'" +
    ");";

    private static final String PARENT_TABLE_DEFINITION_CAPS = "CREATE TABLE Parent (" +
    " id INTEGER PRIMARY KEY NOT NULL," +
    " name VARCHAR(255) DEFAULT \'John Doe\'" +
    ");";

    private static final String CHILDREN_TABLE_DEFINITION = "create table Child (" +
    " id integer primary key not null," +
    " name text," +
    " parentId integer not null," +
    " foreign key (parentId) references Parent (id)" +
    ");";

    @Test
    public void testColumnInfoExtraction() {
        final Schema expected = new Schema.SchemaBuilder()
            .setTables(
                singletonList(
                    new Table.Builder()
                        .setName("Parent")
                        .setColumns(
                            Arrays.asList(
                                new Column.Builder()
                                    .setName("id")
                                    .setType(Affinity.INTEGER)
                                    .setIsNullable(false)
                                    .setIsPrimaryKey(true)
                                    .createColumn(),
                                new Column.Builder()
                                    .setName("name")
                                    .setType(Affinity.TEXT)
                                    .setIsNullable(true)
                                    .createColumn()
                            )
                        )
                        .createTable()
                )
            )
            .createSchema();

        final Schema actual = toSchema(singletonList(PARENT_TABLE_DEFINITION));

        assertEquals(expected, actual);
    }

    @Test
    public void testForeignKeyInfoExtraction() {
        final Schema expected = new Schema.SchemaBuilder()
            .setTables(
                singletonList(
                    new Table.Builder()
                        .setName("Child")
                        .setColumns(
                            Arrays.asList(
                                new Column.Builder()
                                    .setName("id")
                                    .setType(Affinity.INTEGER)
                                    .setIsNullable(false)
                                    .setIsPrimaryKey(true)
                                    .createColumn(),
                                new Column.Builder()
                                    .setName("name")
                                    .setType(Affinity.TEXT)
                                    .setIsNullable(true)
                                    .createColumn(),
                                new Column.Builder()
                                    .setName("parentId")
                                    .setType(Affinity.INTEGER)
                                    .setIsNullable(false)
                                    .createColumn()
                            )
                        )
                        .setForeignKeys(
                            Collections.singletonList(
                                new ForeignKey.Builder()
                                    .setKeyName("parentId")
                                    .setReferenceTable("Parent")
                                    .setReferenceField("id")
                                    .createForeignKey()
                            )
                        )
                        .createTable()
                )
            )
            .createSchema();

        final Schema actual = toSchema(singletonList(CHILDREN_TABLE_DEFINITION));

        assertEquals(expected, actual);
    }

    @Test
    public void testLowerAndUpperCaseKeyWordsDoNoAffectSchema() {
        final Schema lowerCaseSchema = toSchema(singletonList(PARENT_TABLE_DEFINITION));
        final Schema upperCaseSchema = toSchema(singletonList(PARENT_TABLE_DEFINITION_CAPS));

        assertEquals(lowerCaseSchema, upperCaseSchema);
    }

    @Test
    public void testKeywordOrderDoesNotAffectSchema() {
        final Schema schema1 = toSchema(singletonList(PARENT_TABLE_DEFINITION));
        final Schema schema2 = toSchema(singletonList(PARENT_TABLE_DEFINITION_DIFFERENT_ORDER));

        assertEquals(schema1, schema2);
    }
}
