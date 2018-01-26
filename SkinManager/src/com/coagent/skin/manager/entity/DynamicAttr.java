package com.coagent.skin.manager.entity;

public class DynamicAttr {
	/**
	 * attr name , defined from {@link AttrFactory} :<br>
	 * should be
	 * <li> background
	 * <li> textColor
	 * <li> listSelector
	 * <li> divider
	 * <li> src
	 * <li> drawableLeft
	 * <li> ... <br>
	 */
	public String attrName;

	/**
	 * resource id from default context , eg: "R.drawable.app_bg"
	 */
	public int refResId;

	/**
	 * @param attrName defined from {@link AttrFactory.Attr} :<br>
     * should be
     * <li> background
     * <li> textColor
     * <li> src
     * <li> drawableLeft
     * <li> ... <br>
	 * @param refResId resource id from default context , eg: "R.drawable.app_bg"
	 */
	public DynamicAttr(String attrName, int refResId){
		this.attrName = attrName;
		this.refResId = refResId;
	}
}
