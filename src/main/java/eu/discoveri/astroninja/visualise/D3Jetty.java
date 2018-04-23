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
package eu.discoveri.astroninja.visualise;

import eu.discoveri.astroninja.graph.GraphI;
import eu.discoveri.astroninja.utils.CrispIUtil;
import eu.discoveri.astroninja.setup.Setup;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.wink.server.internal.servlet.RestServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;


/**
 * Set up and initialise Jetty for REST and D3 graph data
 *
 * @author Chris Powell, CPgraph Ltd.
 */
@SuppressWarnings("serial")
public class D3Jetty
{
  // Graph db setup
  private static EntityManager db = null;
  private static GraphI        gr = null;
  // Graph db data
  private static D3GraphData   d3gd = null;

    /**
     * Start Jetty against a graph
     *
     * @param graphName     Db name (marker)
     * @param persistUnit   Db persist unit
     * @param setup         Jetty setup
     * @throws Exception
     */
    public static void startJetty( String graphName, String persistUnit, Setup setup )
        throws Exception
    {
        // Set up graph db (graphName does little other than tie a string to the PU)
        gr = new GraphI( graphName, persistUnit );

        // Open current or create new graph db
        try
        {
            db = gr.openGraph();
        }
        catch( PersistenceException pex )
        {
            System.out.println( ">>> Can't open db: " +CrispIUtil.getStackTrace(pex) );
            gr.closeGraph();
            return;
        }
        System.out.println( "\r\nOpened GraphDB... Name: " +graphName+ ", PU: " +persistUnit );

        // Web server
        Server server = new Server( setup.getPortNum() );

        // Web context
        ServletContextHandler scontext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        scontext.setContextPath("/");
        System.out.println( "Servlet context configured..." );

        // WebApp
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase( setup.getDocRoot() );
        webapp.setParentLoaderPriority(true);
        System.out.println( "Webapp context configured...  resourceBase(docRoot): " +webapp.getResourceBase() );

        // Handlers
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] { scontext, webapp });
        System.out.println( "Contexts added... scontext, webapp" );

        // OpenAM (if required)
//        String openamWar = setup.getOpenAMwar();
//        WebAppContext access = null;
//        if( openamWar != null && openamWar != "" )
//        {
//            File f = new File(openamWar);
//            if( f.exists() )
//            {
//                access = new WebAppContext();
//                access.setContextPath(setup.getAccessEntryPoint());
//                access.setWar(openamWar);
//                contexts.addHandler( access );
//                System.out.println( "Context added... access" );
//            }
//            else
//                System.out.println( setup.getOpenAMwar()+ " (OpenAM WAR file) not found..." );
//        }

        // Fire up the Graph data generator
        d3gd  = new D3GraphData();

        // Wink stuff
        if( setup.getResourcePath() != null && setup.getResourcePath() != "" )
        {
            ServletHolder appSrvHold = new ServletHolder( new RestServlet() );
            // Load on start
            appSrvHold.setInitOrder(1);
            // Providers and Resources paths
            appSrvHold.setInitParameter( "applicationConfigLocation", setup.getResourcePath() );
            scontext.addServlet( appSrvHold, setup.getRESTEntryPoint()+"/*" );
        }

        // Set up graph servlets
        scontext.addServlet(new ServletHolder(new D3GraphServlet(gr,d3gd)),"/"+setup.getGraphEntryPoint());

        // Start the server
        System.out.println( "Server settings:" +setup.toString() );

        server.setHandler(contexts);
        System.out.println( "\r\nServer initializing.  Please wait for 'Server started' message...\r\n" );
        server.start();
        System.out.println("\r\n---------------------------------------------" );
        System.out.println("Server started!\r\nAccess on <server>:" +setup.getPortNum());
        System.out.println("\r\nNavigate to /" +setup.getGraphEntryPoint()+ " to see graph JSON data");
        System.out.println("Navigate to, eg: " +setup.getRESTEntryPoint()+ "/person to get (raw) database data");
        System.out.println("Navigate to " +setup.getAccessEntryPoint()+ " to setup security");
        System.out.println("---------------------------------------------");
        server.join();
    }
}
