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

import eu.discoveri.astroninja.graph.GraphObject;


/**
 * The Weight interface, an interface to allow users to 'weight' edges.  For example,
 * an edge could be ascribed a value denoting the 'quality' of the connection:
 * HIGH / MEDIUM / LOW or 0.0 -> 9.0.  Weights should be a (distance) metric and
 * conform to the triangle inequality rule to allow paths to be correctly
 * calculated.  See the Wiki entry:
 * <a href="http://en.wikipedia.org/wiki/Metric_%28mathematics%29">
 * Metric/distance function</a> and {@link Weight#add Weight.add}.
 * <p>
 * Nota Bene: There should be a one-to-one relationship between Weight and Edge
 * classes.  Mixing Weight types on an Edge class could lead to anomalous results
 * and even runtime failures.
 * </p>
 *
 * @see AbstractWeight AbstractWeight
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Weight extends GraphObject, Comparable
{
    /**
     * Gets weight of type defined by (user) implementation. Eg: enum(Low, Med, High)
     * Primitives will need to be boxed.
     *
     * @return Weight value
     */
    Object getWeight();

    /**
     * Sets weight of type defined by (user) implementation. Eg: enum(Low, Med, High)
     */
    void setWeight( Object weight );

    /**
     * How do Weight objects 'add'?  This is for analysis along shortest paths etc.
     *
     * @param o The weight type, eg: one of enum(Low, Med, High).
     * @return An object of type defined by implementation.  Eg: Low+Med => High.
     */
    Object add( Object o );

    /**
     * Used for comparing to the maximum value (or not exceeding same) defined
     * for this class. Eg: returns High from enum(Low, Med, High).
     *
     * @return Maximum Weight value
     */
    Object getMaxWeight();

    /**
     * Used for comparing to the minimum value (or not being inferior to same)
     * as defined for this class. Eg: returns Low from enum(Low, Med, High).
     *
     * @return Minimum Weight value
     */
    Object getMinWeight();
}
