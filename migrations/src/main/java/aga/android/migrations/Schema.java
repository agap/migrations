package aga.android.migrations;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created on 05.11.19
 * @author artem
 */
public final class Schema {

    @NonNull
    private final List<Table> tables;

    public Schema(@NonNull List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return tables.equals(schema.tables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tables);
    }
}
