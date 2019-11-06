package aga.android.migrations;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 05.11.19
 * @author artem
 */
public final class SchemaHelper {

    private SchemaHelper() {

    }

    public static Schema toSchema(final List<String> masterTableContent) {
        final List<Table> tables = new ArrayList<>();

        for (String content: masterTableContent) {
            try {
                final InputStream inputStream = new ByteArrayInputStream(content.getBytes());
                final Lexer lexer = new SQLiteLexer(CharStreams.fromStream(inputStream));
                final TokenStream tokenStream = new CommonTokenStream(lexer);
                final SQLiteParser parser = new SQLiteParser(tokenStream);
                final ParseTreeWalker treeWalker = new ParseTreeWalker();
                final SchemaListener listener = new SchemaListener();

                treeWalker.walk(listener, parser.create_table_stmt());

                final Table table = listener.getTable();

                if (table != null) {
                    tables.add(table);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Schema.SchemaBuilder().setTables(tables).createSchema();
    }
}
