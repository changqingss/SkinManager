package com.coagent.skin.manager.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.coagent.skin.manager.listener.ILoaderListener;
import com.coagent.skin.manager.listener.ISkinLoader;
import com.coagent.skin.manager.listener.ISkinUpdate;
import com.coagent.skin.manager.util.SkinLog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.coagent.skin.manager.config.SkinConfig;

/**
 * Skin Manager Instance
 *
 * <ul>
 * <strong>global init skin manager, MUST BE CALLED FIRST ! </strong>
 * <li> {@link #init()} </li>
 * </ul>
 * <ul>
 * <strong>get single runtime instance</strong>
 * <li> {@link #getInstance()} </li>
 * </ul>
 * <ul>
 * <strong>attach a listener (Activity or fragment) to SkinManager</strong>
 * <li> {@link #onAttach(ISkinUpdate observer)} </li>
 * </ul>
 * <ul>
 * <strong>detach a listener (Activity or fragment) to SkinManager</strong>
 * <li> {@link #detach(ISkinUpdate observer)} </li>
 * </ul>
 * <ul>
 * <strong>load latest theme </strong>
 * <li> {@link #load()} </li>
 * <li> {@link #load(ILoaderListener callback)} </li>
 * </ul>
 * <ul>
 * <strong>load new theme with the giving skinPackagePath</strong>
 * <li> {@link #load(String skinPackagePath,ILoaderListener callback)} </li>
 * </ul>
 * 
 * @author Coagent-Geely mjzhang zhucq
 */
public class SkinManager implements ISkinLoader{
    private final static String TAG = "SkinManager";
	private static Object synchronizedLock = new Object();
	private static SkinManager instance;

	private List<ISkinUpdate> skinObservers;
	private Context mContext;
	private String skinPackageName;
	private Resources mResources;
	private String skinPath;
	private boolean isDefaultSkin = false;
	
	/**
	 * whether the skin being used is from external .skin file 
	 * @return is external skin = true
	 */
	public boolean isExternalSkin(){
		return !isDefaultSkin && mResources != null;
	}
	
	/**
	 * get current skin path
	 * @return current skin path
	 */
	public String getSkinPath() {
		return skinPath;
	}

	/**
	 * return a global static instance of {@link SkinManager}
	 * @return
	 */
	public static SkinManager getInstance() {
		if (instance == null) {
			synchronized (synchronizedLock) {
			    instance = new SkinManager();
			}
		}
		return instance;
	}
	
	public String getSkinPackageName() {
		return skinPackageName;
	}
	
	public Resources getResources(){
		return mResources == null ? mContext.getResources() : mResources;
	}
	
	private SkinManager() { }
	
	public void init(Context context, String skinPackagePath){
	    SkinLog.i(TAG, "init(" +skinPackagePath + ")");
		mContext = context.getApplicationContext();
		if (TextUtils.isEmpty(skinPackagePath)
                || TextUtils.equals(skinPackagePath, SkinConfig.DEFALT_SKIN)) {
		    isDefaultSkin = true;
		    mResources = mContext.getResources();
		} else {
		    load(skinPackagePath);
        }
	}
	
	public void load(String skinPackagePath){
	    load(skinPackagePath, null);
	}
	
	/**
	 * Load resources from apk in asyc task
	 * @param skinPackagePath path of skin apk
	 * @param callback callback to notify user
	 */
	public void load(String skinPackagePath, final ILoaderListener callback) {
	    SkinLog.i(TAG, "+++load(" + skinPackagePath + ")");
	    new AsyncTask<String, Void, Resources>() {
	        @Override
	        protected void onPreExecute() {
	            SkinLog.i(TAG, "onPreExecute()");
	            if (callback != null) {
	                callback.onStart();
	            }
	        };
	        @Override
	        protected Resources doInBackground(String... params) {
	            SkinLog.i(TAG, "doInBackground()");
	            try {
	                if (params.length == 1) {
	                    String skinPkgPath = params[0];

	                    if (TextUtils.isEmpty(skinPkgPath)
	                            || TextUtils.equals(skinPkgPath, SkinConfig.DEFALT_SKIN)) {
	                        isDefaultSkin = true;
	                        return mContext.getResources();
	                    }
	                    File file = new File(skinPkgPath); 
	                    if(file == null || !file.exists()){
	                        return null;
	                    }
	                    
	                    PackageManager mPm = mContext.getPackageManager();
	                    PackageInfo mInfo = mPm.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
	                    skinPackageName = mInfo.packageName;
	                    
	                    AssetManager assetManager;
//	                        assetManager = AssetManager.class.newInstance();
//	                        Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
//	                        addAssetPath.invoke(assetManager, skinPkgPath);
	                    assetManager = new AssetManager();
	                    assetManager.addAssetPath(skinPkgPath);
	                    
	                    Resources superRes = mContext.getResources();
	                    Resources skinResource = new Resources(assetManager,superRes.getDisplayMetrics(),superRes.getConfiguration());
	                    
	                    skinPath = skinPkgPath;
	                    isDefaultSkin = false;
	                    SkinLog.i(TAG, "doInBackground isDefaultSkin = false");
	                    return skinResource;
	                }
	                return null;
	            } catch (Exception e) {
	                e.printStackTrace();
	                return null;
	            }
	        };
	        @Override
	        protected void onPostExecute(Resources result) {
	            mResources = result;
	            if (mResources != null) {
	                notifySkinUpdate();
	                if (callback != null) callback.onSuccess();
	            }else{
	                isDefaultSkin = true;
	                if (callback != null) callback.onFailed();
	            }
	            SkinLog.i(TAG, "onPostExecute isDefaultSkin = " + isDefaultSkin);
	        };
	    }.execute(skinPackagePath);
	    SkinLog.i(TAG, "---load(" + skinPackagePath + ")");
	}
	
	@Override
	public void attach(ISkinUpdate observer) {
		if(skinObservers == null){
			skinObservers = new ArrayList<ISkinUpdate>();
		}
		if(!skinObservers.contains(skinObservers)){
			skinObservers.add(observer);
		}
	}

	@Override
	public void detach(ISkinUpdate observer) {
		if(skinObservers == null) return;
		if(skinObservers.contains(observer)){
			skinObservers.remove(observer);
		}
	}

	@Override
	public void notifySkinUpdate() {
		if(skinObservers == null) return;
		for(ISkinUpdate observer : skinObservers){
			observer.onThemeUpdate();
		}
	}
	
	public int getColor(int resId){
		int originColor = mContext.getResources().getColor(resId);
		if(mResources == null || isDefaultSkin){
			return originColor;
		}
		
		String resName = mContext.getResources().getResourceEntryName(resId);
		
		int trueResId = mResources.getIdentifier(resName, "color", skinPackageName);
		int trueColor = 0;
		
		try{
			trueColor = mResources.getColor(trueResId);
		}catch(NotFoundException e){
		    SkinLog.e(TAG, "getColor(color." + resName + "[0x" + Integer.toHexString(resId) + "]) = 0x" + Integer.toHexString(trueResId));
			e.printStackTrace();
			trueColor = originColor;
		}
		
		return trueColor;
	}
	
	@SuppressLint("NewApi")
	public Drawable getDrawable(int resId){
		Drawable originDrawable = mContext.getResources().getDrawable(resId);
		if(mResources == null || isDefaultSkin){
			return originDrawable;
		}
		String resName = mContext.getResources().getResourceEntryName(resId);
		
		int trueResId = mResources.getIdentifier(resName, "drawable", skinPackageName);
		
		Drawable trueDrawable = null;
		try{
			if(android.os.Build.VERSION.SDK_INT < 22){
				trueDrawable = mResources.getDrawable(trueResId);
//			} else{
//				trueDrawable = mResources.getDrawable(trueResId, null);
			}
		}catch(NotFoundException e){
		    SkinLog.e(TAG, "getDrawable(drawable." + resName + "[0x" + Integer.toHexString(resId) + "]) = 0x" + Integer.toHexString(trueResId));
			e.printStackTrace();
			trueDrawable = originDrawable;
		}
		
		return trueDrawable;
	}
	
	/**
	 * 加载指定资源颜色drawable,转化为ColorStateList，保证selector类型的Color也能被转换。</br>
	 * 无皮肤包资源返回默认主题颜色
	 * @author Coagent-Geely
	 * @param resId
	 * @return
	 */
	public ColorStateList convertToColorStateList(int resId) {
		SkinLog.e("attr", "convertToColorStateList");
		boolean isExtendSkin = true;
		
		if (mResources == null || isDefaultSkin) {
			isExtendSkin = false;
		}
		
		String resName = mContext.getResources().getResourceEntryName(resId);
		SkinLog.e("attr", "resName = " + resName);
		if (isExtendSkin) {
			int trueResId = mResources.getIdentifier(resName, "color", skinPackageName);
			SkinLog.e("attr", "convertToColorStateList(color." + resName + "[0x" + Integer.toHexString(resId) + "]) = 0x" + Integer.toHexString(trueResId));
			ColorStateList trueColorList = null;
			if (trueResId == 0) { // 如果皮肤包没有复写该资源，但是需要判断是否是ColorStateList
				try {
					ColorStateList originColorList = mContext.getResources().getColorStateList(resId);
					return originColorList;
				} catch (NotFoundException e) {
				    SkinLog.e("resName = " + resName + " NotFoundException");
					e.printStackTrace();
				}
			} else {
				try {
					trueColorList = mResources.getColorStateList(trueResId);
					SkinLog.e("attr", "trueColorList = " + trueColorList);
					return trueColorList;
				} catch (NotFoundException e) {
				    SkinLog.e("resName = " + resName + " NotFoundException");
					e.printStackTrace();
				}
			}
		} else {
			try {
				ColorStateList originColorList = mContext.getResources().getColorStateList(resId);
				return originColorList;
			} catch (NotFoundException e) {
			    SkinLog.e("resName = " + resName + " NotFoundException");
				e.printStackTrace();
			}

		}

		int[][] states = new int[1][1];
		return new ColorStateList(states, new int[] { mContext.getResources().getColor(resId) });
	}
}