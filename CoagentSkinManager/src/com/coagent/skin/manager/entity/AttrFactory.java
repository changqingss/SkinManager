package com.coagent.skin.manager.entity;

import android.text.TextUtils;

public class AttrFactory {
    public static enum Attr {
        BACKGROUND("background"),
        TEXT_COLOR("textColor"),
        LIST_SELECTOR("listSelector"),
        DIVIDER("divider"),
        SRC("src"),
        NONE("not support"); // 不支持的Attr

        private final String name;
        private Attr(String n) {
            name = n;
        }
        public static Attr byName(String name) {
            for (Attr attr : Attr.values()) {
                if(TextUtils.equals(attr.name, name)){
                    return attr;
                }
            }
            return NONE;
        }
    }
    public static enum ResType {
        COLOR("color"),
        DRAWABLE("drawable"),
        DIMEN("dimen"),
        STRING("string"),
        ID("id"),
        NONE("not support"); // 不支持的ResType
        
        private final String type;
        private ResType(String t) {
            type = t;
        }
        public static ResType byName(String type) {
            for (ResType resType : ResType.values()) {
                if(TextUtils.equals(resType.type, type)){
                    return resType;
                }
            }
            return NONE;
        }
    }

	public static SkinAttr get(String attrName, int attrValueRefId, String attrValueRefName, String typeName){
		SkinAttr mSkinAttr = null;
		switch (Attr.byName(attrName)) {
        case BACKGROUND:
            mSkinAttr = new BackgroundAttr();
            break;
        case TEXT_COLOR:
            mSkinAttr = new TextColorAttr();
            break;
        case LIST_SELECTOR:
            mSkinAttr = new ListSelectorAttr();
            break;
        case DIVIDER:
            mSkinAttr = new DividerAttr();
            break;
        case SRC:
            mSkinAttr = new SrcAttr();
            break;
        case NONE:
        default:
            return null;
        }
		
		mSkinAttr.attrName = attrName;
		mSkinAttr.attrValueRefId = attrValueRefId;
		mSkinAttr.attrValueRefName = attrValueRefName;
		mSkinAttr.attrValueType = ResType.byName(typeName);
		return mSkinAttr;
	}
	
	/**
	 * Check whether the attribute is supported
	 * @param attrName
	 * @return true : supported <br> false: not supported
	 */
	public static boolean isSupportedAttr(String attrName){
	    if (Attr.NONE == Attr.byName(attrName)) {
            return false;
        }
	    return true;
	}
}
