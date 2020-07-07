package aga.android.migrations

import java.util.*

/**
 * Created on 07.07.20
 * @author artem
 */

data class Schema internal constructor(private val tables: List<Table>)

internal data class Table (
    private val name: String,
    private val columns: List<Column>,
    private val foreignKeys: List<ForeignKey> = emptyList()
)

internal data class Column (
    internal val name: String,
    private val isNullable: Boolean,
    private val isPrimaryKey: Boolean,
    private val defaultValue: String?,
    private val type: Affinity
) {

    class Builder {
        private var name: String = ""
        private var defaultValue: String? = null
        private var isNullable = true
        private var isPrimaryKey = false
        private var type: Affinity = Affinity.TEXT

        fun setName(name: String): Builder = apply { this.name = name }

        fun setIsNullable(isNullable: Boolean): Builder = apply { this.isNullable = isNullable }

        fun setIsPrimaryKey(isPrimaryKey: Boolean): Builder = apply {
            this.isPrimaryKey = isPrimaryKey
        }

        fun setType(type: Affinity): Builder = apply { this.type = type }

        fun setDefaultValue(defaultValue: String?): Builder = apply {
            this.defaultValue = defaultValue
        }

        fun build() = Column(name, isNullable, isPrimaryKey, defaultValue, type)
    }
}

internal data class ForeignKey constructor(
    private val referenceTable: String,
    private val referenceField: String,
    private val keyName: String,
    private val onUpdate: ForeignKeyAction,
    private val onDelete: ForeignKeyAction
) {

    internal class Builder {
        private var referenceTable: String = ""
        private var referenceField: String = ""
        private var keyName: String = ""
        private var onUpdate: ForeignKeyAction = ForeignKeyAction.NO_ACTION
        private var onDelete: ForeignKeyAction = ForeignKeyAction.NO_ACTION

        fun setReferenceTable(referenceTable: String): Builder = apply {
            this.referenceTable = referenceTable
        }

        fun setReferenceField(referenceField: String): Builder = apply {
            this.referenceField = referenceField
        }

        fun setKeyName(keyName: String): Builder = apply {
            this.keyName = keyName
        }

        fun setOnUpdate(onUpdate: ForeignKeyAction): Builder = apply {
            this.onUpdate = onUpdate
        }

        fun setOnDelete(onDelete: ForeignKeyAction): Builder = apply {
            this.onDelete = onDelete
        }

        fun build(): ForeignKey = ForeignKey(
                referenceTable, referenceField, keyName, onUpdate, onDelete
        )
    }
}

internal enum class ForeignKeyAction {
    CASCADE, NO_ACTION, RESTRICT, SET_NULL, SET_DEFAULT
}

//todo add NULL and BLOB
enum class Affinity {
    INTEGER, REAL, TEXT;

    companion object {
        fun of(columnDefinitionType: String): Affinity {
            return if (columnDefinitionType.equals("integer", ignoreCase = true)
                    || columnDefinitionType.equals("boolean", ignoreCase = true)) {
                INTEGER
            } else if (columnDefinitionType.equals("real", ignoreCase = true)) {
                REAL
            } else if (columnDefinitionType.equals("text", ignoreCase = true)
                    || columnDefinitionType.equals("date", ignoreCase = true)
                    || columnDefinitionType.toLowerCase(Locale.getDefault()).contains("varchar")) {
                TEXT
            } else {
                throw IllegalArgumentException(
                        "Unknown column definition type: $columnDefinitionType"
                )
            }
        }
    }
}