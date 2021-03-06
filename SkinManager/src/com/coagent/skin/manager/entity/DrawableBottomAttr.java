package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class DrawableBottomAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
            TextView textView = (TextView)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable drawableBottom = SkinManager.getInstance().getDrawable(attrValueRefId);
//		    	drawableBottom.setBounds(0, 0, drawableBottom.getMinimumWidth(), drawableBottom.getMinimumHeight());
//		    	textView.setCompoundDrawables(null, null, null, drawableBottom);
		    	textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawableBottom);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
