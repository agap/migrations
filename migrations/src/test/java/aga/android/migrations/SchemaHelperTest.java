package aga.android.migrations;

import org.junit.Test;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class SchemaHelperTest {

    private static final String USER_TABLE_DEFINITION = "create table User (" +
    " id integer primary key not null," +
    " name text default `John Doe`" +
    ");";

    @Test
    public void testTableSchemaExtraction() {
        final Schema expected = new Schema.SchemaBuilder()
            .setTables(
                singletonList(
                    new Table.Builder()
                        .setName("User")
                        .setColumns(
                            Arrays.asList(
                                new Column.Builder()
                                    .setName("id")
                                    .createColumn(),
                                new Column.Builder()
                                    .setName("name")
                                    .createColumn()
                            )
                        )
                        .createTable()
                )
            )
            .createSchema();

        final Schema actual = SchemaHelper.toSchema(singletonList(USER_TABLE_DEFINITION));

        assertEquals(expected, actual);
    }
}
