package aga.android.migrations;

import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created on 05.11.19
 * @author artem
 */
public final class Column {

    @NonNull
    private final String name;

    private final boolean isNullable;

    public Column(@NonNull String name,
                  boolean isNullable) {
        this.name = name;
        this.isNullable = isNullable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return isNullable == column.isNullable &&
                name.equals(column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isNullable);
    }
}
