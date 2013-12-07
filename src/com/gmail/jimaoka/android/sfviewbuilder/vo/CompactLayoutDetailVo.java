package com.gmail.jimaoka.android.sfviewbuilder.vo;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Field 情報の ValueObject
 * @author junji imaoka
 *
 */
public class CompactLayoutDetailVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String calculatedFormula;
	private String defaultValueFormula;
	private boolean defaultedOnCreate;
	private int digits;
	private boolean groupable;
	private boolean permissionable;
	private String[] referenceTo;
	private String relationshipOrder;
	private String soapType;
	private boolean dependentPicklist;
	private boolean namePointing;
	private boolean custom;
	private boolean htmlFormatted;
	private boolean unique;
	private boolean nameField;
	private boolean sortable;
	private boolean filterable;
	private boolean caseSensitive;
	private boolean restrictedPicklist;
	private boolean calculated;
	private int scale;
	private boolean nillable;
	private String inlineHelpText;
	private boolean writeRequiresMasterRead;
	private boolean externalId;
	private boolean idLookup;
	private boolean updateable;
	private boolean createable;
	private String controllerName;
	private String[] picklistValues;
	private boolean autoNumber;
	private String relationshipName;
	private boolean deprecatedAndHidden;
	private boolean displayLocationInDecimal;
	private boolean cascadeDelete;
	private boolean restrictedDelete;
	private int length;
	private String name;
	private String type;
	private String defaultValue;
	private int byteLength;
	private String label;
	private int precision;
	
	/**
	 * コンストラクタ
	 * @param root
	 * @throws JSONException 
	 */
	public CompactLayoutDetailVo(JSONObject root) throws JSONException{
		calculated = root.getBoolean("calculated");
		defaultValueFormula = root.getString("defaultValueFormula");
		defaultedOnCreate = root.getBoolean("defaultedOnCreate");
		digits = root.getInt("digits");
		groupable = root.getBoolean("groupable");
		permissionable = root.getBoolean("permissionable");
		referenceTo = root.getString("referenceTo").replace("[", "").replace("]", "").replace("\"", "").split(",");
		relationshipOrder = root.getString("relationshipOrder");
		soapType = root.getString("soapType");
		dependentPicklist = root.getBoolean("dependentPicklist");
		namePointing = root.getBoolean("namePointing");
		custom = root.getBoolean("custom");
		htmlFormatted = root.getBoolean("htmlFormatted");
		unique = root.getBoolean("unique");
		nameField = root.getBoolean("nameField");
		sortable = root.getBoolean("sortable");
		filterable = root.getBoolean("filterable");
		caseSensitive = root.getBoolean("caseSensitive");
		restrictedPicklist = root.getBoolean("restrictedPicklist");
		calculated = root.getBoolean("calculated");
		scale = root.getInt("scale");
		nillable = root.getBoolean("nillable");
		inlineHelpText = root.getString("inlineHelpText");
		writeRequiresMasterRead = root.getBoolean("writeRequiresMasterRead");
		externalId = root.getBoolean("externalId");
		idLookup = root.getBoolean("idLookup");
		updateable = root.getBoolean("updateable");
		createable = root.getBoolean("createable");
		controllerName = root.getString("controllerName");
		picklistValues = root.getString("picklistValues").replace("[", "").replace("]", "").replace("\"", "").split(",");
		autoNumber = root.getBoolean("autoNumber");
		relationshipName = root.getString("relationshipName");
		deprecatedAndHidden = root.getBoolean("deprecatedAndHidden");
		displayLocationInDecimal = root.getBoolean("displayLocationInDecimal");
		cascadeDelete = root.getBoolean("cascadeDelete");
		restrictedDelete = root.getBoolean("restrictedDelete");
		length = root.getInt("length");
		name = root.getString("name");
		type = root.getString("type");
		defaultValue = root.getString("defaultValue");
		byteLength = root.getInt("byteLength");
		label = root.getString("label");
		precision = root.getInt("precision");
	}

	public String getCalculatedFormula() {
		return calculatedFormula;
	}

	public String getDefaultValueFormula() {
		return defaultValueFormula;
	}

	public boolean isDefaultedOnCreate() {
		return defaultedOnCreate;
	}

	public int getDigits() {
		return digits;
	}

	public boolean isGroupable() {
		return groupable;
	}

	public boolean isPermissionable() {
		return permissionable;
	}

	public String[] getReferenceTo() {
		return referenceTo;
	}

	public String getRelationshipOrder() {
		return relationshipOrder;
	}

	public String getSoapType() {
		return soapType;
	}

	public boolean isDependentPicklist() {
		return dependentPicklist;
	}

	public boolean isNamePointing() {
		return namePointing;
	}

	public boolean isCustom() {
		return custom;
	}

	public boolean isHtmlFormatted() {
		return htmlFormatted;
	}

	public boolean isUnique() {
		return unique;
	}

	public boolean isNameField() {
		return nameField;
	}

	public boolean isSortable() {
		return sortable;
	}

	public boolean isFilterable() {
		return filterable;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public boolean isRestrictedPicklist() {
		return restrictedPicklist;
	}

	public boolean isCalculated() {
		return calculated;
	}

	public int getScale() {
		return scale;
	}

	public boolean isNillable() {
		return nillable;
	}

	public String getInlineHelpText() {
		return inlineHelpText;
	}

	public boolean isWriteRequiresMasterRead() {
		return writeRequiresMasterRead;
	}

	public boolean isExternalId() {
		return externalId;
	}

	public boolean isIdLookup() {
		return idLookup;
	}

	public boolean isUpdateable() {
		return updateable;
	}

	public boolean isCreateable() {
		return createable;
	}

	public String getControllerName() {
		return controllerName;
	}

	public String[] getPicklistValues() {
		return picklistValues;
	}

	public boolean isAutoNumber() {
		return autoNumber;
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public boolean isDeprecatedAndHidden() {
		return deprecatedAndHidden;
	}

	public boolean isDisplayLocationInDecimal() {
		return displayLocationInDecimal;
	}

	public boolean isCascadeDelete() {
		return cascadeDelete;
	}

	public boolean isRestrictedDelete() {
		return restrictedDelete;
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public int getByteLength() {
		return byteLength;
	}

	public String getLabel() {
		return label;
	}

	public int getPrecision() {
		return precision;
	}
	
}
