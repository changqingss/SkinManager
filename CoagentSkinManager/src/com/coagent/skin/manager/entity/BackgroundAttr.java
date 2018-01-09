package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.graphics.drawable.Drawable;
import android.view.View;

public class BackgroundAttr extends SkinAttr {
	@Override
	public void apply(View view) {
	    switch (attrValueType) {
        case COLOR:
            view.setBackgroundColor(SkinManager.getInstance().getColor(attrValueRefId));
            SkinLog.i("attr", "apply: BackgroundAttr - " + attrValueType);
            break;
        case DRAWABLE:
            Drawable bg = SkinManager.getInstance().getDrawable(attrValueRefId);
            view.setBackground(bg);
            SkinLog.i("attr", "apply: BackgroundAttr - " + attrValueType + ": "+ bg);
            SkinLog.i("attr", this.attrValueRefName + " 是否可变换状态? : " + bg.isStateful());
            break;
        default:
            break;
        }
	}
}
