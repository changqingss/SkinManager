package com.coagent.skin.manager.base;

import java.util.List;

import com.coagent.app.BaseActivity;
import com.coagent.skin.manager.entity.DynamicAttr;
import com.coagent.skin.manager.listener.IDynamicNewView;
import com.coagent.skin.manager.listener.ISkinUpdate;
import com.coagent.skin.manager.loader.SkinManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.coagent.skin.manager.loader.SkinInflaterFactory;

/**
 * Base Activity for development
 * 
 * <p>NOTICE:<br> 
 * You should extends from this if you what to do skin change
 * 
 * @author Coagent-Geely
 */
public class SkinActivity extends BaseActivity implements ISkinUpdate, IDynamicNewView{
	private final static String TAG = "SkinActivity";
	/**
	 * Whether response to skin changing after create
	 */
	private boolean isResponseOnSkinChanging = true;
	
	private SkinInflaterFactory mSkinInflaterFactory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() isExternalSkin() = " + SkinManager.getInstance().isExternalSkin());
		mSkinInflaterFactory = new SkinInflaterFactory();
		getLayoutInflater().setFactory(mSkinInflaterFactory);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
		SkinManager.getInstance().attach(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
		SkinManager.getInstance().detach(this);
		mSkinInflaterFactory.clean();
	}

	@Override
	protected void onThemeChanged(String theme) {
	    super.onThemeChanged(theme);
	    Log.d(TAG, "onThemeChanged(" + theme + ")");
	    SkinManager.getInstance().load(getCurrentTheme());
	}
	/**
	 * dynamic add a skin view 
	 * @param view
	 * @param attrName
	 * @param attrValueResId
	 */
	protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId){	
		mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
	}
	
	protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs){	
		mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
	}
	
	final protected void enableResponseOnSkinChanging(boolean enable){
		isResponseOnSkinChanging = enable;
	}

	@Override
	public void onThemeUpdate() {
		if(!isResponseOnSkinChanging){
			return;
		}
		mSkinInflaterFactory.applySkin();
	}

	@Override
	public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
		mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
	}
}
