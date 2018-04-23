//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2013-2014 Chris Powell.
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

import eu.discoveri.astroninja.exception.FailedEntityDeleteException;
import eu.discoveri.astroninja.exception.FailedEntityUpdateException;
import eu.discoveri.astroninja.exception.FailedEntityWriteException;
import eu.discoveri.astroninja.vertex.Vertex;

import com.fasterxml.jackson.core.JsonProcessingException;

import eu.discoveri.astroninja.example.entity.AccState;
import eu.discoveri.astroninja.example.entity.Phone;

import java.util.List;


/**
 * Person
 * @author Chris Powell
 */
public interface Person extends Vertex
{
    @Override
    String toJSON() throws JsonProcessingException;

    String getFirstName();

    Person setFirstName(String firstName);

    String getMidName1();

    Person setMidName1(String midName1);

    String getMidName2();

    Person setMidName2(String midName2);

    String getLastName();

    Person setLastName(String lastName);

    String getPalias();

    Person setPalias(String palias);

    List<Phone> getPhones();

    void setPhones(List<Phone> phones);

    /**
     * Associate a Phone with this Person
     * @param ph
     */
    void assocPhone(Phone ph);

    AccState getAccState();

    void setAccState(AccState accState);

    /**
     * Write a new Person on to database
     * @return this Person
     * @throws FailedEntityWriteException
     */
    Person writePerson()
            throws FailedEntityWriteException;

    /**
     * Update a Person on the database
     * @return  this Person
     * @throws FailedEntityUpdateException
     */
    Person updatePerson()
            throws FailedEntityUpdateException;

    /**
     * Delete a Person from the database
     * @throws FailedEntityDeleteException
     */
    void deletePerson()
            throws FailedEntityDeleteException;
}
