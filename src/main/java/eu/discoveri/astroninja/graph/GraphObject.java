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
package eu.discoveri.astroninja.graph;

import eu.discoveri.astroninja.exception.FailedEntityDeleteException;
import eu.discoveri.astroninja.exception.FailedEntityUpdateException;
import eu.discoveri.astroninja.exception.FailedEntityWriteException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.Serializable;
import java.util.Date;


/**
 * Any object in the graph
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface GraphObject extends Serializable
{
    //<editor-fold defaultstate="collapsed" desc="Get info">
    /**
     * The 'stringyfied' Id for this object.
     * @return The string representation of the Id
     */
    String getId();

    /**
     * The Id for this object.
     * @return Id of this object (once persisted)
     */
    long getID();

    /**
     * Output JSON for this instance (attributes).
     * @return JSON string of the attributes of this object
     * @throws JsonProcessingException
     */
    String toJSON() throws JsonProcessingException;

    /**
     * Get the creation date of this object.
     * @return Creation date
     */
    Date getCreateDate();

    /**
     * Get the last update date.
     * @return Date this object last updated
     */
    Date getUpdateDate();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CRUD">
    /**
     * Write the entity to the database.
     * @return The graph object
     * @throws FailedEntityWriteException
     */
    public GraphObject writeEntity()
            throws FailedEntityWriteException;

    /**
     * Delete this entity off the database.
     * @throws FailedEntityDeleteException
     */
    public void deleteEntity()
            throws FailedEntityDeleteException;

    /**
     * Update this entity on the database.
     * @return The graph object
     * @throws FailedEntityUpdateException
     */
    public GraphObject updateEntity()
            throws FailedEntityUpdateException;
    //</editor-fold>
}
