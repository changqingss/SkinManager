package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.view.View;
import android.widget.TextView;

/**
 * @author Coagent-Geely mjzhang
 */
public class TextColorHintAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof TextView){
			TextView textView = (TextView)view;
			switch (attrValueType) {
            case COLOR:
                textView.setHintTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
                SkinLog.i("attr", "apply: TextColorHintAttr - " + attrValueType);
                break;
            default:
                break;
            }
		}
	}
}
