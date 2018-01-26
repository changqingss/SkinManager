package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class DrawableLeftAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
		    TextView textView = (TextView)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable drawableLeft = SkinManager.getInstance().getDrawable(attrValueRefId);
//		    	drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
//		    	textView.setCompoundDrawables(drawableLeft, null, null, null);
		    	textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
