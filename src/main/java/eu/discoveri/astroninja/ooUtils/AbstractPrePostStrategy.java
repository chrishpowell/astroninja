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
 * AbstracPrePostVisitor
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public abstract class AbstractPrePostStrategy implements PrePostStrategy
{
    @Override
    public void preVisit( Object o ){}

    @Override
    public void inVisit( Object o ){}

    @Override
    public void postVisit( Object o ){}

    @Override
    public boolean isDone()
	{ return false; }
}
