/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.visualise;
import eu.discoveri.astroninja.edge.GraphEdge;
import eu.discoveri.astroninja.vertex.GraphVertex;
import eu.discoveri.astroninja.vertex.Vertex;

import it.uniroma1.dis.wsngroup.gexf4j.core.EdgeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.Graph;
import it.uniroma1.dis.wsngroup.gexf4j.core.Mode;
import it.uniroma1.dis.wsngroup.gexf4j.core.Node;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.Attribute;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeClass;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeList;
import it.uniroma1.dis.wsngroup.gexf4j.core.data.AttributeType;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.StaxGraphWriter;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.data.AttributeListImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.viz.ColorImpl;
import it.uniroma1.dis.wsngroup.gexf4j.core.viz.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class GEXFutils
{
    /**
     * Create the GEXF entities from a (discoveri) Graph and output a GEXF file
     * 
     * @param gr Discoveri graph
     * @param startV The initial (highlighted) vertex, if any
     * @param description Description for this graph
     * @param outGEXF Output GEXF file
     */
    public static void writeGEXF( eu.discoveri.astroninja.graph.Graph gr, Vertex startV, String description, String outGEXF )
    {
        Gexf gexf = new GexfImpl();
        Calendar date = Calendar.getInstance();
        
        // Some colours (improve this)
        Color red = new ColorImpl(255,0,0);
        Color black = new ColorImpl(255,255,255);
        Color pink = new ColorImpl(255,204,229);
        Color flesh = new ColorImpl(255,204,204);
        Color lightgreen = new ColorImpl(204,255,204);
        Color bluegrey = new ColorImpl(204,204,255);
        Color green = new ColorImpl(0,255,0);
        Color blue = new ColorImpl(0,0,255);
        Color redbrown = new ColorImpl(204,0,0);
        Color bluegreen = new ColorImpl(51,153,255);
        Color darkgrey = new ColorImpl(128,128,128);
        Color yellow = new ColorImpl(255,255,0);
        Color orange = new ColorImpl(255,153,51);
        Color violet = new ColorImpl(255,51,255);
        
        // GEXF Metadata
        gexf.getMetadata()
                .setLastModified(date.getTime())
                .setCreator("Discoveri OU")
                .setDescription("A Web network");
        gexf.setVisualization(true);

        Graph graph = gexf.getGraph();
        graph.setDefaultEdgeType(EdgeType.UNDIRECTED).setMode(Mode.STATIC);

        // Additional entity attributes
        AttributeList attrList = new AttributeListImpl(AttributeClass.NODE);
        graph.getAttributeLists().add(attrList);

        Attribute attUrl = attrList.createAttribute("0", AttributeType.STRING, "url");
        Attribute attIndegree = attrList.createAttribute("1", AttributeType.FLOAT, "indegree");
        Attribute attFrog = attrList.createAttribute("2", AttributeType.BOOLEAN, "frog")
                .setDefaultValue("true");

        // Get Graph entities
        System.out.println("...> Writing GEXF file: " +outGEXF);
        Map<GraphVertex,Node> nodeMap = new HashMap<>();
        
        // Get all edges
        List<GraphEdge> es = gr.getAllEdges();
        // Get all vertices
        List<GraphVertex> vs = gr.getAllVertices();
        
        // Build nodes
        vs.forEach((v) -> {
            if( v.equals(startV) )
            {
                nodeMap.put( v, graph.createNode(v.getId()).setLabel(v.getVName()).setColor(red).setSize(21.0f) );
            }
            else
            {
                nodeMap.put( v, graph.createNode(v.getId()).setLabel(v.getVName()).setColor(bluegrey).setSize(20.0f) );
            }
        });
        
        // Add edges
        nodeMap.forEach((v,n) -> {
            for( eu.discoveri.astroninja.edge.Edge e : v.getEmanatingEdges(null,null) )
            {
                Vertex v1 = e.getV1();
                n.connectTo(graph.getNode(v1.getId())).setColor(blue);
            }
        });

        // Output a GEXF file
        StaxGraphWriter graphWriter = new StaxGraphWriter();
        File f = new File(outGEXF);
        Writer out;
        try
        {
            out =  new FileWriter(f, false);
            graphWriter.writeToStream(gexf, out, "UTF-8");
            System.out.println(f.getAbsolutePath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
