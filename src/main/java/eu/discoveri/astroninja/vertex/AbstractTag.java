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

import javax.persistence.Entity;

/**
 * Tag system.  An extension of Topic Maps.
 * @see <a href="http://en.wikipedia.org/wiki/Topic_Maps">Topic Maps Wiki</a>
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
@Entity
public abstract class AbstractTag extends GraphVertex implements Tag
{
    //<editor-fold defaultstate="collapsed" desc="Constants and Fields">
    private long count = 0;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    protected AbstractTag(){}
    public AbstractTag( String vname )
    {
        super( vname );
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /** {@inheritDoc}
     */
    @Override
    public long getCount() {  return count;  }

    /** {@inheritDoc}
     */
    @Override
    public long incrCount()
    {
        return ++count;
    }

    /** {@inheritDoc}
     */
    @Override
    public void setCount(long count) {  this.count = count;  }
    //</editor-fold>
}
