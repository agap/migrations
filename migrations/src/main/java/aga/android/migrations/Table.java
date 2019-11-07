package aga.android.migrations;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created on 05.11.19
 * @author artem
 */
public final class Table {

    @NonNull
    private final String name;

    @NonNull
    private final List<Column> columns;

    @NonNull
    private final List<ForeignKey> foreignKeys;

    private Table(@NonNull String name,
                  @NonNull List<Column> columns,
                  @NonNull List<ForeignKey> foreignKeys) {
        this.name = name;
        this.columns = columns;
        this.foreignKeys = foreignKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return name.equals(table.name) &&
                columns.equals(table.columns) &&
                foreignKeys.equals(table.foreignKeys);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, columns, foreignKeys);
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                ", foreignKeys=" + foreignKeys +
                '}';
    }

    public static class Builder {

        private String name;

        private List<Column> columns = Collections.emptyList();

        private List<ForeignKey> foreignKeys = Collections.emptyList();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setColumns(List<Column> columns) {
            this.columns = columns;
            return this;
        }

        Builder setForeignKeys(List<ForeignKey> foreignKeys) {
            this.foreignKeys = foreignKeys;
            return this;
        }

        Table createTable() {
            return new Table(name, columns, foreignKeys);
        }
    }
}
