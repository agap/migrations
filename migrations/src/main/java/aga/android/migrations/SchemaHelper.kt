package aga.android.migrations

import aga.android.migrations.antlr.SQLiteLexer
import aga.android.migrations.antlr.SQLiteParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

/**
 * Created on 05.11.19
 * @author artem
 */
object SchemaHelper {

    fun toSchema(masterTableContent: List<String>): Schema {

        val tables: List<Table> = masterTableContent.mapNotNull { content ->
            try {
                val inputStream: InputStream = ByteArrayInputStream(content.toByteArray())
                val lexer: Lexer = SQLiteLexer(CharStreams.fromStream(inputStream))
                val tokenStream: TokenStream = CommonTokenStream(lexer)
                val parser = SQLiteParser(tokenStream)
                val treeWalker = ParseTreeWalker()
                val listener = SchemaListener()

                treeWalker.walk(listener, parser.create_table_stmt())

                listener.table
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        return Schema(tables)
    }
}