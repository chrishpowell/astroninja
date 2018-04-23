//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2018 Chris Powell, Discoveri OU
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

import eu.discoveri.astroninja.ooUtils.Strategy;


/**
 * Digraph.  A directed graph
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Digraph extends Graph
{
    boolean isStronglyConnected();
    void topologicalOrderTraversal( Strategy visitor );
}
