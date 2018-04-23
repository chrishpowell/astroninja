//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012-2013 Chris Powell, CPgraph Ltd..
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
package eu.discoveri.astroninja.config;

import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Configuration via <code>configfile</code> in class Const. Configuration is
 * split into 3 main sections: {@link Database Database}, {@link Config Config(General)}
 * and {@link UIConfig UI}. Individual configurations are read via relevant classes in
 * this <code>config</code> subpackage.  Configurations are versioned.
 *
 * Configuration.xml is of the format:
<br/><pre>
&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;BaseConfig xmlns="http://config.chrishpowell.com/bases"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            version="0.9"&gt;
    &lt;Database&gt;
        &lt;DefPersistUnit&gt;discoveri_PU&lt;/DefPersistUnit&gt;
        :
    :
    &lt;/Database&gt;
    &lt;Config&gt;
    :
    &lt;/Config&gt;
    :
&lt;/BaseConfig&gt;
</pre>
 *
 * Based on the simpleframework: <a href="http://www.simpleframework.org/">Simple</a>
 *
 * @author Chris Powell, Discoveri OU
 */
public class AllConfig
{
    /**
     * Get the configurations from the configuration file (eg: configuration.xml)
     */
    public static BaseConfig baseconfig()
            throws Exception
    {
        Serializer serializer = new Persister();
        File config = new File(System.getProperty("configfile",Const.CONFIGFILE));

        return serializer.read( BaseConfig.class, config );
    }
}
