package aga.android.migrations;

/**
 * Created on 07.11.19
 * @author artem
 */
public enum ForeignKeyAction {
    CASCADE,
    NO_ACTION,
    RESTRICT,
    SET_NULL,
    SET_DEFAULT;
}
