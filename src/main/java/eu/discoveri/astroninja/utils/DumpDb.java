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

import eu.discoveri.astroninja.graph.Graph;
import eu.discoveri.astroninja.utils.DatabaseUtils;
import eu.discoveri.astroninja.visualise.D3GraphData;


/**
 * Dump a graph database.
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.8
 *
 * @since 0.9
 */
public class DumpDb
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    private Graph                 gr = null;
    private Class                 vClass = null, eClass = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor.
     * @param gr the graph
     * @param vClass vertex class
     * @param eClass edge class
     */
    public DumpDb( Graph gr, Class vClass, Class eClass )
    {
        this.gr = gr;
        this.vClass = vClass;
        this.eClass = eClass;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /**
     * Get graph handle
     * @return The graph
     */
    public Graph getGr()
    {
        return gr;
    }

    /**
     * Get the vertex class.
     * @return Vertex class
     */
    public Class getvClass()
    {
        return vClass;
    }

    /**
     * Get the edge class.
     * @return  Edge class
     */
    public Class geteClass()
    {
        return eClass;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Db info dump">
    /**
     * Dump facts about the database.
     * Objects, Adjacency list, graph JSON data
     */
    public void dumpDb()
    {
        // What have we got?
        System.out.println("--------[Objects etc.]--------");
        DatabaseUtils.dumpGraph(gr);
        System.out.println("------------------------------\r\n");

        // Adjacency list (Database dump is not very interesting)
        System.out.println("-------[Adjacency list]-------");
        DatabaseUtils.adjListDump(gr, null, null);
        System.out.println("------------------------------\r\n");

        // Let's see the graph JSON data
        System.out.println("-------[Graph data]-------");
        D3GraphData d3gd = new D3GraphData();
        try
        {
            System.out.println("Graph JSON> " + d3gd.getAllData(gr) );
        }
        catch( Exception ex )
        {
            System.out.println( "  ...> Could not generate JSON: " + CrispIUtil.getStackTrace(ex ) );
        }
        System.out.println("--------------------------\r\n");
    }
    //</editor-fold>
}
