//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012-2013 Meritwork.com.
 *
 * This file is subject to the terms and conditions defined in
 * 'LICENSE.txt', which is part of this source code package.
 */
//</editor-fold>
package eu.discoveri.astroninja.config;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Configuration system
 *
 * @author Chris Powell, CPgraph Ltd.
 */
@Root(name="BaseConfig")
public class BaseConfig
{
    @Attribute
    private String    version;
    @Element(name="Database")
    private Database  database;
    @Element(name="Config")
    private Config    config;
    @Element(name="UIconfig")
    private UIConfig  uiConfig;

    public String getVersion()
    {
        return version;
    }

    public Database getDatabase()
    {
        return database;
    }

    public Config getConfig()
    {
        return config;
    }

    public UIConfig getUIConfig()
    {
        return uiConfig;
    }
}
