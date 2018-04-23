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
package eu.discoveri.astroninja.vertex;

import java.io.Serializable;

/**
 * Interface for Tags
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public interface Tag extends Vertex
{
    /**
     * Get number of times 'used'.  NB: A Tag does not get deleted if its count
     * goes to zero.
     * @return Count of references to this Tag
     */
    long getCount();

    /**
     * Increment Tag reference count.
     * @return Current reference count
     */
    long incrCount();

    /**
     * Set count of Tag use.
     */
    void setCount( long count );
}
