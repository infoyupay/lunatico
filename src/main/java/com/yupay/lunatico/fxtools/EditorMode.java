package com.yupay.lunatico.fxtools;

/**
 * Editor form mode of functionality.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum EditorMode {
    /**
     * All fields to create a new entity
     * should be activated.
     */
    CREATOR,
    /**
     * Only editable fields of an entity
     * may be activated.
     */
    EDITOR,
    /**
     * No field should be editable.
     */
    VIEWER
}
