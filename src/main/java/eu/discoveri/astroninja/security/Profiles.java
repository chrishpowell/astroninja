//<editor-fold defaultstate="collapsed" desc="Copyright">
/**
 * Copyright 2012 Snupps Ltd, UK; Snupps Inc, USA
 * All rights reserved.
 * This material may not be reproduced, displayed, modified or distributed without the express prior written permission of the copyright holder.
 */
//</editor-fold>
package eu.discoveri.astroninja.security;

/**
 * Marker subclass of Profile to represent the root Profile.
 * Having this as a subclass makes it easy to find on the graph db
 * @author Chris Powell, CPgraph Ltd.
 */
public class Profiles extends Profile {

    //<editor-fold defaultstate="collapsed" desc="Constants and Fields">
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Profiles(String name, Perm... perms) {
        super(name, perms);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    //</editor-fold>

    @Override
    protected String getToStringName() {
        return "Profiles";
    }
}
