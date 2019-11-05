package aga.android.migrations;

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

    public Table(@NonNull String name,
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
}
