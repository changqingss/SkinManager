package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.view.View;
import android.widget.TextView;

public class TextColorAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
			TextView textView = (TextView)view;
			switch (attrValueType) {
            case COLOR:
                textView.setTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
                SkinLog.i("attr", "apply: TextColorAttr - " + attrValueType);
                break;
            default:
                break;
            }
		}
	}
}
