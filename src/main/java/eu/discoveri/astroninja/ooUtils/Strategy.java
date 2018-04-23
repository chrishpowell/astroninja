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


/**
 * Strategy interface.  Represents an operation that can be performed on a set of
 * objects, that is any set of Vertex or Edge (or mixture).
 *
 * For example:
 * <code>
 * for each Object o in set
 *   strategy.visit(o);
 * </code>
 *
 * The implementation of visit() is class dependent.
 * @see <a href="http://java.dzone.com/articles/design-patterns-strategy">Strategy design pattern</a>
 * @see <a href="http://java.dzone.com/articles/design-patterns-visitor">Visitor design pattern</a>
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public interface Strategy
{
    /**
     * Object visit operation.
     * @param o the (graph) Object being visited
     */
    void visit( Object o );

    /**
     * Has visit operation completed?
     * @return <code>true</code> if visit is over, <code>false</code> otherwise
     */
    boolean isDone();
}
