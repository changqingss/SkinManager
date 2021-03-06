package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class DrawableRightAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
            TextView textView = (TextView)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable drawableRight = SkinManager.getInstance().getDrawable(attrValueRefId);
//		    	drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
//		    	textView.setCompoundDrawables(null, null, drawableRight, null);
		    	textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
