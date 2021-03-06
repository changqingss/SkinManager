package com.coagent.skin.manager.entity;

import com.coagent.skin.manager.loader.SkinManager;
import com.coagent.skin.manager.util.SkinLog;

import android.view.View;
import android.widget.AbsListView;

/**
 * @author Coagent-Geely mjzhang zhucq
 */
public class ListSelectorAttr extends SkinAttr {
	@Override
	public void apply(View view) {
		if(view instanceof AbsListView){
			AbsListView absListView = (AbsListView)view;
			switch (attrValueType) {
            case COLOR:
                absListView.setSelector(SkinManager.getInstance().getColor(attrValueRefId));
                SkinLog.i("attr", "apply: ListSelectorAttr - " + attrValueType);
                break;
            case DRAWABLE:
                absListView.setSelector(SkinManager.getInstance().getDrawable(attrValueRefId));
                SkinLog.i("attr", "apply: ListSelectorAttr - " + attrValueType);
                break;
            default:
                break;
            }
		}
	}
}
