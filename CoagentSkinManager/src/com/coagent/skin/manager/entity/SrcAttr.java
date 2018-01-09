package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class SrcAttr extends SkinAttr {
	@Override
	public void apply(View view) {
	    if(view instanceof ImageView){
	        ImageView imageView = (ImageView) view;
	        switch (attrValueType) {
	        case COLOR:
	            int color = SkinManager.getInstance().getColor(attrValueRefId);
	            ColorDrawable colorDrawable = new ColorDrawable(color);
	            imageView.setImageDrawable(colorDrawable);
	            SkinLog.i("attr", "apply: SrcAttr - " + attrValueType);
	            break;
	        case DRAWABLE:
	            Drawable drawable = SkinManager.getInstance().getDrawable(attrValueRefId);
	            imageView.setImageDrawable(drawable);
	            imageView.setImageDrawable(SkinManager.getInstance().getDrawable(attrValueRefId));
	            SkinLog.i("attr", "apply: BackgroundAttr - " + attrValueType + ": "+ drawable);
	            SkinLog.i("attr", this.attrValueRefName + " 是否可变换状态? : " + drawable.isStateful());
	            break;
	        default:
	            break;
	        }
	    }
	}
}
