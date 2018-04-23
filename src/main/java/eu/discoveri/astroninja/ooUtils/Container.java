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
package eu.discoveri.astroninja.ooUtils;

import java.io.Serializable;


/**
 * Container: An object containing other objects
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Container extends Comparable<Container>, Serializable
{
    /**
     * Get count of contained objects.
     * @return Count of contained objects
     */
    long getCount();

    /**
     * Does container have objects?
     * @return <code>true</code> if Container is not empty, <code>false</code> otherwise
     */
    boolean isEmpty();

    /**
     * Empty this Container
     */
    void purge();

    /**
     * Used for visiting entities on traversals. Part of Strategy/Visitor pattern,
     * see {@link Strategy Strategy}.  Allows the visitor to run some action over
     * the graph element.
     * @param visitor
     */
    void accept( Strategy visitor );

    /**
     * Get an Iterable for the objects of this Container
     * @return Iterable for objects of this Container
     */
    Iterable getIterator();
}
