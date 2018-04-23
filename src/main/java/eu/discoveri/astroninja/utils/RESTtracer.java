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
package eu.discoveri.astroninja.utils;

import java.util.List;
import java.util.Map;
import javax.ws.rs.core.*;


/**
 * Display REST/HTTP info.
 * @see <a href="http://en.wikipedia.org/wiki/Representational_state_transfer">REST Wiki</a>.
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public class RESTtracer
{
    //<editor-fold defaultstate="collapsed" desc="Show Headers, URI, POST data">
    /**
     * Trace headers (which can be injected into the Response argument stream).
     * Using, for example, the &#64;Context annotation:
     * <pre>
     * &#64;GET
     * &#64;Path("/user")
     * public Response addUser(&#64;Context HttpHeaders httpders) {
     *    traceHeaders( httpders, "user add" );
     *    :
     * </pre>
     *
     * @param reqHeaders Http request headers
     * @param ctxt a user context to clarify where this occurs (eg: the method name)
     */
    public static void traceHdrs( HttpHeaders reqHeaders, String ctxt )
    {
        System.out.println( "Headers for [" +ctxt+ "]:" );
        for( Map.Entry<String, List<String>> map : reqHeaders.getRequestHeaders().entrySet() )
        {
            System.out.println( "Key : " +map.getKey() );
            for( String entry : map.getValue() )
            {
                System.out.println(" Value : " +entry );
            }
        }
    }

    /**
     * Show request URI. An example of its use:
     * <pre>
     *  public Response findAlias(&#64;PathParam("alias") String alias, &#64;Context UriInfo uriInfo) {
     *    RESTtracer.traceURI(uriInfo, palias);
     *    :
     * </pre>
     *
     * @param uriInfo the URI string being used for this request
     * @param ctxt a user context to clarify where this occurs (eg: the method name)
     */
    public static void traceURI( @Context UriInfo uriInfo, String ctxt )
    {
        System.out.println( "URI for [" +ctxt+ "]: " +uriInfo.getRequestUri().toString() );
    }
    //</editor-fold>
}
