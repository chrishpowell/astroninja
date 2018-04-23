//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012-2013 Meritwork.com.
 *
 * This file is subject to the terms and conditions defined in
 * 'LICENSE.txt', which is part of this source code package.
 */
//</editor-fold>
package eu.discoveri.astroninja.config;

import org.simpleframework.xml.Element;

/**
 * Configuration for database being the persistence unit (PU), the default file
 * extension for JSON files, and PU via properties
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Database
{
    //<editor-fold defaultstate="collapsed" desc="Annotations and Variables">
    @Element(name="DefPersistUnit")
    private String  defPersistUnit;
    @Element(name="DbJSONExt",required=false)
    private String  dbJSONExt;
    @Element(name="pJPAxmlProp",required=false)
    private String   pJPAxmlProp;
    @Element(name="ConnectionURL")
    private String connectionURL;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    public String getDefPersistUnit()
    {
        return defPersistUnit;
    }

    public String getDbJSONExt()
    {
        return dbJSONExt;
    }

    public String getPJPAxmlProp()
    {
        return pJPAxmlProp;
    }
    
    public String getConnectionURL()
    {
        return connectionURL;
    }
    //</editor-fold>
}
