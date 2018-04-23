//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012 Chris Powell.
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
package eu.discoveri.astroninja.example.edge;

import eu.discoveri.astroninja.exception.NullVertexException;
import eu.discoveri.astroninja.edge.EdgeDirected;
import eu.discoveri.astroninja.edge.GraphEdge;
import eu.discoveri.astroninja.vertex.Vertex;
import javax.persistence.Entity;
import eu.discoveri.astroninja.example.vertex.FollowsTag;


/**
 * A Follows edge (directed).
 *
 * @author Chris Powell
 */
@Entity
@EdgeDirected(directed=true)
public class Follows extends GraphEdge
{
  private FollowsTag qualifier;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Follows(){}
    public Follows( Vertex v0, Vertex v1 )
            throws NullVertexException
    {
        this( v0,v1, null );
    }
    public Follows( Vertex v0, Vertex v1, FollowsTag t )
            throws NullVertexException
    {
        super( v0,v1 );
        qualifier = t;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    public FollowsTag getQualifier() {  return qualifier;  }

    public void setQualifier( FollowsTag qualifier ) {  this.qualifier = qualifier;  }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "Follows";
    }
    //</editor-fold>
}
