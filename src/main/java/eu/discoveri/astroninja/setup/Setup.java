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

import java.util.Set;

/**
 * Web server setup
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public interface Setup
{
    /**
     * The set of (optional) classes to be used in Setup.
     * @todo implement
     * @param classes
     */
    void setClasses( Set<Class<?>> classes );

    /**
     * Get the classes used in Setup.
     * @return Set of classes used in Setup
     */
    Set<Class<?>> getClasses();

    /**
     * Get REST provider file path.
     * @return REST provider file path
     */
    String getProviderPath();

    /**
     * Set REST provider file path (relative to doc root).
     * @param providerPath the path to the provider file
     */
    void setProviderPath(String providerPath);

    /**
     * Get REST resources path.
     * @return REST resources file path
     */
    String getResourcePath();

    /**
     * Set REST resources path (relative to doc root).
     * @param resourcePath the path to the resources file
     */
    void setResourcePath(String resourcePath);

    /**
     * Get the port number used by the Web server.
     * @return Port number (eg: 8080)
     */
    int getPortNum();

    /**
     * Get Web server doc root.
     * @return Web server doc root
     */
    String getDocRoot();

    /**
     * Set Web server doc root.
     * @param docRoot path for Web server root
     */
    void setDocRoot( String docRoot );

    /**
     * Get REST entry point (for all REST services).
     * @return REST entry point (eg: /rest)
     */
    String getRESTEntryPoint();

    /**
     * Get graph entry point (for all graphs).
     * @return Graph entry point (eg: /graph)
     */
    String getGraphEntryPoint();

    /**
     * Get graph data entry point (SVG).
     * @return Graph data entry point (eg: /graphdata)
     */
    String getGraphDataEntryPoint();

    /**
     * Get security access point.
     * @return Security admin access point (eg: /secadmin)
     */
    String getAccessEntryPoint();

    /**
     * Set security access point.
     * @param accessEntryPoint the security access (admin) point
     */
    void setAccessEntryPoint(String accessEntryPoint);
}
