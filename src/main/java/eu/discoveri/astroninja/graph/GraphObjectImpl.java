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
package eu.discoveri.astroninja.graph;

import eu.discoveri.astroninja.config.AllConfig;
import eu.discoveri.astroninja.exception.FailedEntityDeleteException;
import eu.discoveri.astroninja.exception.FailedEntityUpdateException;
import eu.discoveri.astroninja.exception.FailedEntityWriteException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;

import java.io.IOException;
//import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The implementation of basic graph objects and the associated REST/JSON
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
//@SequenceGenerator(name="seq", initialValue=0, allocationSize=250)
@Entity
public class GraphObjectImpl implements GraphObject
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @Id @Column(name="ENTITY_ID", nullable=false) @GeneratedValue
    @JsonIgnore
    private long    id;     // 64bit, max: 2^63-1, approx. 9 1/4 quintillion

    // Dates
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date    createDate = null;
    @Temporal(TemporalType.DATE)
    private Date    updateDate = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Create a new object with date Now
     */
    public GraphObjectImpl()
    {
        createDate = new Date();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Id methods">
    /** {@inheritDoc}
     */
    @Override
    @JsonProperty("id")
    public String getId() {  return String.valueOf(id);  }

    /** {@inheritDoc}
     */
    @Override
    public long getID() {  return id;  }

    /**
     * Get the ID from the JSON
     *
     * @TODO: Deprecated?  createJsonParser()
     * @param idname
     * @param json
     * @return the id as long
     * @throws IOException
     * @throws NumberFormatException
     */
    public static long extractID( String idname, String json )
            throws IOException,NumberFormatException
    {
        JsonParser jp = new JsonFactory().createJsonParser(json);
        ObjectMapper mapper = new ObjectMapper();

        jp.nextToken();
        while( jp.nextToken() != JsonToken.END_OBJECT )
        {
            String fieldname = jp.getCurrentName();
            if( idname.equals(fieldname) )
            {
                jp.nextToken();
                return Long.valueOf(jp.getText());
            }
        }

        return 0;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="equals and hashcode">
    /**
     * Are these graph objects equal?
     * Equality here is <i>shallow</i>, based on the object db id.  If you take
     * a copy of a subclass of GraphObject, alter an attribute of that subclass,
     * then the ids will still match.  Override this equals() if you want deep
     * comparisons.
     *
     * @param other
     * @return <code>true</code> if objects are equal (as defined), <code>false</code> otherwise
     */
    @Override
    public boolean equals( Object other )
    {
        if( this.getClass().isInstance(other) )  // Can we cast successfully?
        {
            if( id == ((GraphObjectImpl)other).getID() ) {  return true;  }
        }
        return false;
    }

    /**
     * A hashcode specific to Graph objects
     * @return Hash for this object id
     */
    @Override
    public int hashCode()
    {
        return (int)((id >> 32) ^ (id));
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Dates">
    /** {@inheritDoc}
     */
    @Override
    @JsonProperty("createDate")
    public Date getCreateDate() {  return createDate;  }

    /** {@inheritDoc}
     */
    @Override
    @JsonProperty("updateDate")
    public Date getUpdateDate() {  return updateDate;  }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="JSON Methods">
    /** {@inheritDoc}
     */
    @Override
    public String toJSON()
            throws JsonProcessingException
    {
        return getOM().writeValueAsString(this);
    }

    /**
     * Deserialize the JSON into class clazz
     *
     * @param json
     * @param clazz
     * @return Class deserialized from JSON
     * @throws IOException
     */
    public Class<? extends GraphObjectImpl> fromJSON( String json, Class<? extends GraphObjectImpl> clazz )
            throws IOException
    {
        return getOM().readValue( json, clazz.getClass() );
    }

    /**
     * Gets the OM singleton instance
     *
     * @return The ObjectMapper singleton instance.
     */
    public static ObjectMapper getOM()
    {
        return OMSingletonHolder.instance;
    }

    /**
     * A private static class for holding OM singleton instance.
     */
    private static class OMSingletonHolder
    {
        private static final OMSingletonHolder om;
        private static ObjectMapper instance;
        static
        {
            try
                {  om = new OMSingletonHolder();  }
            catch( Exception e )
                {  throw new ExceptionInInitializerError(e);  }
        }

        private OMSingletonHolder()
                throws Exception
        {
            instance = new ObjectMapper();
            // Can skip unwanted/unknown properties in JSON strings when de-serializing
            if( !AllConfig.baseconfig().getConfig().getJson().getFailUnknownProps() )
            {
                instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="C(R)UD">
    /** {@inheritDoc}
     */
    @Override
    public GraphObject writeEntity()
            throws FailedEntityWriteException
    {
        EntityManager dbc = GraphI.getDb();      // Database cache

        try
        {
            dbc.getTransaction().begin();
            dbc.persist( this );
            dbc.getTransaction().commit();
        }
        catch( EntityExistsException eex )
        {
            throw new FailedEntityWriteException( "Entity already exists: " +this.toString(), eex );
        }
        catch( Exception ex )
        {
            throw new FailedEntityWriteException( this.toString(), ex );
        }
        finally {
            if( dbc.getTransaction().isActive() )
            { dbc.getTransaction().rollback();  }
        }

        return this;
    }

    /** {@inheritDoc}
     */
    @Override
    public void deleteEntity()
            throws FailedEntityDeleteException
    {
        EntityManager dbc = GraphI.getDb();      // Database cache

        try
        {
            dbc.getTransaction().begin();
            dbc.remove( this );
            dbc.getTransaction().commit();
        }
        catch( Exception ex )
        {
            throw new FailedEntityDeleteException( this.toString(), ex );
        }
        finally {
            if( dbc.getTransaction().isActive() )
            { dbc.getTransaction().rollback();  }
        }
    }

    /** {@inheritDoc}
     */
    @Override
    public GraphObject updateEntity()
            throws FailedEntityUpdateException
    {
        updateDate = new Date();                // Set update date
        EntityManager dbc = GraphI.getDb();     // Database cache

        // Write the update
        try
        {
            dbc.getTransaction().begin();
            dbc.merge( this );
            dbc.getTransaction().commit();
        }
        catch( Exception ex )
        {
            throw new FailedEntityUpdateException( this.toString(), ex );
        }
        finally {
            if( dbc.getTransaction().isActive() )
            { dbc.getTransaction().rollback();  }
        }

        return this;
    }
    //</editor-fold>
}
