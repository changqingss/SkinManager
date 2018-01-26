package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

/**
 * @author Coagent-Geely mjzhang
 */
public class TextCursorDrawableAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
			TextView textView = (TextView)view;
			switch (attrValueType) {
            case DRAWABLE:
                Drawable drawableCursor = SkinManager.getInstance().getDrawable(attrValueRefId);
                textView.setCursorDrawable(drawableCursor);
                SkinLog.i("attr", "apply: TextCursorDrawableAttr - " + attrValueType);
                break;
            default:
                break;
            }
		}
	}
}
