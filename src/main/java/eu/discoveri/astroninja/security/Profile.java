//<editor-fold defaultstate="collapsed" desc="Copyright">
/**
 * Copyright 2012 Snupps Ltd, UK.
 * All rights reserved.
 * This material may not be reproduced, displayed, modified or distributed without the express prior written permission of the copyright holder.
 */
//</editor-fold>
package eu.discoveri.astroninja.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A definition of the profile for a user.
 * A Profile is associated with a set of permissions, see {@link Perm Perm enum}
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public class Profile
{
    //<editor-fold defaultstate="collapsed" desc="Constants and Fields">
    //...Persist
    protected String          profileName;
    protected String[]        pperms;
    protected List<Profile>   superiors;

    //...Transient
    private transient List<String>  perms = null;
    private transient Perm[]        eperms = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Constructor
     * @param name      name of this profile
     * @param perms     an array of permissions
     */
    public Profile( String name, Perm... perms )
    {
        setProfileName( name );
        setPPerms( perms );
        superiors = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /**
     * Set Profile name.
     * @param name the given name of this profile (eg: StdUser)
     */
    public final void setProfileName( String name )
    {
        profileName = name;
    }
    /**
     * Get profile name.
     * @return Name of this profile
     */
    public String getProfileName()
    {
        return profileName;
    }

    /**
     * Get persisted permissions.
     * @return  the array permission strings
     */
    public String[] getPPerms()
    {
        return pperms;
    }

    /**
     * Set persisted permissions for this profile.
     * @param eperms the set of permissions
     */
    public final void setPPerms( Perm... eperms )
    {
        perms = toStrList(eperms);
        pperms = perms.toArray(new String[perms.size()]);
    }

    /**
     * Access to superior (child) Profiles.
     * @return immutable list of superior (child) Profiles
     */
    public List<Profile> getSuperiors() {
        return Collections.unmodifiableList(superiors);
    }

    /**
     * Add a superior (child) Profile.
     * @param superior Profile to add to superior (child) list
     */
    public void addSuperior(Profile superior) {
        superiors.add(superior);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Manipulate between transient and persisted">
    /**
     * Get permissions for this profile
     * @return  List of strings representing permissions
     */
    public List<String> getPerms()
    {
        return perms;
    }
    /**
     * Set transient perms from input list
     * @todo remove? do better
     *
     * @param inheritPerms the inherited list of all perms
     */
    public void setPerms( List<String> inheritPerms )
    {
        perms = inheritPerms;
    }

    /**
     * Profile (String) permissions as String
     * @return Comma separated list of permissions
     */
    public String strPerms()
    {
        String allperms = "";

        for( String p : perms )
            allperms = p + ", " + allperms;

        return allperms;
    }

    /*
     * Return an array of String from enum (IG requirement)
     */
    private List<String> toStrList( Perm... perms )
    {
      ArrayList<String> parr = new ArrayList<>();
        for( Perm p : perms )
            parr.add(p.name());

        return parr;
    }
    //</editor-fold>

    protected String getToStringName() {
        return "Profile";
    }

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return getToStringName() + "[" + "profileName=" + profileName + ", superiors=" + superiors + ']';
    }
    //</editor-fold>
}
