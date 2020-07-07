package aga.android.migrations

import aga.android.migrations.antlr.SQLiteBaseListener
import aga.android.migrations.antlr.SQLiteParser.Column_constraintContext
import aga.android.migrations.antlr.SQLiteParser.Column_defContext
import aga.android.migrations.antlr.SQLiteParser.Column_nameContext
import aga.android.migrations.antlr.SQLiteParser.Create_table_stmtContext
import aga.android.migrations.antlr.SQLiteParser.Foreign_key_clauseContext
import aga.android.migrations.antlr.SQLiteParser.Table_constraintContext
import aga.android.migrations.antlr.SQLiteParser.Table_nameContext
import aga.android.migrations.antlr.SQLiteParser.Type_nameContext
import org.antlr.v4.runtime.tree.ParseTree

/**
 * Created on 06.11.19
 * @author artem
 */
internal class SchemaListener : SQLiteBaseListener() {

    private lateinit var tableName: String

    private lateinit var columnBuilder: Column.Builder
    private lateinit var foreignKeyBuilder: ForeignKey.Builder

    private lateinit var columns: MutableList<Column>
    private lateinit var foreignKeys: MutableList<ForeignKey>

    val table: Table
        get() = Table(tableName, columns = columns, foreignKeys = foreignKeys)

    override fun enterCreate_table_stmt(ctx: Create_table_stmtContext) {
        columns = ArrayList()
        foreignKeys = ArrayList()
    }

    override fun enterColumn_def(ctx: Column_defContext) {
        columnBuilder = Column.Builder()
    }

    override fun exitColumn_def(ctx: Column_defContext) {
        columns.add(columnBuilder.build())
    }

    override fun enterType_name(ctx: Type_nameContext) {
        columnBuilder.setType(Affinity.of(ctx.text))
    }

    override fun enterColumn_constraint(ctx: Column_constraintContext) {
        if (ctx.K_NULL() != null) {
            if (ctx.K_NOT() != null) {
                columnBuilder.setIsNullable(false)
            } else {
                columnBuilder.setIsNullable(true)
            }
        } else if (ctx.K_PRIMARY() != null && ctx.K_KEY() != null) {
            columnBuilder.setIsPrimaryKey(true)
        } else if (ctx.K_DEFAULT() != null) {
            columnBuilder.setDefaultValue(ctx.children[1].text)
        }
    }

    override fun enterForeign_key_clause(ctx: Foreign_key_clauseContext) {
        val iterator: Iterator<ParseTree> = ctx.children.iterator()

        while (iterator.hasNext()) {
            val parseTree = iterator.next()

            when {
                parseTree.text.equals("references", ignoreCase = true) -> {
                    val table = iterator.next()
                    foreignKeyBuilder.setReferenceTable(table.text)
                }

                parseTree.text == "(" -> {
                    val referenceField = iterator.next()
                    foreignKeyBuilder.setReferenceField(referenceField.text)
                }

                parseTree.text.equals("update", ignoreCase = true) -> {
                    foreignKeyBuilder.setOnUpdate(extractForeignKeyAction(iterator))
                }

                parseTree.text.equals("delete", ignoreCase = true) -> {
                    foreignKeyBuilder.setOnDelete(extractForeignKeyAction(iterator))
                }
            }
        }
    }

    private fun extractForeignKeyAction(iterator: Iterator<ParseTree>): ForeignKeyAction {
        val action = iterator.next().text

        return if (action.equals("no", ignoreCase = true)) {
            ForeignKeyAction.NO_ACTION
        } else if (action.equals("restrict", ignoreCase = true)) {
            ForeignKeyAction.RESTRICT
        } else if (action.equals("cascade", ignoreCase = true)) {
            ForeignKeyAction.CASCADE
        } else {
            val additionalField = iterator.next().text

            if (additionalField.equals("null", ignoreCase = true)) {
                ForeignKeyAction.SET_NULL
            } else {
                ForeignKeyAction.SET_DEFAULT
            }
        }
    }

    override fun exitForeign_key_clause(ctx: Foreign_key_clauseContext) {
        foreignKeys.add(foreignKeyBuilder.build())
    }

    override fun enterTable_constraint(ctx: Table_constraintContext) {
        foreignKeyBuilder = ForeignKey.Builder()
        foreignKeyBuilder.setKeyName(ctx.column_name(0).text)
    }

    override fun enterTable_name(ctx: Table_nameContext) {
        tableName = ctx.text
    }

    override fun enterColumn_name(ctx: Column_nameContext) {
        columnBuilder.setName(ctx.text)
    }
}