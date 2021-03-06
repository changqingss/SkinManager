package com.coagent.skin.manager.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import com.coagent.skin.manager.loader.SkinManager;

/**
 * @author Coagent-Geely mjzhang
 */
public class ThumbAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof SeekBar){
		    SeekBar seekBar = (SeekBar)view;
		    switch (attrValueType) {
		    case DRAWABLE:
		    	Drawable thumb = SkinManager.getInstance().getDrawable(attrValueRefId);
		    	seekBar.setThumb(thumb);
		    	break;
	        default:
	            break;
	        }
		}
	}
}
