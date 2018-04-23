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
import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.edge.GraphEdge;
import eu.discoveri.astroninja.graph.Graph;
import eu.discoveri.astroninja.utils.CrispIUtil;
import eu.discoveri.astroninja.vertex.Vertex;
import eu.discoveri.astroninja.vertex.GraphVertex;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Output D3 format for graph visualization
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class D3GraphData
{
    //<editor-fold defaultstate="collapsed" desc="Statics">
    /**
     * Escape strings to change the single quote char
     *
     * @param value
     * @return escaped string
     */
    private static String singleQuoteEscape(String value)
    {
        return value.replaceAll("'", "`");
    }

    /**
     * Write a Vertex to given builder given a group
     * This format suits javascript D3 visualization
     *
     * @param builder given output
     * @param vertex vertex to write out
     * @param group group to give vertex
     */
    private static void writeVertex( StringBuilder builder, Vertex vertex, Long group )
             throws JsonProcessingException
    {
        builder.append( String.format("{\"json\":%s,\"type\":\"%s\",\"id\":%d,\"group\":%d}", vertex.toJSON(), vertex.getClass().getSimpleName(), vertex.getID(), group) );
    }

    /**
     * Write an Edge to given builder given a value
     * This format suits javascript D3 visualization
     *
     * @param builder given output
     * @param edge edge to write out
     * @param originPosition Vertex id of origin
     * @param targetPosition Vertex id of target
     * @param value value to give edge
     */
    private static void writeEdge( StringBuilder builder, Edge edge, Long originPosition, Long targetPosition, Long value )
    {
        builder.append(String.format("{\"name\":\"%s\",\"source\":%d,\"target\":%d,\"id\":%d,\"value\":%d}", edge.getClass().getSimpleName(), originPosition, targetPosition, edge.getID(), value));
    }

    /**
     * Write a Group to given builder
     * This format suits javascript D3 visualization
     *
     * @param builder given output
     * @param group group given to one or more vertices
     */
    private static void writeGroup(StringBuilder builder, VGroup group)
    {
        builder.append(String.format("{\"name\":\"%s\"}", group.getName()));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inner Classes">
    /**
     * Private class to represent the vertex group items
     */
    private class VGroup
    {
      private String name;
      private Long pos;

        public VGroup( String name, Long pos )
        {
            this.name = name;
            this.pos = pos;
        }
        public String getName()
        {
            return name;
        }
        public Long getPos()
        {
            return pos;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Generators">
    /**
     * Entire Db dump in D3 visualisation format
     * @todo Re-write for performance
     * @return as JSON
     */
    public String getAllData( Graph gr )
            throws JsonProcessingException
    {
      // Builder for a JSON string
      StringBuilder builder = new StringBuilder();
      // Store position of each vertex
      Map<Long, Long> idToPosition = new HashMap<>();
      // Store each group (vertex type)
      Map<String, Long> grpToPosition = new HashMap<>();
      Map<Long, VGroup> vGroup = new HashMap<>();

        // Header of JSON
        builder.append("{\"nodes\":[");

        // Get all Vertices and store position and add to JSON
        List<GraphVertex> vs = gr.getAllVertices();
        Long countId = 0L;
        Long countVType = 0L;
        String delimiter = "";

        for( Vertex eachVertex : vs )
        {
            // Store id position
            idToPosition.put(eachVertex.getID(), countId);
            // Store type position
            if (grpToPosition.get(eachVertex.getClass().getSimpleName()) == null)
            {
                grpToPosition.put(eachVertex.getClass().getSimpleName(), countVType);
                vGroup.put(countVType,
                        new VGroup(eachVertex.getClass().getSimpleName(), countVType));
                countVType++;
            }

            // Write out to JSON
            Long vPosition = grpToPosition.get(eachVertex.getClass().getSimpleName());
            builder.append(delimiter);
            delimiter = ",";
            writeVertex( builder, eachVertex, vPosition );

            countId++;
        }

        // Division between nodes and groups in JSON
        builder.append("],\"groups\":[");
        delimiter = "";
        for (int eachGroup=0; eachGroup < vGroup.size(); eachGroup++)
        {
            VGroup vg = vGroup.get(new Long(eachGroup));
            builder.append(delimiter);
            delimiter = ",";
            writeGroup(builder, vg);
        }

        // Division between groups and links in JSON
        builder.append("],\"links\":[");

        // Store position of each value (edge type)
        Map<String, Long> etypeToPosition = new HashMap<>();

        // Get All Edges and add to JSON after translating vertex ids to positions
        List<GraphEdge> es = gr.getAllEdges();
        Long countEType = 0L;
        delimiter = "";
        for( Edge eachEdge : es )
        {
            Long originPosition = idToPosition.get(eachEdge.getV0().getID());
            Long targetPosition = idToPosition.get(eachEdge.getV1().getID());
            // Store type position
            if( etypeToPosition.get(eachEdge.getClass().getSimpleName()) == null )
            {
                etypeToPosition.put(eachEdge.getClass().getSimpleName(), countEType);
                countEType++;
            }
            Long ePosition = etypeToPosition.get(eachEdge.getClass().getSimpleName());
            builder.append(delimiter);
            delimiter = ",";
            writeEdge( builder, eachEdge, originPosition, targetPosition, 5L );
        }

        // Footer of JSON
        builder.append("]}");

        // String can be 2G in length - which should be enough...
        return builder.toString();
    }
    //</editor-fold>
}
