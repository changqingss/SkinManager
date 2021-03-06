package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ProgressBar;
import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class IndeterminateDrawableAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof ProgressBar){
		    ProgressBar progressBar = (ProgressBar)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable indeterminateDrawable = SkinManager.getInstance().getDrawable(attrValueRefId);
		    	progressBar.setIndeterminateDrawable(indeterminateDrawable);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
