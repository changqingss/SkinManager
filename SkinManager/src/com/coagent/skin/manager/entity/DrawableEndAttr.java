package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class DrawableEndAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
            TextView textView = (TextView)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable drawableEnd = SkinManager.getInstance().getDrawable(attrValueRefId);
//		    	drawableEnd.setBounds(0, 0, drawableEnd.getMinimumWidth(), drawableEnd.getMinimumHeight());
//		    	textView.setCompoundDrawablesRelative(null, null, drawableEnd, null);
		    	textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableEnd, null);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
