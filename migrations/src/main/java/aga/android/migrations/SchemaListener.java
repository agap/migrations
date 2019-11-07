package aga.android.migrations;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created on 06.11.19
 * @author artem
 */
// todo inherit from SQLiteBaseListener instead
final class SchemaListener implements SQLiteListener {

    private Table.Builder tableBuilder;

    private Column.Builder columnBuilder;

    private final List<Column> columns = new ArrayList<>();

    @Nullable
    Table getTable() {
        return tableBuilder.createTable();
    }

    @Override
    public void enterParse(SQLiteParser.ParseContext ctx) {

    }

    @Override
    public void exitParse(SQLiteParser.ParseContext ctx) {

    }

    @Override
    public void enterError(SQLiteParser.ErrorContext ctx) {

    }

    @Override
    public void exitError(SQLiteParser.ErrorContext ctx) {

    }

    @Override
    public void enterSql_stmt_list(SQLiteParser.Sql_stmt_listContext ctx) {

    }

    @Override
    public void exitSql_stmt_list(SQLiteParser.Sql_stmt_listContext ctx) {

    }

    @Override
    public void enterSql_stmt(SQLiteParser.Sql_stmtContext ctx) {

    }

    @Override
    public void exitSql_stmt(SQLiteParser.Sql_stmtContext ctx) {

    }

    @Override
    public void enterAlter_table_stmt(SQLiteParser.Alter_table_stmtContext ctx) {

    }

    @Override
    public void exitAlter_table_stmt(SQLiteParser.Alter_table_stmtContext ctx) {

    }

    @Override
    public void enterAnalyze_stmt(SQLiteParser.Analyze_stmtContext ctx) {

    }

    @Override
    public void exitAnalyze_stmt(SQLiteParser.Analyze_stmtContext ctx) {

    }

    @Override
    public void enterAttach_stmt(SQLiteParser.Attach_stmtContext ctx) {

    }

    @Override
    public void exitAttach_stmt(SQLiteParser.Attach_stmtContext ctx) {

    }

    @Override
    public void enterBegin_stmt(SQLiteParser.Begin_stmtContext ctx) {

    }

    @Override
    public void exitBegin_stmt(SQLiteParser.Begin_stmtContext ctx) {

    }

    @Override
    public void enterCommit_stmt(SQLiteParser.Commit_stmtContext ctx) {

    }

    @Override
    public void exitCommit_stmt(SQLiteParser.Commit_stmtContext ctx) {

    }

    @Override
    public void enterCompound_select_stmt(SQLiteParser.Compound_select_stmtContext ctx) {

    }

    @Override
    public void exitCompound_select_stmt(SQLiteParser.Compound_select_stmtContext ctx) {

    }

    @Override
    public void enterCreate_index_stmt(SQLiteParser.Create_index_stmtContext ctx) {

    }

    @Override
    public void exitCreate_index_stmt(SQLiteParser.Create_index_stmtContext ctx) {

    }

    @Override
    public void enterCreate_table_stmt(SQLiteParser.Create_table_stmtContext ctx) {
        tableBuilder = new Table.Builder();
        System.out.println("Enter create");
    }

    @Override
    public void exitCreate_table_stmt(SQLiteParser.Create_table_stmtContext ctx) {
        tableBuilder.setColumns(columns);
        System.out.println("Exit create");
    }

    @Override
    public void enterCreate_trigger_stmt(SQLiteParser.Create_trigger_stmtContext ctx) {

    }

    @Override
    public void exitCreate_trigger_stmt(SQLiteParser.Create_trigger_stmtContext ctx) {

    }

    @Override
    public void enterCreate_view_stmt(SQLiteParser.Create_view_stmtContext ctx) {

    }

    @Override
    public void exitCreate_view_stmt(SQLiteParser.Create_view_stmtContext ctx) {

    }

    @Override
    public void enterCreate_virtual_table_stmt(SQLiteParser.Create_virtual_table_stmtContext ctx) {

    }

    @Override
    public void exitCreate_virtual_table_stmt(SQLiteParser.Create_virtual_table_stmtContext ctx) {

    }

    @Override
    public void enterDelete_stmt(SQLiteParser.Delete_stmtContext ctx) {

    }

    @Override
    public void exitDelete_stmt(SQLiteParser.Delete_stmtContext ctx) {

    }

    @Override
    public void enterDelete_stmt_limited(SQLiteParser.Delete_stmt_limitedContext ctx) {

    }

    @Override
    public void exitDelete_stmt_limited(SQLiteParser.Delete_stmt_limitedContext ctx) {

    }

    @Override
    public void enterDetach_stmt(SQLiteParser.Detach_stmtContext ctx) {

    }

    @Override
    public void exitDetach_stmt(SQLiteParser.Detach_stmtContext ctx) {

    }

    @Override
    public void enterDrop_index_stmt(SQLiteParser.Drop_index_stmtContext ctx) {

    }

    @Override
    public void exitDrop_index_stmt(SQLiteParser.Drop_index_stmtContext ctx) {

    }

    @Override
    public void enterDrop_table_stmt(SQLiteParser.Drop_table_stmtContext ctx) {

    }

    @Override
    public void exitDrop_table_stmt(SQLiteParser.Drop_table_stmtContext ctx) {

    }

    @Override
    public void enterDrop_trigger_stmt(SQLiteParser.Drop_trigger_stmtContext ctx) {

    }

    @Override
    public void exitDrop_trigger_stmt(SQLiteParser.Drop_trigger_stmtContext ctx) {

    }

    @Override
    public void enterDrop_view_stmt(SQLiteParser.Drop_view_stmtContext ctx) {

    }

    @Override
    public void exitDrop_view_stmt(SQLiteParser.Drop_view_stmtContext ctx) {

    }

    @Override
    public void enterFactored_select_stmt(SQLiteParser.Factored_select_stmtContext ctx) {

    }

    @Override
    public void exitFactored_select_stmt(SQLiteParser.Factored_select_stmtContext ctx) {

    }

    @Override
    public void enterInsert_stmt(SQLiteParser.Insert_stmtContext ctx) {

    }

    @Override
    public void exitInsert_stmt(SQLiteParser.Insert_stmtContext ctx) {

    }

    @Override
    public void enterPragma_stmt(SQLiteParser.Pragma_stmtContext ctx) {

    }

    @Override
    public void exitPragma_stmt(SQLiteParser.Pragma_stmtContext ctx) {

    }

    @Override
    public void enterReindex_stmt(SQLiteParser.Reindex_stmtContext ctx) {

    }

    @Override
    public void exitReindex_stmt(SQLiteParser.Reindex_stmtContext ctx) {

    }

    @Override
    public void enterRelease_stmt(SQLiteParser.Release_stmtContext ctx) {

    }

    @Override
    public void exitRelease_stmt(SQLiteParser.Release_stmtContext ctx) {

    }

    @Override
    public void enterRollback_stmt(SQLiteParser.Rollback_stmtContext ctx) {

    }

    @Override
    public void exitRollback_stmt(SQLiteParser.Rollback_stmtContext ctx) {

    }

    @Override
    public void enterSavepoint_stmt(SQLiteParser.Savepoint_stmtContext ctx) {

    }

    @Override
    public void exitSavepoint_stmt(SQLiteParser.Savepoint_stmtContext ctx) {

    }

    @Override
    public void enterSimple_select_stmt(SQLiteParser.Simple_select_stmtContext ctx) {

    }

    @Override
    public void exitSimple_select_stmt(SQLiteParser.Simple_select_stmtContext ctx) {

    }

    @Override
    public void enterSelect_stmt(SQLiteParser.Select_stmtContext ctx) {

    }

    @Override
    public void exitSelect_stmt(SQLiteParser.Select_stmtContext ctx) {

    }

    @Override
    public void enterSelect_or_values(SQLiteParser.Select_or_valuesContext ctx) {

    }

    @Override
    public void exitSelect_or_values(SQLiteParser.Select_or_valuesContext ctx) {

    }

    @Override
    public void enterUpdate_stmt(SQLiteParser.Update_stmtContext ctx) {

    }

    @Override
    public void exitUpdate_stmt(SQLiteParser.Update_stmtContext ctx) {

    }

    @Override
    public void enterUpdate_stmt_limited(SQLiteParser.Update_stmt_limitedContext ctx) {

    }

    @Override
    public void exitUpdate_stmt_limited(SQLiteParser.Update_stmt_limitedContext ctx) {

    }

    @Override
    public void enterVacuum_stmt(SQLiteParser.Vacuum_stmtContext ctx) {

    }

    @Override
    public void exitVacuum_stmt(SQLiteParser.Vacuum_stmtContext ctx) {

    }

    @Override
    public void enterColumn_def(SQLiteParser.Column_defContext ctx) {
        columnBuilder = new Column.Builder();
        System.out.println("Enter column def");
    }

    @Override
    public void exitColumn_def(SQLiteParser.Column_defContext ctx) {
        columns.add(columnBuilder.createColumn());
        System.out.println("Exit column def");
    }

    @Override
    public void enterType_name(SQLiteParser.Type_nameContext ctx) {
        System.out.println("Enter column type");
    }

    @Override
    public void exitType_name(SQLiteParser.Type_nameContext ctx) {
        System.out.println("Exit column type");
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
        }
        System.out.println("Enter column constraint");
    }

    @Override
    public void exitColumn_constraint(SQLiteParser.Column_constraintContext ctx) {
        System.out.println("Exit column constraint");
    }

    @Override
    public void enterConflict_clause(SQLiteParser.Conflict_clauseContext ctx) {

    }

    @Override
    public void exitConflict_clause(SQLiteParser.Conflict_clauseContext ctx) {

    }

    @Override
    public void enterExpr(SQLiteParser.ExprContext ctx) {

    }

    @Override
    public void exitExpr(SQLiteParser.ExprContext ctx) {

    }

    @Override
    public void enterForeign_key_clause(SQLiteParser.Foreign_key_clauseContext ctx) {

    }

    @Override
    public void exitForeign_key_clause(SQLiteParser.Foreign_key_clauseContext ctx) {

    }

    @Override
    public void enterRaise_function(SQLiteParser.Raise_functionContext ctx) {

    }

    @Override
    public void exitRaise_function(SQLiteParser.Raise_functionContext ctx) {

    }

    @Override
    public void enterIndexed_column(SQLiteParser.Indexed_columnContext ctx) {

    }

    @Override
    public void exitIndexed_column(SQLiteParser.Indexed_columnContext ctx) {

    }

    @Override
    public void enterTable_constraint(SQLiteParser.Table_constraintContext ctx) {
        System.out.println("Enter table constraint");
    }

    @Override
    public void exitTable_constraint(SQLiteParser.Table_constraintContext ctx) {
        System.out.println("Exit table constraint");
    }

    @Override
    public void enterWith_clause(SQLiteParser.With_clauseContext ctx) {

    }

    @Override
    public void exitWith_clause(SQLiteParser.With_clauseContext ctx) {

    }

    @Override
    public void enterQualified_table_name(SQLiteParser.Qualified_table_nameContext ctx) {

    }

    @Override
    public void exitQualified_table_name(SQLiteParser.Qualified_table_nameContext ctx) {

    }

    @Override
    public void enterOrdering_term(SQLiteParser.Ordering_termContext ctx) {

    }

    @Override
    public void exitOrdering_term(SQLiteParser.Ordering_termContext ctx) {

    }

    @Override
    public void enterPragma_value(SQLiteParser.Pragma_valueContext ctx) {

    }

    @Override
    public void exitPragma_value(SQLiteParser.Pragma_valueContext ctx) {

    }

    @Override
    public void enterCommon_table_expression(SQLiteParser.Common_table_expressionContext ctx) {

    }

    @Override
    public void exitCommon_table_expression(SQLiteParser.Common_table_expressionContext ctx) {

    }

    @Override
    public void enterResult_column(SQLiteParser.Result_columnContext ctx) {

    }

    @Override
    public void exitResult_column(SQLiteParser.Result_columnContext ctx) {

    }

    @Override
    public void enterTable_or_subquery(SQLiteParser.Table_or_subqueryContext ctx) {

    }

    @Override
    public void exitTable_or_subquery(SQLiteParser.Table_or_subqueryContext ctx) {

    }

    @Override
    public void enterJoin_clause(SQLiteParser.Join_clauseContext ctx) {

    }

    @Override
    public void exitJoin_clause(SQLiteParser.Join_clauseContext ctx) {

    }

    @Override
    public void enterJoin_operator(SQLiteParser.Join_operatorContext ctx) {

    }

    @Override
    public void exitJoin_operator(SQLiteParser.Join_operatorContext ctx) {

    }

    @Override
    public void enterJoin_constraint(SQLiteParser.Join_constraintContext ctx) {

    }

    @Override
    public void exitJoin_constraint(SQLiteParser.Join_constraintContext ctx) {

    }

    @Override
    public void enterSelect_core(SQLiteParser.Select_coreContext ctx) {

    }

    @Override
    public void exitSelect_core(SQLiteParser.Select_coreContext ctx) {

    }

    @Override
    public void enterCompound_operator(SQLiteParser.Compound_operatorContext ctx) {

    }

    @Override
    public void exitCompound_operator(SQLiteParser.Compound_operatorContext ctx) {

    }

    @Override
    public void enterSchema_name(SQLiteParser.Schema_nameContext ctx) {

    }

    @Override
    public void exitSchema_name(SQLiteParser.Schema_nameContext ctx) {

    }

    @Override
    public void enterTable_function_name(SQLiteParser.Table_function_nameContext ctx) {

    }

    @Override
    public void exitTable_function_name(SQLiteParser.Table_function_nameContext ctx) {

    }

    @Override
    public void enterSigned_number(SQLiteParser.Signed_numberContext ctx) {

    }

    @Override
    public void exitSigned_number(SQLiteParser.Signed_numberContext ctx) {

    }

    @Override
    public void enterLiteral_value(SQLiteParser.Literal_valueContext ctx) {

    }

    @Override
    public void exitLiteral_value(SQLiteParser.Literal_valueContext ctx) {

    }

    @Override
    public void enterUnary_operator(SQLiteParser.Unary_operatorContext ctx) {

    }

    @Override
    public void exitUnary_operator(SQLiteParser.Unary_operatorContext ctx) {

    }

    @Override
    public void enterError_message(SQLiteParser.Error_messageContext ctx) {

    }

    @Override
    public void exitError_message(SQLiteParser.Error_messageContext ctx) {

    }

    @Override
    public void enterModule_argument(SQLiteParser.Module_argumentContext ctx) {

    }

    @Override
    public void exitModule_argument(SQLiteParser.Module_argumentContext ctx) {

    }

    @Override
    public void enterColumn_alias(SQLiteParser.Column_aliasContext ctx) {

    }

    @Override
    public void exitColumn_alias(SQLiteParser.Column_aliasContext ctx) {

    }

    @Override
    public void enterKeyword(SQLiteParser.KeywordContext ctx) {

    }

    @Override
    public void exitKeyword(SQLiteParser.KeywordContext ctx) {

    }

    @Override
    public void enterName(SQLiteParser.NameContext ctx) {

    }

    @Override
    public void exitName(SQLiteParser.NameContext ctx) {

    }

    @Override
    public void enterFunction_name(SQLiteParser.Function_nameContext ctx) {

    }

    @Override
    public void exitFunction_name(SQLiteParser.Function_nameContext ctx) {

    }

    @Override
    public void enterDatabase_name(SQLiteParser.Database_nameContext ctx) {

    }

    @Override
    public void exitDatabase_name(SQLiteParser.Database_nameContext ctx) {

    }

    @Override
    public void enterTable_name(SQLiteParser.Table_nameContext ctx) {
        tableBuilder.setName(ctx.getText());
        System.out.println("Enter table name");
    }

    @Override
    public void exitTable_name(SQLiteParser.Table_nameContext ctx) {
        System.out.println("Exit table name");
    }

    @Override
    public void enterTable_or_index_name(SQLiteParser.Table_or_index_nameContext ctx) {

    }

    @Override
    public void exitTable_or_index_name(SQLiteParser.Table_or_index_nameContext ctx) {

    }

    @Override
    public void enterNew_table_name(SQLiteParser.New_table_nameContext ctx) {

    }

    @Override
    public void exitNew_table_name(SQLiteParser.New_table_nameContext ctx) {

    }

    @Override
    public void enterColumn_name(SQLiteParser.Column_nameContext ctx) {
        columnBuilder.setName(ctx.getText());
        System.out.println("Enter column name");
    }

    @Override
    public void exitColumn_name(SQLiteParser.Column_nameContext ctx) {
        System.out.println("Exit column name");
    }

    @Override
    public void enterCollation_name(SQLiteParser.Collation_nameContext ctx) {
        System.out.println("Enter collation name");
    }

    @Override
    public void exitCollation_name(SQLiteParser.Collation_nameContext ctx) {
        System.out.println("Exit collation name");
    }

    @Override
    public void enterForeign_table(SQLiteParser.Foreign_tableContext ctx) {

    }

    @Override
    public void exitForeign_table(SQLiteParser.Foreign_tableContext ctx) {

    }

    @Override
    public void enterIndex_name(SQLiteParser.Index_nameContext ctx) {

    }

    @Override
    public void exitIndex_name(SQLiteParser.Index_nameContext ctx) {

    }

    @Override
    public void enterTrigger_name(SQLiteParser.Trigger_nameContext ctx) {

    }

    @Override
    public void exitTrigger_name(SQLiteParser.Trigger_nameContext ctx) {

    }

    @Override
    public void enterView_name(SQLiteParser.View_nameContext ctx) {

    }

    @Override
    public void exitView_name(SQLiteParser.View_nameContext ctx) {

    }

    @Override
    public void enterModule_name(SQLiteParser.Module_nameContext ctx) {

    }

    @Override
    public void exitModule_name(SQLiteParser.Module_nameContext ctx) {

    }

    @Override
    public void enterPragma_name(SQLiteParser.Pragma_nameContext ctx) {

    }

    @Override
    public void exitPragma_name(SQLiteParser.Pragma_nameContext ctx) {

    }

    @Override
    public void enterSavepoint_name(SQLiteParser.Savepoint_nameContext ctx) {

    }

    @Override
    public void exitSavepoint_name(SQLiteParser.Savepoint_nameContext ctx) {

    }

    @Override
    public void enterTable_alias(SQLiteParser.Table_aliasContext ctx) {

    }

    @Override
    public void exitTable_alias(SQLiteParser.Table_aliasContext ctx) {

    }

    @Override
    public void enterTransaction_name(SQLiteParser.Transaction_nameContext ctx) {

    }

    @Override
    public void exitTransaction_name(SQLiteParser.Transaction_nameContext ctx) {

    }

    @Override
    public void enterAny_name(SQLiteParser.Any_nameContext ctx) {

    }

    @Override
    public void exitAny_name(SQLiteParser.Any_nameContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}
