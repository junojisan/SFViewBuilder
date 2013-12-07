package com.gmail.jimaoka.android.sfviewbuilder.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * LayoutComponent 情報の ValueObject
 * @author junji imaoka
 *
 */
public class CompactLayoutFieldItemVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String placeholder;
	private boolean editable;
	private boolean required;
	private String label;
	
	private List<CompactLayoutComponentVo> componentsVos;
	
	/**
	 * コンストラクタ
	 * @param root
	 * @throws JSONException 
	 */
	public CompactLayoutFieldItemVo(JSONObject root) throws JSONException{
		componentsVos = new ArrayList<CompactLayoutComponentVo>();
		
		placeholder = root.getString("placeholder");
		editable = root.getBoolean("editable");
		required = root.getBoolean("required");
		label = root.getString("label");
		
		JSONArray layoutComponents = root.getJSONArray("layoutComponents");
		componentsVos = new ArrayList<CompactLayoutComponentVo>();
		
		for(int i = 0; i < layoutComponents.length(); i++){
			JSONObject layoutComponent = (JSONObject) layoutComponents.get(i);
			componentsVos.add(new CompactLayoutComponentVo(layoutComponent));
		}
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public boolean isEditable() {
		return editable;
	}

	public boolean isRequired() {
		return required;
	}

	public String getLabel() {
		return label;
	}

	public List<CompactLayoutComponentVo> getComponentsVos() {
		return componentsVos;
	}
	
}
