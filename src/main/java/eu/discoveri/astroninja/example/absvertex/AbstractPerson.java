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
package eu.discoveri.astroninja.example.absvertex;

import eu.discoveri.astroninja.exception.FailedEntityDeleteException;
import eu.discoveri.astroninja.exception.FailedEntityUpdateException;
import eu.discoveri.astroninja.exception.FailedEntityWriteException;
import eu.discoveri.astroninja.graph.GraphI;
import eu.discoveri.astroninja.vertex.GraphVertex;

import eu.discoveri.astroninja.example.entity.AccState;
import eu.discoveri.astroninja.example.entity.Phone;
import eu.discoveri.astroninja.example.vertex.Person;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 * Simple Person.
 *
 * @author Chris Powell
 */
@Entity
@Inheritance
//<editor-fold defaultstate="collapsed" desc="NamedQueries">
@NamedQueries({
    @NamedQuery(
        name="AbstractPerson.findAll",
        query="SELECT p FROM AbstractPerson p ORDER BY p.lastName, p.firstName ASC"
        ),
    @NamedQuery(
        name="AbstractPerson.findAllACtInAct",
        query="SELECT p FROM AbstractPerson p ORDER BY p.lastName, p.firstName ASC"
        ),
    @NamedQuery(
        name="AbstractPerson.findByLastName",
        query="SELECT p FROM AbstractPerson p WHERE p.lastName = :lastName"
        ),
    @NamedQuery(
        name="AbstractPerson.findByPalias",
        query="SELECT p FROM AbstractPerson p WHERE p.palias = :palias"
        ),
    @NamedQuery(
        name="AbstractPerson.findByID",
        query="SELECT ap FROM AbstractPerson ap WHERE ap.id = :id"
        )
})
//</editor-fold>
public abstract class AbstractPerson extends GraphVertex implements Person
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    // Names
    private String      firstName;
    private String      lastName;
    private String      palias;
    private String      accStateStr;    // State of this Person (ACTIVE,...) as String
    private String      midName1;
    private String      midName2;

    // Phones
    @OneToMany(orphanRemoval=true,mappedBy="owner",cascade=CascadeType.ALL)
    private List<Phone> phones;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    protected AbstractPerson() {}
    public AbstractPerson( String firstName, String lastName, String midName1, String midName2, String palias )
    {
        super( lastName+","+firstName );
        this.midName1 = midName1;
        this.midName2 = midName2;
        this.firstName = firstName;
        this.lastName = lastName;
        this.palias = palias;
        this.accStateStr = AccState.ACTIVE.name();  // Stringed (yep, Versant again)
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    @Override
    public String getFirstName() {  return firstName;  }

    @Override
    public Person setFirstName(String firstName) {  this.firstName = firstName; return this; }

    @Override
    public String getMidName1() {  return midName1;  }

    @Override
    public Person setMidName1(String midName1) {  this.midName1 = midName1; return this; }

    @Override
    public String getMidName2() {  return midName2;  }

    @Override
    public Person setMidName2(String midName2) {  this.midName2 = midName2; return this; }

    @Override
    public String getLastName() {  return lastName;  }

    @Override
    public Person setLastName(String lastName) {  this.lastName = lastName; return this; }

    @Override
    public String getPalias() {  return palias;  }

    @Override
    public Person setPalias(String palias) {  this.palias = palias; return this; }

    @Override
    public List<Phone> getPhones() {  return phones;  }

    @Override
    public void setPhones(List<Phone> phones) {  this.phones = phones;  }

    @Override
    public void assocPhone( Phone ph )
    {
        // May not be any phones
        if( phones == null )
        {
            phones = new ArrayList<>();
        }
        phones.add( ph );
    }

    @Override
    public AccState getAccState() {  return AccState.valueOf(AccState.class,accStateStr);  }

    @Override
    public void setAccState(AccState accState) {  this.accStateStr = accState.name();  }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finders">
    public static List<Person> findAllPP()
    {
        TypedQuery<Person> query =
          GraphI.getDb().createNamedQuery("AbstractPerson.findAll", Person.class);

        List<Person> lp = query.getResultList();
        if( lp == null )
            {  return new ArrayList<>();  }
        else
            {  return lp;  }
    }

    public static List<Person> findByLastName( String lastName )
    {
// @TODO: Fix NamedQueries
//        TypedQuery<Person> q =
//          GraphI.getDb().createNamedQuery("AbstractPerson.findByLastName", Person.class).setParameter(lastName, lastName);
        Query q = GraphI.getDb().createQuery("SELECT p FROM AbstractPerson p WHERE p.lastName='" +lastName+ "'");

        List<Person> lp = q.getResultList();
        if( lp == null )
            {  return new ArrayList<>();  }
        else
            {  return lp;  }
    }

    public static Person findByPalias( String palias )
    {
        Query q = GraphI.getDb().createQuery("SELECT p FROM AbstractPerson p WHERE p.palias='" +palias+ "'");
        return (Person)q.getSingleResult();
    }

    public static Person findByID( long id )
    {
        TypedQuery<Person> q =
            GraphI.getDb().createNamedQuery("AbstractPerson.findByID", Person.class).setParameter("id",id);

        return (Person)q.getSingleResult();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="C(R)UD">
    /** {@inheritDoc}
     */
    @Override
    public Person writePerson()
            throws FailedEntityWriteException
    {
        return (Person)this.writeEntity();  // GraphObject write
    }

    /** {@inheritDoc}
     */
    @Override
    public void deletePerson()
            throws FailedEntityDeleteException
    {
        this.deleteEntity();
    }

    /** {@inheritDoc}
     */
    @Override
    public Person updatePerson()
            throws FailedEntityUpdateException
    {
        return (Person)this.updateEntity();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString()
    {
        return firstName+ " " +lastName+ " [" +palias+ "]";
    }
    //</editor-fold>
}
