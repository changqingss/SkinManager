package com.coagent.skin.manager.entity;

import android.text.TextUtils;

/**
 * @author Coagent-Geely mjzhang zhucq
 */
public class AttrFactory {
    public static enum Attr {
        BACKGROUND("background"),
        TEXT_COLOR("textColor"),
        TEXT_COLOR_HINT("textColorHint"),
        TEXT_COLOR_HINT_LIGHT("textColorHighlight"),
        TEXT_CURSOR_DRAWABLE("textCursorDrawable"),
        LIST_SELECTOR("listSelector"),
        DIVIDER("divider"),
        SRC("src"),
        DRAWABLE_LEFT("drawableLeft"),
        DRAWABLE_TOP("drawableTop"),
        DRAWABLE_RIGHT("drawableRight"),
        DRAWABLE_BOTTOM("drawableBottom"),
        DRAWABLE_START("drawableStart"),
        DRAWABLE_END("drawableEnd"),
        PROGRESS_DRAWABLE("progressDrawable"),
        INDETERMINATE_DRAWABLE("indeterminateDrawable"),
        THUMB("thumb"),
        BUTTON("button"),
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
        case TEXT_COLOR_HINT:
            mSkinAttr = new TextColorHintAttr();
            break;
        case TEXT_COLOR_HINT_LIGHT:
            mSkinAttr = new TextColorHighlightAttr();
            break;
        case TEXT_CURSOR_DRAWABLE:
            mSkinAttr = new TextCursorDrawableAttr();
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
        case DRAWABLE_LEFT:
        	mSkinAttr = new DrawableLeftAttr();
        	break;
        case DRAWABLE_TOP:
            mSkinAttr = new DrawableTopAttr();
            break;
        case DRAWABLE_RIGHT:
            mSkinAttr = new DrawableRightAttr();
            break;
        case DRAWABLE_BOTTOM:
            mSkinAttr = new DrawableBottomAttr();
            break;
        case DRAWABLE_START:
            mSkinAttr = new DrawableStartAttr();
            break;
        case DRAWABLE_END:
            mSkinAttr = new DrawableEndAttr();
            break;
        case PROGRESS_DRAWABLE:
            mSkinAttr = new ProgressDrawableAttr();
            break;
        case INDETERMINATE_DRAWABLE:
            mSkinAttr = new IndeterminateDrawableAttr();
            break;
        case THUMB:
            mSkinAttr = new ThumbAttr();
            break;
        case BUTTON:
            mSkinAttr = new ButtonAttr();
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
