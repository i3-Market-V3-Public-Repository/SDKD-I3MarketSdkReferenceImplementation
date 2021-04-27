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
