package com.gmail.jimaoka.android.sfviewbuilder.vo;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class DescribeGlobalVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String label;
	private String keyPrefix;
	private String labelPlural;
	private boolean custom;
	private boolean layoutable;
	private boolean activateable;
	private String urlSobject;
	private String urlDescribe;
	private String urlRowTemplate;
	private boolean searchable; 
	private boolean updateable; 
	private boolean createable; 
	private boolean deprecatedAndHidden; 
	private boolean customSetting; 
	private boolean deletable; 
	private boolean feedEnabled; 
	private boolean mergeable; 
	private boolean queryable; 
	private boolean replicateable; 
	private boolean retrieveable; 
	private boolean undeletable; 
	private boolean triggerable;
	
	/**
	 * コンストラクタ
	 * @param jObj
	 * @throws JSONException 
	 */
	public DescribeGlobalVo(JSONObject jObj) throws JSONException{
		name = jObj.getString("name");
		label = jObj.getString("label");
		keyPrefix = jObj.getString("keyPrefix");
		labelPlural = jObj.getString("labelPlural");
		custom = jObj.getBoolean("custom");
		layoutable = jObj.getBoolean("layoutable");
		activateable = jObj.getBoolean("activateable");
		urlSobject = jObj.getJSONObject("urls").getString("sobject");
		urlDescribe = jObj.getJSONObject("urls").getString("describe");
		urlRowTemplate = jObj.getJSONObject("urls").getString("rowTemplate");
		searchable = jObj.getBoolean("searchable");
		updateable = jObj.getBoolean("updateable");
		createable = jObj.getBoolean("createable");
		deprecatedAndHidden = jObj.getBoolean("deprecatedAndHidden");
		customSetting = jObj.getBoolean("customSetting");
		deletable = jObj.getBoolean("deletable");
		feedEnabled = jObj.getBoolean("feedEnabled");
		mergeable = jObj.getBoolean("mergeable");
		queryable = jObj.getBoolean("queryable");
		replicateable = jObj.getBoolean("replicateable");
		retrieveable = jObj.getBoolean("retrieveable");
		undeletable = jObj.getBoolean("undeletable");
		triggerable = jObj.getBoolean("triggerable");
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public String getLabelPlural() {
		return labelPlural;
	}

	public boolean isCustom() {
		return custom;
	}

	public boolean isLayoutable() {
		return layoutable;
	}

	public boolean isActivateable() {
		return activateable;
	}

	public String getUrlSobject() {
		return urlSobject;
	}

	public String getUrlDescribe() {
		return urlDescribe;
	}

	public String getUrlRowTemplate() {
		return urlRowTemplate;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public boolean isUpdateable() {
		return updateable;
	}

	public boolean isCreateable() {
		return createable;
	}

	public boolean isDeprecatedAndHidden() {
		return deprecatedAndHidden;
	}

	public boolean isCustomSetting() {
		return customSetting;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public boolean isFeedEnabled() {
		return feedEnabled;
	}

	public boolean isMergeable() {
		return mergeable;
	}

	public boolean isQueryable() {
		return queryable;
	}

	public boolean isReplicateable() {
		return replicateable;
	}

	public boolean isRetrieveable() {
		return retrieveable;
	}

	public boolean isUndeletable() {
		return undeletable;
	}

	public boolean isTriggerable() {
		return triggerable;
	}


}
