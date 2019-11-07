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

    private final boolean isPrimaryKey;

    private final Affinity type;

    private Column(@NonNull String name,
                   boolean isNullable,
                   boolean isPrimaryKey,
                   @NonNull Affinity type) {
        this.name = name;
        this.isNullable = isNullable;
        this.isPrimaryKey = isPrimaryKey;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return isNullable == column.isNullable &&
                isPrimaryKey == column.isPrimaryKey &&
                type == column.type &&
                name.equals(column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isNullable, isPrimaryKey, type);
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", isNullable=" + isNullable +
                ", isPrimaryKey=" + isPrimaryKey +
                ", type=" + type +
                '}';
    }

    public static class Builder {

        private String name;

        private boolean isNullable = true;

        private boolean isPrimaryKey = false;

        private Affinity type;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIsNullable(boolean isNullable) {
            this.isNullable = isNullable;
            return this;
        }

        public Builder setIsPrimaryKey(boolean isPrimaryKey) {
            this.isPrimaryKey = isPrimaryKey;
            return this;
        }

        public Builder setType(Affinity type) {
            this.type = type;
            return this;
        }

        public Column createColumn() {
            return new Column(name, isNullable, isPrimaryKey, type);
        }
    }
}
