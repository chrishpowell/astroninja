//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2013-2014 Chris Powell, CPgraph Ltd..
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
package eu.discoveri.astroninja.algorithms;

import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.edge.Weight;
import eu.discoveri.astroninja.vertex.Vertex;
import eu.discoveri.astroninja.graph.Graph;

import java.util.Map;
import java.util.LinkedHashMap;


/**
 * A path between vertices.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class GraphPath
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    private Graph               graph;
    private Vertex              startVertex, endVertex;
    private Map<Edge,Weight>    edgePath;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Create the path (preferably shortest)
     *
     * @param graph
     * @param startVertex
     * @param endVertex
     * @param edgePath list of Edges
     */
    public GraphPath( Graph graph, Vertex startVertex, Vertex endVertex, LinkedHashMap<Edge,Weight> edgePath )
    {
        this.graph = graph;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgePath = edgePath;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /**
     * Get the graph for this path.
     * @return the graph
     */
    public Graph getGraph() {  return graph;  }

    /**
     * Get the Start vertex of this path.
     * @return The start vertex of the path
     */
    public Vertex getStartVertex() {  return startVertex;  }

    /**
     * Get the End vertex of this path.
     * @return The end vertex of the path
     */
    public Vertex getEndVertex() {  return endVertex;  }

    /**
     * Get the edges of this path (normally the shortest).
     * @return List of Edges of this path
     */
    public Map<Edge,Weight> getEdgePath() {  return edgePath;  }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString()
    {
        return "GraphPath{" + "startVertex=" + startVertex + ", endVertex=" + endVertex + ", edgePath=" + edgePath + '}';
    }
    //</editor-fold>
}
