package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class DrawableTopAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
		    TextView textView = (TextView)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable drawableTop = SkinManager.getInstance().getDrawable(attrValueRefId);
//		    	drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
//		    	textView.setCompoundDrawables(null, drawableTop, null, null);
		    	textView.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
