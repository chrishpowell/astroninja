//<editor-fold defaultstate="collapsed" desc="Copyright">
/**
 * Copyright 2012 Snupps Ltd, UK.
 * All rights reserved.
 * This material may not be reproduced, displayed, modified or distributed without the express prior written permission of the copyright holder.
 */
//</editor-fold>
package eu.discoveri.astroninja.security;

/**
 * The set of permissions grouped into a Profile (@see Profile).
 * @author Chris Powell, CPgraph Ltd.
 */
public enum Perm {
    /**
     * Profile owner can view public stuff
     */
    VIEW,

    /**
     * Profile owner can create tags (owned tag lists) and stuff
     */
    CREATE,
    /**
     * Profile owner can amend tags (of owned tag lists) and stuff
     */
    UPDATE,
    /**
     * Profile owner can delete stuff (not tags)
     */
    DELETE,

    /**
     * Profile owner can view private or group stuff
     */
    PRIVVIEW,

    /**
     * Profile owner allows friends to view FRIEND stuff (shelves etc.)
     */
    FRIENDVIEW,

    /**
     * Profile owner is associated with an Agency
     */
    SUBUSER,
    /**
     * Agency superuser (can add subusers, create groups associated with agency)
     */
    AGENCYSU,

    /**
     * Profile owner has unlimited storage
     */
    UNLIMSTOR,

    /**
     * Profile owner can manage a specific Group (or Groups)
     */
    GROUP,

    /**
     * Profile owner can use advanced search mechanisms
     */
    ADVSEARCH,

    /**
     * Profile owner can manage all snupps Groups (non-Agency groups)
     */
    GRPSMANAGE,

    /**
     * Profile owner can lock user, reset pwd etc.
     */
    ADMINUSER;

}
