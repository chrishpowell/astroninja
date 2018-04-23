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


import org.simpleframework.xml.Element;

/**
 * General configuration
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class Config
{
    @Element(name="Logging")
    private Logging     logging;
    @Element(name="LIndexing")
    private LIndexing   lindexing;
    @Element(name="Security")
    private Security    security;
    @Element(name="Json")
    private Json        json;

    public Logging getLogging()
    {
        return logging;
    }

    public Security getSecurity()
    {
        return security;
    }

    public LIndexing getLIndexing()
    {
        return lindexing;
    }

    public Json getJson()
    {
        return json;
    }

}
