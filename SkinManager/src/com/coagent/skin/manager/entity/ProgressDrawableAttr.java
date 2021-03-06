package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ProgressBar;
import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class ProgressDrawableAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof ProgressBar){
		    ProgressBar progressBar = (ProgressBar)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable progressDrawable = SkinManager.getInstance().getDrawable(attrValueRefId);
		    	progressBar.setProgressDrawable(progressDrawable);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
