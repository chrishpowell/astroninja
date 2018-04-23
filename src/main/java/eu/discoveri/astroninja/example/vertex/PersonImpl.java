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
package eu.discoveri.astroninja.example.vertex;

import eu.discoveri.astroninja.graph.GraphI;
import eu.discoveri.astroninja.example.absvertex.AbstractPerson;
import eu.discoveri.astroninja.example.entity.AccState;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.persistence.*;


/**
 * Person is a particular Owner, who is a public individual.  This class
 * encapsulates a 'View' class used for controlled deserializing of JSON into a
 * Person on updates.
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Entity
//<editor-fold defaultstate="collapsed" desc="Named queries">
@NamedQueries({
    @NamedQuery(
        name="Person.findAll",
        query="SELECT p FROM PersonImpl p WHERE p.accStateStr IN (:accStateStr) ORDER BY p.lastName, p.firstName ASC"
        ),
    @NamedQuery(
        name="Person.count",
        query="SELECT count(p) FROM PersonImpl p"
        )
})
//</editor-fold>
public class PersonImpl extends AbstractPerson
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    // List of returned people
    volatile static List<Person> lp = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public PersonImpl(){}
    public PersonImpl( String firstName, String lastName, String palias )
    {
        this( firstName, lastName, "","", palias );
    }
    public PersonImpl( String firstName, String lastName, String midName1, String midName2, String palias )
    {
        super( firstName, lastName, midName1, midName2, palias );      // Vertex name => lastName,firstName
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finders">
    /**
     * Find all Persons with given list of states (AccState).  If list(0) is
     * AccState.ANY then the whole set of AccState enum is assumed.
     *
     * @param accStates List of AccState enums
     * @return ResultSet List<Person>
     */
    public static List<Person> findAll( List<AccState> accStates )
    {
        StringBuilder inList = new StringBuilder();

        accStates.forEach((as) -> {
            inList.append("'").append(as.name()).append("',");
        });
        inList.append("'*'");         // VJPA has to be kept happy...

        return findAllQuery( inList );
    }

    /**
     * Find all Persons with given list of states (AccState).  If list(0) is
     * AccState.ANY then the whole set of AccState enum is assumed.
     *
     * @param accState List of AccState enums
     * @return ResultSet List<Person>
     */
    public static List<Person> findAll( AccState accState )
    {
        StringBuilder inList = new StringBuilder();

        if( accState == AccState.ANY )
        {
            EnumSet.allOf(AccState.class).forEach((as) -> {
                inList.append("'").append(as.name()).append("',");
            });
            inList.append("'*'");         // VJPA has to be kept happy...
        }
        else
            inList.append("'").append(accState.name()).append("'");

        return findAllQuery( inList );
    }

    /*
     * Quoted list of states separated by commas.  Eg: 'ACTIVE','BLOCKED'
     */
    private static List<Person> findAllQuery( StringBuilder inList )
    {
//        TypedQuery<Person> query =
//          GraphI.getDb().createNamedQuery("Person.findAll", Person.class);
//        List<String> ss = new ArrayList<>();
//        ss.add(AccState.ACTIVE.name()); ss.add(AccState.BLOCKED.name());
//        String inList[] = {"ACTIVE","BLOCKED"};
//        query.setParameter("accStateStr", inList);

        /*
         * @todo: Versant *really* need to get their NamedQuery stuff fixed...
         */
        Query q = GraphI.getDb().createQuery("SELECT p FROM PersonImpl p WHERE p.accStateStr IN (" +inList.toString()+ ") ORDER BY p.lastName, p.firstName ASC");

        lp = q.getResultList();
        if( lp == null )
            {  return new ArrayList<>();  }
        else
            {  return lp;  }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    /**
     * Simple toString
     *
     * @return lastname, firstname, alias
     */
    @Override
    public String toString()
    {
        return getVName() + " (" +getPalias()+ ")";
    }
    //</editor-fold>
}
