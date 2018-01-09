package com.coagent.skin.manager.util;

import android.util.Log;

/**
 * Log Utils for debug
 * 
 * @author Coagent-Geely
 */
public class SkinLog {

	private static final boolean DEBUG  = true;
	private static final String  TAG    = "SkinLoader";
	
    private SkinLog() {
        throw new AssertionError();
    }
    
    public static void i(String msg){
    	if(DEBUG){
    		Log.i(TAG, msg);
    	}
    }
    
    public static void d(String msg){
    	if(DEBUG){
    		Log.d(TAG, msg);
    	}
    }
    
    public static void w(String msg){
    	if(DEBUG){
    		Log.w(TAG, msg);
    	}
    }
    
    public static void e(String msg){
    	if(DEBUG){
    		Log.e(TAG, msg);
    	}
    }
    
    public static void i(String tag, String msg){
    	if(DEBUG){
    		Log.i(tag, msg);
    	}
    }
    
    public static void d(String tag, String msg){
    	if(DEBUG){
    		Log.d(tag, msg);
    	}
    }
    
    public static void w(String tag, String msg){
    	if(DEBUG){
    		Log.w(tag, msg);
    	}
    }
    
    public static void e(String tag, String msg){
    	if(DEBUG){
    		Log.e(tag, msg);
    	}
    }
}
