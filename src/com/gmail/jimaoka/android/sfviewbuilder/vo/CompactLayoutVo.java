package com.gmail.jimaoka.android.sfviewbuilder.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * CompactLayout の ValueObject
 * @author junji imaoka
 *
 */
public class CompactLayoutVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String imageItems;
	private String name;
	private String id;
	private String label;
	
	private List<CompactLayoutFieldItemVo> fieldItemsVos;
	
	/**
	 * コンストラクタ
	 * @param root
	 * @throws JSONException 
	 */
	public CompactLayoutVo(JSONObject root) throws JSONException{
		imageItems = root.getString("imageItems");
		name = root.getString("name");
		id = root.getString("id");
		label = root.getString("label");
		
		JSONArray fieldItems = root.getJSONArray("fieldItems");
		fieldItemsVos = new ArrayList<CompactLayoutFieldItemVo>();
		
		for(int i = 0; i < fieldItems.length(); i++){
			JSONObject fieldItem = (JSONObject) fieldItems.get(i);
			fieldItemsVos.add(new CompactLayoutFieldItemVo(fieldItem));
		}
	}

	public String getImageItems() {
		return imageItems;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public List<CompactLayoutFieldItemVo> getFieldItemsVos() {
		return fieldItemsVos;
	}
	
}
