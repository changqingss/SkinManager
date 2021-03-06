package com.coagent.skin.manager.loader;

import java.util.ArrayList;
import java.util.List;

import com.coagent.skin.manager.entity.DynamicAttr;
import com.coagent.skin.manager.entity.SkinAttr;
import com.coagent.skin.manager.entity.SkinItem;
import com.coagent.skin.manager.util.ListUtils;
import com.coagent.skin.manager.util.SkinLog;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;
import com.coagent.skin.manager.config.SkinConfig;
import com.coagent.skin.manager.entity.AttrFactory;

/**
 * Supply {@link SkinInflaterFactory} to be called when inflating from a LayoutInflater.
 * 
 * <p>Use this to collect the {skin:enable="true|false"} views availabled in our XML layout files.
 * 
 * @author Coagent-Geely  mjzhang zhucq
 */
public class SkinInflaterFactory implements Factory {
    private final static String TAG = "SkinInflaterFactory";
	/**
	 * Store the view item that need skin changing in the activity
	 */
	private List<SkinItem> mSkinItems = new ArrayList<SkinItem>();
	
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// if this is NOT enable to be skined , simplly skip it 
	    SkinLog.i("SkinInflaterFactory", "onCreateView: " + name);
		boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        if (!isSkinEnable){
        		return null;
        }
		
		View view = createView(context, name, attrs);
		
		if (view == null){
			return null;
		}
		
		parseSkinAttr(context, attrs, view);
		
		return view;
	}
	
	/**
     * Invoke low-level function for instantiating a view by name. This attempts to
     * instantiate a view class of the given <var>name</var> found in this
     * LayoutInflater's ClassLoader.
     * 
     * @param context 
     * @param name The full name of the class to be instantiated.
     * @param attrs The XML attributes supplied for this instance.
     * 
     * @return View The newly instantiated view, or null.
     */
	private View createView(Context context, String name, AttributeSet attrs) {
		View view = null;
		try {
			if (-1 == name.indexOf('.')){
				if ("View".equals(name)) {
					view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
				} 
				if (view == null) {
					view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
				} 
				if (view == null) {
					view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
				} 
			} else {
	            view = LayoutInflater.from(context).createView(name, null, attrs);
	        }
		} catch (Exception e) { 
			SkinLog.e("error while create " + name + " : " + e.getMessage());
			view = null;
		}
		return view;
	}

	/**
	 * Collect skin able tag such as background , textColor and so on
	 * 
	 * @param context
	 * @param attrs
	 * @param view
	 */
	private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        SkinLog.d(TAG, "parseSkinAttr()");
		List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
		for (int i = 0; i < attrs.getAttributeCount(); i++){
			String attrName = attrs.getAttributeName(i);
			String attrValue = attrs.getAttributeValue(i);
			
			SkinLog.d(TAG, "attrName = " + attrName
			        + ", attrValue = " + attrValue);
			/** ==================================== */
//			if(attrValue.startsWith("@")){
//			    try {
//			        int id = Integer.parseInt(attrValue.substring(1));
//			        String entryName = context.getResources().getResourceEntryName(id);
//			        String typeName = context.getResources().getResourceTypeName(id);
//			        if (DEBUG) SkinLog.d("SkinInflaterFactory", "@ id = " + id
//			                + ", entryName = " + entryName
//			                + ", typeName = " + typeName);
//			    } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                } catch (NotFoundException e) {
//                    e.printStackTrace();
//                }
//			}
//			if(attrValue.startsWith("?")){
//			    try {
//			        int id = Integer.parseInt(attrValue.substring(1));
//			        String entryName = context.getResources().getResourceEntryName(id);
//			        String typeName = context.getResources().getResourceTypeName(id);
//			        if (DEBUG) SkinLog.d("SkinInflaterFactory", "? id = " + id
//			                + ", entryName = " + entryName
//			                + ", typeName = " + typeName);
//			    } catch (NumberFormatException e) {
//			        e.printStackTrace();
//			    } catch (NotFoundException e) {
//			        e.printStackTrace();
//			    }
//			}
			/** ==================================== */
			if(!AttrFactory.isSupportedAttr(attrName)){
				continue;
			}
			
		    if(attrValue.startsWith("@")){
				try {
					int id = Integer.parseInt(attrValue.substring(1));
					String entryName = context.getResources().getResourceEntryName(id);
					String typeName = context.getResources().getResourceTypeName(id);
					SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
					SkinLog.d(TAG, "mSkinAttr = " + mSkinAttr);
					if (mSkinAttr != null) {
						viewAttrs.add(mSkinAttr);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (NotFoundException e) {
					e.printStackTrace();
				}
		    }
		}
		
		if(!ListUtils.isEmpty(viewAttrs)){
			SkinItem skinItem = new SkinItem();
			skinItem.view = view;
			skinItem.attrs = viewAttrs;
			SkinLog.d(TAG, "skinItem = " + skinItem);

			addSkinView(skinItem);
			
			if(SkinManager.getInstance().isExternalSkin()){
				skinItem.apply();
			}
		}
	}
	
	public void applySkin(){
		if(ListUtils.isEmpty(mSkinItems)){
			return;
		}
		for(SkinItem skinItem : mSkinItems){
			if(skinItem.view == null){
				continue;
			}
			skinItem.apply();
		}
	}
	
	public void dynamicAddSkinEnableView(Context context, View view, List<DynamicAttr> pDAttrs){	
	    SkinLog.d(TAG, "dynamicAddSkinEnableView(" + view + ")");
		List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
		SkinItem skinItem = new SkinItem();
		skinItem.view = view;
		
		for(DynamicAttr dAttr : pDAttrs){
			int id = dAttr.refResId;
			String entryName = context.getResources().getResourceEntryName(id);
			String typeName = context.getResources().getResourceTypeName(id);
			SkinAttr mSkinAttr = AttrFactory.get(dAttr.attrName, id, entryName, typeName);
			viewAttrs.add(mSkinAttr);
		}
		
		skinItem.attrs = viewAttrs;
		addSkinView(skinItem);
	}
	
	public void dynamicAddSkinEnableView(Context context, View view, String attrName, int attrValueResId){	
	    SkinLog.d(TAG, "dynamicAddSkinEnableView(" + view + ", " + attrName + ")");
		int id = attrValueResId;
		String entryName = context.getResources().getResourceEntryName(id);
		String typeName = context.getResources().getResourceTypeName(id);
		SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
		SkinItem skinItem = new SkinItem();
		skinItem.view = view;
		List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
		viewAttrs.add(mSkinAttr);
		skinItem.attrs = viewAttrs;
		addSkinView(skinItem);
	}
	
	private void addSkinView(SkinItem item){
		mSkinItems.add(item);
	}
	
	public void clean(){
		if(ListUtils.isEmpty(mSkinItems)){
			return;
		}
		
		for(SkinItem si : mSkinItems){
			if(si.view == null){
				continue;
			}
			si.clean();
		}
	}
}
