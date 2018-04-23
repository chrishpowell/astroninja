//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012-2013 Chris Powell, CPgraph Ltd..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//</editor-fold>
package eu.discoveri.astroninja.utils;

import eu.discoveri.astroninja.config.AllConfig;
import java.awt.Color;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Various utilities such as logging and stack trace strings.
 * @Todo: Sort out logging and config properly
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public final class CrispIUtil
{
    //<editor-fold defaultstate="collapsed" desc="Variables">
    //... Output
    private static final Writer writer = new StringWriter();
    private static PrintWriter pw = new PrintWriter(writer);
    //... Singleton instance
    public static final CrispIUtil LOG = null;
    //... Logging
    public Logger logger;
    //... Colours
    private final static Map<Color,String> colourMap = new HashMap<>();
    //</editor-fold>
    
    static {
        colourMap.put(Color.BLACK,      "black");
        colourMap.put(Color.BLUE,       "blue");
        colourMap.put(Color.LIGHT_GRAY, "lightgray");
        colourMap.put(Color.GRAY,       "gray");
        colourMap.put(Color.GREEN,      "green");
        colourMap.put(Color.ORANGE,     "orange");
        colourMap.put(Color.RED,        "red");
        colourMap.put(Color.WHITE,      "white");
        colourMap.put(Color.MAGENTA,    "magenta");
    }

    //<editor-fold defaultstate="collapsed" desc="Constructora">
    /**
     * Set logging
     * @param useSimple true will use simplest logging (console etc.), false
     *  will use configuration file defined in AllConfig
     * @throws Exception
     */
    public CrispIUtil( boolean useSimple )
            throws Exception
    {
        // Logger
        logger = LoggerFactory.getLogger( AllConfig.baseconfig().getConfig().getLogging().getLogger() );

        // Get logging.properties
        if( !useSimple )
        {
            PropertyConfigurator.configure( AllConfig.baseconfig().getConfig().getLogging().getLoggingPropFile() );
        }
    }

    public CrispIUtil( Map<String,String> props )
            throws Exception
    {
        Properties log4jProperties = new Properties();

        // Logger
        logger = LoggerFactory.getLogger( AllConfig.baseconfig().getConfig().getLogging().getLogger() );
        // Set logging.properties
        props.entrySet().forEach((entry) -> {
            log4jProperties.setProperty( entry.getKey(), entry.getValue() );
        });
        PropertyConfigurator.configure(log4jProperties);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Stack trace">
    /**
     * Return stack trace as string
     *
     * @param   throwable
     * @return  stack trace
     */
    public static String getStackTrace(Throwable throwable)
    {
        throwable.printStackTrace(pw);
        return writer.toString();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Get Colour name">
    /**
     * Get colour name from enum list
     * @param colour
     * @return
     */
    public static String getColourName( Color colour )
    {
        // If no match return black
        if( !colourMap.containsKey(colour) )
            return "Black";
        
        return colourMap.get(colour);
    }
    //</editor-fold>
}
