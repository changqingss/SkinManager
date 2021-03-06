package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class DrawableStartAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
            TextView textView = (TextView)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable drawableStart = SkinManager.getInstance().getDrawable(attrValueRefId);
//		    	drawableStart.setBounds(0, 0, drawableStart.getMinimumWidth(), drawableStart.getMinimumHeight());
//		    	textView.setCompoundDrawablesRelative(drawableStart, null, null, null);
		    	textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableStart, null, null, null);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
