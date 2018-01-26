package com.coagent.skin.manager.base;

import java.util.List;

import com.coagent.skin.manager.entity.DynamicAttr;
import com.coagent.skin.manager.listener.IDynamicNewView;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

public class SkinFragment extends Fragment implements IDynamicNewView{
	private IDynamicNewView mIDynamicNewView;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			mIDynamicNewView = (IDynamicNewView)activity;
		}catch(ClassCastException e){
			mIDynamicNewView = null;
		}
	}

	@Override
	public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
		if(mIDynamicNewView == null){
			throw new RuntimeException("IDynamicNewView should be implements !");
		}else{
			mIDynamicNewView.dynamicAddView(view, pDAttrs);
		}
	}
	
}
