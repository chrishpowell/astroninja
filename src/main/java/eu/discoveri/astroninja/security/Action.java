//<editor-fold defaultstate="collapsed" desc="Copyright">
/**
 * Copyright 2012 Snupps Ltd, UK; Snupps Inc, USA
 * All rights reserved.
 * This material may not be reproduced, displayed, modified or distributed without the express prior written permission of the copyright holder.
 */
//</editor-fold>
package eu.discoveri.astroninja.security;

/**
 * The set of Actions constituting the atomic processes of the business logic.
 * These may be: Update Display, View friend's Shelf, etc.  Some actions may
 * constitute multiple other actions.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public enum Action {

    /**
     * To view an object
     */
    VIEW,
    /**
     * To create an object
     */
    CREATE,
    /**
     * To update an object
     */
    UPDATE,
    /**
     * To delete an object
     */
    DELETE,
    /**
     * To like (graded) an object
     */
    LIKE,
    /**
     * To follow an object
     */
    FOLLOW,
    /**
     * To comment on an object
     */
    COMMENT,
    /**
     * To message someone
     */
    MSG;

}
