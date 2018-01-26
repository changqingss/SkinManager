package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

/**
 * @author Coagent-Geely zhucq
 */
public class DividerAttr extends SkinAttr {
	public int dividerHeight = 1;
	@Override
	public void apply(View view) {
		if(view instanceof ListView){
			ListView listView = (ListView)view;
			switch (attrValueType) {
            case COLOR:
                int color = SkinManager.getInstance().getColor(attrValueRefId);
                ColorDrawable colorDrawable = new ColorDrawable(color);
                listView.setDivider(colorDrawable);
                listView.setDividerHeight(dividerHeight);
                SkinLog.i("attr", "apply: DividerAttr - " + attrValueType);
                break;
            case DRAWABLE:
                listView.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
                SkinLog.i("attr", "apply: DividerAttr - " + attrValueType);
                break;
            default:
                break;
            }
		}
	}
}
