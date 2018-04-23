package eu.discoveri.astroninja.visualise;

import eu.discoveri.astroninja.graph.Graph;
import eu.discoveri.astroninja.graph.GraphI;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Provides the JSON data for D3 graphing
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class D3GraphServlet extends HttpServlet
{
  private Graph        gr;
  private D3GraphData  d3gd;

    public D3GraphServlet( GraphI gr, D3GraphData d3gd )
    {
        this.gr = gr;
        this.d3gd = d3gd;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print( d3gd.getAllData(gr) );
    }
}
