package aga.android.migrations;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created on 05.11.19
 * @author artem
 */
public final class Column {

    @NonNull
    private final String name;

    private final boolean isNullable;

    private final boolean isPrimaryKey;

    @Nullable
    private final String defaultValue;

    private final Affinity type;

    private Column(@NonNull String name,
                   boolean isNullable,
                   boolean isPrimaryKey,
                   @Nullable String defaultValue,
                   @NonNull Affinity type) {
        this.name = name;
        this.isNullable = isNullable;
        this.isPrimaryKey = isPrimaryKey;
        this.defaultValue = defaultValue;
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
                Objects.equals(defaultValue, column.defaultValue) &&
                name.equals(column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isNullable, isPrimaryKey, type, defaultValue);
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", isNullable=" + isNullable +
                ", isPrimaryKey=" + isPrimaryKey +
                ", type=" + type +
                ", default=" + defaultValue +
                '}';
    }

    public static class Builder {

        private String name;

        private String defaultValue;

        private boolean isNullable = true;

        private boolean isPrimaryKey = false;

        private Affinity type;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        Builder setIsNullable(boolean isNullable) {
            this.isNullable = isNullable;
            return this;
        }

        Builder setIsPrimaryKey(boolean isPrimaryKey) {
            this.isPrimaryKey = isPrimaryKey;
            return this;
        }

        Builder setType(Affinity type) {
            this.type = type;
            return this;
        }

        Builder setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        Column createColumn() {
            return new Column(name, isNullable, isPrimaryKey, defaultValue, type);
        }
    }
}
