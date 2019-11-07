package aga.android.migrations;

import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * Created on 07.11.19
 * @author artem
 */
class ForeignKey {

    @NonNull
    private final String keyName;

    @NonNull
    private final String referenceTable;

    @NonNull
    private final String referenceField;

    @NonNull
    private final ForeignKeyAction onUpdate;

    @NonNull
    private final ForeignKeyAction onDelete;

    private ForeignKey(@NonNull String referenceTable,
                       @NonNull String referenceField,
                       @NonNull String keyName,
                       @NonNull ForeignKeyAction onUpdate,
                       @NonNull ForeignKeyAction onDelete) {
        this.referenceTable = referenceTable;
        this.referenceField = referenceField;
        this.keyName = keyName;
        this.onUpdate = onUpdate;
        this.onDelete = onDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForeignKey that = (ForeignKey) o;
        return referenceTable.equals(that.referenceTable) &&
                referenceField.equals(that.referenceField) &&
                keyName.equals(that.keyName) &&
                onUpdate.equals(that.onUpdate) &&
                onDelete.equals(that.onDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceTable, referenceField, keyName, onUpdate, onDelete);
    }

    @NonNull
    @Override
    public String toString() {
        return "ForeignKey{" +
                "keyName='" + keyName + '\'' +
                ", referenceTable='" + referenceTable + '\'' +
                ", referenceField='" + referenceField + '\'' +
                ", onUpdate='" + onUpdate + '\'' +
                ", onDelete='" + onDelete + '\'' +
                '}';
    }

    public static class Builder {

        private String referenceTable;

        private String referenceField;

        private String keyName;

        private ForeignKeyAction onUpdate;

        private ForeignKeyAction onDelete;

        public Builder setReferenceTable(String referenceTable) {
            this.referenceTable = referenceTable;
            return this;
        }

        public Builder setReferenceField(String referenceField) {
            this.referenceField = referenceField;
            return this;
        }

        public Builder setKeyName(String keyName) {
            this.keyName = keyName;
            return this;
        }

        public Builder setOnUpdate(ForeignKeyAction onUpdate) {
            this.onUpdate = onUpdate;
            return this;
        }

        public Builder setOnDelete(ForeignKeyAction onDelete) {
            this.onDelete = onDelete;
            return this;
        }

        public ForeignKey createForeignKey() {
            return new ForeignKey(referenceTable, referenceField, keyName, onUpdate, onDelete);
        }
    }
}
