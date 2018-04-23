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
 * PrePostVisitor interface.  Defines operations for visit 'workflow'.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface PrePostStrategy
{
    void preVisit( Object o );
    void inVisit( Object o );
    void postVisit( Object o );
    boolean isDone();
}
