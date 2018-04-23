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
package eu.discoveri.astroninja.example.entity;

import eu.discoveri.astroninja.exception.FailedEntityDeleteException;
import eu.discoveri.astroninja.exception.FailedEntityUpdateException;
import eu.discoveri.astroninja.exception.FailedEntityWriteException;
import eu.discoveri.astroninja.graph.GraphI;
import eu.discoveri.astroninja.graph.GraphObjectImpl;

import eu.discoveri.astroninja.example.vertex.Person;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 * A Phone class
 *
 * @author Chris Powell
 */
@Entity
//<editor-fold defaultstate="collapsed" desc="NamedQueries">
@NamedQueries({
    @NamedQuery(
        name="Phone.findAll",
        query="SELECT p FROM Phone p"
        ),
})
//</editor-fold>
public class Phone extends GraphObjectImpl
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    private String name;    // An arbitrary name
    private String type;    // LNDLINE, MOBILE, ...
    private String number;  // The number (any format)
    // Owner
    @ManyToOne @JoinColumn(name="ENTITY_ID")
    private Person  owner;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Phone(){}
    public Phone(String name, String type, String number)
    {
        this.name = name;
        this.type = type;
        this.number = number;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        // Test length first then...
        //  ...validate with /((?:\+|00)[17](?: |\-)?|(?:\+|00)[1-9]\d{0,2}(?: |\-)?|(?:\+|00)1\-\d{3}(?: |\-)?)?(0\d|\([0-9]{3}\)|[1-9]{0,3})(?:((?: |\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\-)[0-9]{3}(?: |\-)[0-9]{4})|([0-9]{7}))/g
        // final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
        // if (!pattern.matcher(input).matches()) {
        //          throw new IllegalArgumentException("Invalid String");
        this.number = number;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finders">
    /**
     * Find all phones
     * @return
     */
    public static List<Phone> findAll()
    {
        TypedQuery<Phone> query =
          GraphI.getDb().createNamedQuery("Phone.findAll", Phone.class);

        List<Phone> lp = query.getResultList();
        if( lp == null )
            {  return new ArrayList<>();  }
        else
            {  return lp;  }
    }

    /**
     * Find a phone by its number
     * @param number
     * @return
     */
    public static Phone findByNumber( String number )
    {
        Query q = GraphI.getDb().createQuery("SELECT p FROM Phone p WHERE p.number='" +number+ "'");
        return (Phone)q.getSingleResult();
    }

    /**
     * Find a phone by its Id
     * @param id
     * @return
     */
    public static Phone findByID( long id )
    {
// V/JPA pretty naff on superclasses, TYPE() in NamedQuery etc.etc.
//        TypedQuery<Person> q =
//            GraphI.getDb().createNamedQuery("AbstractPerson.findByUUID", Person.class).setParameter(uuid,uuid);
        Query q = GraphI.getDb().createQuery("SELECT p FROM Phone p WHERE p.id=" +id );
        return (Phone)q.getSingleResult();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="C(R)UD">
    /**
     * Write the Person's Phone (attached via addPhone()).  Assumes Person already
     * exists on database
     * @param p The Phone owner
     */
    public Phone writePhonePerson( Person p )
            throws FailedEntityWriteException
    {
        // Overwriting simple single entity write
        EntityManager dbc = GraphI.getDb();     // Database cache

        try
        {
            dbc.getTransaction().begin();
            dbc.persist( this );
            dbc.merge( p );
            dbc.getTransaction().commit();
        }
        catch( EntityExistsException eex )
        {
            throw new FailedEntityWriteException( "Phone already exists: " +this.toString(), eex );
        }
        catch( Exception ex )
        {
            throw new FailedEntityWriteException( "Write PersonPhone failed, phone: " +this.toString(), ex );
        }
        finally {
            if( dbc.getTransaction().isActive() )
            { dbc.getTransaction().rollback();  }
        }

        return this;
    }

    /** {@inheritDoc}
     */
    public void deletePhone()
            throws FailedEntityDeleteException
    {
        try
        {
            this.deleteEntity();
        }
        catch( FailedEntityDeleteException fedx )
        {
            throw new FailedEntityDeleteException( "Can't delete Phone " +this.toString(), fedx );
        }
    }

    /** {@inheritDoc}
     */
    public Phone updatePhone()
            throws FailedEntityUpdateException
    {
        Phone phone = null;

        try
        {
            phone = (Phone)this.updateEntity();
        }
        catch( FailedEntityUpdateException feux )
        {
            throw new FailedEntityUpdateException( "Can't update Phone " +this.toString(), feux );
        }

        return phone;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString()
    {
        return "Name: " +this.name+ ", Type: " +this.type+ ", Num: " +this.number;
    }
    //</editor-fold>
}
