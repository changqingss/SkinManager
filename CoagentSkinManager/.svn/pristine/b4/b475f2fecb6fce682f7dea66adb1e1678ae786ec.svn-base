package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CompoundButton;

/**
 * @author Coagent-Geely mjzhang
 */
public class ButtonAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof CompoundButton){
		    CompoundButton compoundButton = (CompoundButton) view;
			switch (attrValueType) {
            case DRAWABLE:
                Drawable button = SkinManager.getInstance().getDrawable(attrValueRefId);
                compoundButton.setButtonDrawable(button);
                SkinLog.i("attr", "apply: ButtonAttr - " + attrValueType);
                break;
            default:
                break;
            }
		}
	}
}
