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

    private Table(@NonNull String name,
                  @NonNull List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return name.equals(table.name) &&
                columns.equals(table.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, columns);
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }

    public static class Builder {

        private String name;

        private List<Column> columns = Collections.emptyList();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setColumns(List<Column> columns) {
            this.columns = columns;
            return this;
        }

        Table createTable() {
            return new Table(name, columns);
        }
    }
}
