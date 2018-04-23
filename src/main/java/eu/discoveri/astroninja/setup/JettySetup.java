//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012 Chris Powell, CPgraph Ltd..
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
package eu.discoveri.astroninja.setup;

import java.util.HashSet;
import java.util.Set;


/**
 * Set up Jetty for REST.  See examples documentation for details.
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public class JettySetup implements Setup
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    private int             portNum= 8080;          // Web port
    private String          docRoot = ".";          // Document root
    private Set<Class<?>>   classes = null;
    private String          providerPath = null,
                            resourcePath = null;    // REST resource file path
    private String          restEntryPoint = "/rest",
                            graphEntryPoint = "graph",
                            graphDataEntryPoint = "/D3GraphData",
                            accessEntryPoint = "/secadm";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Setup for Jetty.
     * Setup, no REST services, no security
     *
     * @param docRoot Web document root
     */
    public JettySetup( String docRoot )
    {
        this( docRoot, 8080, "", "", "/rest", "graph", "/D3GraphData", null );
    }

    /**
     * Setup for Jetty.
     * Setup with no security
     *
     * @param docRoot Web document root (eg: ./crispi)
     * @param providerPath
     * @param resourcePath
     */
    public JettySetup( String docRoot, String providerPath, String resourcePath )
    {
        this( docRoot, 8080, providerPath, resourcePath, "/rest", "graph", "/D3GraphData", null );
    }

    /**
     * Setup for Jetty.
     *
     * @param docRoot Web document root (eg: ./crispi)
     * @param providerPath path to provide classes
     * @param resourcePath path to resource classes
     * @param restEntry REST entry point (eg: /rest)
     * @param graphEntry graph entry point (eg: /graph)
     * @param graphDataEntry graph data entry point (eg: /graphdata)
     */
    public JettySetup( String docRoot, String providerPath, String resourcePath, String restEntry, String graphEntry, String graphDataEntry )
    {
        this( docRoot, 8080, providerPath, resourcePath, restEntry, graphEntry, graphDataEntry, null );
    }

    /**
     * Setup for Jetty
     *
     * @param docRoot Web document root
     * @param portNum Web server port (default: 8080)
     * @param providerPath path to provide classes
     * @param resourcePath path to resource classes
     * @param restEntry REST entry point (eg: /rest)
     * @param graphEntry graph entry point (eg: /graph)
     * @param graphDataEntry graph data entry point (eg: /graphdata)
     */
    public JettySetup( String docRoot, int portNum, String providerPath, String resourcePath, String restEntry, String graphEntry, String graphDataEntry )
    {
        this( docRoot, portNum, providerPath, resourcePath, restEntry, graphEntry, graphDataEntry, null );
    }

    /**
     * Setup for REST on Jetty
     *
     * @param docRoot Web document root
     * @param portNum Web server port (default: 8080)
     * @param providerPath path to provide classes
     * @param resourcePath path to resource classes
     * @param restEntry REST entry point (eg: /rest)
     * @param graphEntry graph entry point (eg: /graph)
     * @param graphDataEntry graph data entry point (eg: /graphdata)
     * @param classes Set of optional classes to provide setup (tbd)
     */
    public JettySetup( String docRoot, int portNum, String providerPath, String resourcePath, String restEntry, String graphEntry, String graphDataEntry, Set<Class<?>> classes )
    {
        this.docRoot = docRoot;
        this.portNum = portNum;
        this.providerPath = providerPath;
        this.resourcePath = resourcePath;
        this.restEntryPoint = restEntry;
        this.graphEntryPoint = graphEntry;
        this.graphDataEntryPoint = graphDataEntry;
        this.classes = classes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /** {@inheritDoc}
     */
    @Override
    public void setClasses( Set<Class<?>> classes )
    {
        this.classes = classes;
    }

    /** {@inheritDoc}
     */
    @Override
    public Set<Class<?>> getClasses()
    {
        return classes;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getProviderPath()
    {
        return providerPath;
    }

    /** {@inheritDoc}
     */
    @Override
    public void setProviderPath(String providerPath)
    {
        this.providerPath = providerPath;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getResourcePath()
    {
        return resourcePath;
    }

    /** {@inheritDoc}
     */
    @Override
    public void setResourcePath(String resourcePath)
    {
        this.resourcePath = resourcePath;
    }

    /** {@inheritDoc}
     */
    @Override
    public int getPortNum()
    {
        return portNum;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getDocRoot()
    {
        return docRoot;
    }

    /** {@inheritDoc}
     */
    @Override
    public void setDocRoot( String docRoot )
    {
        this.docRoot = docRoot;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getRESTEntryPoint()
    {
        return restEntryPoint;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getGraphEntryPoint()
    {
        return graphEntryPoint;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getGraphDataEntryPoint()
    {
        return graphDataEntryPoint;
    }

    /** {@inheritDoc}
     */
    @Override
    public String getAccessEntryPoint()
    {
        return accessEntryPoint;
    }

    /** {@inheritDoc}
     */
    @Override
    public void setAccessEntryPoint(String accessEntryPoint)
    {
        this.accessEntryPoint = accessEntryPoint;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString()
    {
        //     providerPath
        //     resourcePath
        //     restEntry
        //     graphEntry
        //     graph data
        //     portNum
        //     docRoot
        //     Security access point
        return  "\r\n Wink (RESTful Web services) setup:" +
                "\r\n   providerPath: " +getProviderPath()+
                "\r\n   resourcePath: " +getResourcePath()+
                "\r\n   REST servlet: " +getRESTEntryPoint()+
                "\r\n   Classes (if any): [tbd]" +
                "\r\n Graphs:" +
                "\r\n   Graph servlet: " +getGraphEntryPoint()+
                "\r\n   Graph data: " +getGraphDataEntryPoint()+
                "\r\n Web server:" +
                "\r\n   port num: " +getPortNum()+
                "\r\n   doc root: " +getDocRoot()+
                "\r\n Security:" +
                "\r\n   Security servlet: " +getAccessEntryPoint();
    }
    //</editor-fold>
}
