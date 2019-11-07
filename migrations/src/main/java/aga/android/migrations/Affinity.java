package aga.android.migrations;

import androidx.annotation.NonNull;

/**
 * Created on 07.11.19
 * @author artem
 */
//todo add NULL and BLOB
public enum Affinity {
    INTEGER,
    REAL,
    TEXT;

    static Affinity of(@NonNull final String columnDefinitionType) {
        if (columnDefinitionType.equalsIgnoreCase("integer")) {
            return INTEGER;
        } else if (columnDefinitionType.equalsIgnoreCase("real")) {
            return REAL;
        } else if (columnDefinitionType.equalsIgnoreCase("text")
                || columnDefinitionType.toLowerCase().contains("varchar")) {
            return TEXT;
        } else {
            throw new IllegalArgumentException(
                "Unknown column definition type: " + columnDefinitionType
            );
        }
    }
}
