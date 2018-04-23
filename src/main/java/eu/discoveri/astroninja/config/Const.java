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
package eu.discoveri.astroninja.config;

/**
 * System constants
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class Const
{
    //<editor-fold defaultstate="collapsed" desc="Constants">
    /** Configuration XML file */
    protected static final String CONFIGFILE="/home/chrispowell/NetBeansProjects/AstroNinja/src/main/resources/Config/Configuration.xml";
    protected static final String SCHEMAGEN = "javax.persistence.schema-generation.database.action";
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Comparable ">
    /** Comparator: less/before */
    public static int BEFORE = -1;
    /** Comparator: Equal */
    public static int EQUAL = 0;
    /** Comparator: more/after */
    public static int AFTER = 1;
    // </editor-fold>
}
