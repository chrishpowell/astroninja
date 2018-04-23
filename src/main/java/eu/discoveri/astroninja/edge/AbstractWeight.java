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
package eu.discoveri.astroninja.edge;

import eu.discoveri.astroninja.graph.GraphObjectImpl;
import javax.persistence.Entity;


/**
 * A Graph Object implemented by users for edges to be able to have a generalized
 * attribute to modify path access (eg: traversals).  Extend this abstract class
 * and implement Comparable {@link java.lang.Comparable#compareTo compareTo} and
 * {@link Weight Weight} suitably.
 * 
 * @see IntegerWeight IntegerWeight
 *
 * @author Chris Powell, CPgraph Ltd.
 */
@Entity
public abstract class AbstractWeight extends GraphObjectImpl implements Weight{}
