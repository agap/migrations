package aga.android.migrations;

import androidx.annotation.Nullable;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 06.11.19
 * @author artem
 */
final class SchemaListener extends SQLiteBaseListener {

    private Table.Builder tableBuilder;

    private Column.Builder columnBuilder;

    private ForeignKey.Builder foreignKeyBuilder;

    private final List<Column> columns = new ArrayList<>();
    private final List<ForeignKey> foreignKeys = new ArrayList<>();

    @Nullable
    Table getTable() {
        return tableBuilder.createTable();
    }

    @Override
    public void enterCreate_table_stmt(SQLiteParser.Create_table_stmtContext ctx) {
        tableBuilder = new Table.Builder();
    }

    @Override
    public void exitCreate_table_stmt(SQLiteParser.Create_table_stmtContext ctx) {
        tableBuilder.setColumns(columns);
        tableBuilder.setForeignKeys(foreignKeys);
    }

    @Override
    public void enterColumn_def(SQLiteParser.Column_defContext ctx) {
        columnBuilder = new Column.Builder();
    }

    @Override
    public void exitColumn_def(SQLiteParser.Column_defContext ctx) {
        columns.add(columnBuilder.createColumn());
    }

    @Override
    public void enterType_name(SQLiteParser.Type_nameContext ctx) {
        columnBuilder.setType(Affinity.of(ctx.getText()));
    }

    @Override
    public void enterColumn_constraint(SQLiteParser.Column_constraintContext ctx) {
        if (ctx.K_NULL() != null) {
            if (ctx.K_NOT() != null) {
                columnBuilder.setIsNullable(false);
            } else {
                columnBuilder.setIsNullable(true);
            }
        } else if (ctx.K_PRIMARY() != null && ctx.K_KEY() != null) {
            columnBuilder.setIsPrimaryKey(true);
        } else if (ctx.K_DEFAULT() != null) {
            columnBuilder.setDefaultValue(ctx.children.get(1).getText());
        }
    }

    @Override
    public void enterForeign_key_clause(SQLiteParser.Foreign_key_clauseContext ctx) {

        final Iterator<ParseTree> iterator = ctx.children.iterator();

        while (iterator.hasNext()) {
            final ParseTree parseTree = iterator.next();

            if (parseTree.getText().equalsIgnoreCase("references")) {
                final ParseTree table = iterator.next();

                foreignKeyBuilder.setReferenceTable(table.getText());
            } else if (parseTree.getText().equals("(")) {
                final ParseTree referenceField = iterator.next();

                foreignKeyBuilder.setReferenceField(referenceField.getText());
            } else if (parseTree.getText().equalsIgnoreCase("update")) {
                foreignKeyBuilder.setOnUpdate(extractForeignKeyAction(iterator));
            } else if (parseTree.getText().equalsIgnoreCase("delete")) {
                foreignKeyBuilder.setOnDelete(extractForeignKeyAction(iterator));
            }
        }
    }

    private ForeignKeyAction extractForeignKeyAction(final Iterator<ParseTree> iterator) {
        final String action = iterator.next().getText();

        final ForeignKeyAction keyAction;

        if (action.equalsIgnoreCase("no")) {
            keyAction = ForeignKeyAction.NO_ACTION;
        } else if (action.equalsIgnoreCase("restrict")) {
            keyAction = ForeignKeyAction.RESTRICT;
        } else if (action.equalsIgnoreCase("cascade")) {
            keyAction = ForeignKeyAction.CASCADE;
        } else {
            final String additionalField = iterator.next().getText();

            if (additionalField.equalsIgnoreCase("null")) {
                keyAction = ForeignKeyAction.SET_NULL;
            } else {
                keyAction = ForeignKeyAction.SET_DEFAULT;
            }
        }

        return keyAction;
    }

    @Override
    public void exitForeign_key_clause(SQLiteParser.Foreign_key_clauseContext ctx) {
        foreignKeys.add(foreignKeyBuilder.createForeignKey());
    }

    @Override
    public void enterTable_constraint(SQLiteParser.Table_constraintContext ctx) {
        foreignKeyBuilder = new ForeignKey.Builder();
        foreignKeyBuilder.setKeyName(ctx.column_name(0).getText());
    }

    @Override
    public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
        tableBuilder.setName(ctx.getText());
    }

    @Override
    public void enterColumn_name(SQLiteParser.Column_nameContext ctx) {
        columnBuilder.setName(ctx.getText());
    }
}
