package com.gmail.jimaoka.android.sfviewbuilder.vo;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * LayoutComponent 詳細情報の ValueObject
 * @author junji imaoka
 *
 */
public class CompactLayoutComponentVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int displayLines;
	private int tabOrder;
	private String value;
	private String type;
	private CompactLayoutDetailVo detailVo;
	
	/**
	 * コンストラクタ
	 * @param root
	 * @throws JSONException 
	 */
	public CompactLayoutComponentVo(JSONObject root) throws JSONException{
		displayLines = root.getInt("displayLines");
		tabOrder = root.getInt("tabOrder");
		value = root.getString("value");
		type = root.getString("type");
		
		if(root.has("details")){
			detailVo = new CompactLayoutDetailVo(root.getJSONObject("details"));
		}
	}

	public int getDisplayLines() {
		return displayLines;
	}

	public int getTabOrder() {
		return tabOrder;
	}

	public String getValue() {
		return value;
	}

	public String getType() {
		return type;
	}

	public CompactLayoutDetailVo getDetailVo() {
		return detailVo;
	}
	
	
}
