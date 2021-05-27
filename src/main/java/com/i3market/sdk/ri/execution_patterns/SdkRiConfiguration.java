/*
 * Copyright 2019 Atos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.i3market.sdk.ri.execution_patterns;

import java.util.Properties;

/* Classe to manage Sdk RI configuration */
public class SdkRiConfiguration {
	
	private Properties properties = null;
	private static SdkRiConfiguration instance = null;

	/** Private constructor */
	private SdkRiConfiguration (){
	    this.properties = new Properties();
	    try{
	        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(SdkRiConstants.PATH_CONFFILE));
	    }catch(Exception ex){
	        ex.printStackTrace();
	    }
	}   

	/** Creates the instance is synchronized to avoid multithreads problems */
	private synchronized static void createInstance () {
	    if (instance == null) { 
	        instance = new SdkRiConfiguration ();
	    }
	}

	/** Get the properties instance. Uses singleton pattern */
	public static SdkRiConfiguration getInstance(){
	    // Uses singleton pattern to guarantee the creation of only one instance
	    if(instance == null) {
	        createInstance();
	    }
	    return instance;
	}

	/** Get a property of the property file */
	public String getProperty(String key){
	    String result = null;
	    if(key !=null && !key.trim().isEmpty()){
	        result = this.properties.getProperty(key);
	    }
	    return result;
	}

	/** Override the clone method to ensure the "unique instance" requeriment of this class */
	public Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException();
	}

}
