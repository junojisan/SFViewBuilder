package com.gmail.jimaoka.android.sfviewbuilder.ui;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.gmail.jimaoka.android.sfviewbuilder.R;
import com.gmail.jimaoka.android.sfviewbuilder.ui.adapter.SearchResultListAdapter;
import com.gmail.jimaoka.android.sfviewbuilder.vo.CompactLayoutComponentVo;
import com.gmail.jimaoka.android.sfviewbuilder.vo.CompactLayoutDetailVo;
import com.gmail.jimaoka.android.sfviewbuilder.vo.CompactLayoutFieldItemVo;
import com.gmail.jimaoka.android.sfviewbuilder.vo.CompactLayoutVo;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestClient.AsyncRequestCallback;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;
import com.salesforce.androidsdk.ui.sfnative.SalesforceActivity;

/**
 * Search Activity
 * @author junji
 *
 */
public class SearchActivity extends SalesforceActivity {
	public static final String ARG_LABEL = "arg_label";
	public static final String ARG_NAME = "arg_name";
	public static final String ARG_COMPACT_LAYOUT_VO = "arg_compact_layout_vo";
	public static final String ARG_SELECTED_ITEM = "arg_selected_item";
	
	private RestClient client;
	private SearchResultListAdapter adapter;
	private SearchView searchView;
	
	private List<LinkedHashMap<String, Object>>searchResults;
	private CompactLayoutVo compactLayoutVo;
	private String name;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		compactLayoutVo = (CompactLayoutVo) intent.getExtras().getSerializable(ARG_COMPACT_LAYOUT_VO);
		setTitle(intent.getExtras().getString(ARG_LABEL));
		name = intent.getExtras().getString(ARG_NAME);
		
		setAdapter();
		setListner();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search, menu);
		
		searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
		searchView.setIconified(false);
		searchView.setSubmitButtonEnabled(true);
		searchView.setQueryHint(getString(R.string.hint_search_string));
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
					sendGetSOSLRequest(query);
					return true;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});
		return true;
	}

	@Override
	public void onResume(RestClient client) {
		this.client = client;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case android.R.id.home:
			return onHomeOptionItemSelected(item);
		default:
			break;
		}
		return false;
	}

	/**
	 * Menu 「Home」 ItemSelected ハンドラ
	 * @return
	 */
	public boolean onHomeOptionItemSelected(MenuItem item){
		Intent extra = new Intent();
		setResult(RESULT_CANCELED, extra);
		finish();
		return true;
	}
	
	/**
	 * Adapter を ListView に設定します
	 */
	private void setAdapter(){
		searchResults = new ArrayList<LinkedHashMap<String,Object>>();
		adapter = new SearchResultListAdapter(this, R.layout.search_result_list_item, searchResults, compactLayoutVo);
		
		LinearLayout rootView = (LinearLayout) findViewById(R.id.root);
		ListView lvSearchResult = (ListView) rootView.findViewById(R.id.lv_search_results);
		lvSearchResult.setAdapter(adapter);
	}
	
	/**
	 * Listner を ListView に設定します
	 */
	private void setListner(){
		LinearLayout rootView = (LinearLayout) findViewById(R.id.root);
		ListView lvSearchResult = (ListView) rootView.findViewById(R.id.lv_search_results);
		lvSearchResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onSearchResultItemClick(parent, view, position, id);
			}
		});
	}
	
	/**
	 * 検索結果の onItemClick 処理です
	 */
	protected void onSearchResultItemClick(AdapterView<?> parent, View view, int position, long id) {
		LinkedHashMap<String, Object> selectedItemMap = new LinkedHashMap<String, Object>();
		selectedItemMap = searchResults.get(position);
		
		Intent extra = new Intent();
		extra.putExtra(ARG_SELECTED_ITEM, selectedItemMap);
		setResult(RESULT_OK, extra);
		
		finish();
	}

	/**
	 * SOSL の RestRequest を送信します
	 * @param searchQuery
	 */
	private void sendGetSOSLRequest(String searchQuery) {
		String sosl = createSOSL().replace("%SEARCH_QUERY%", searchQuery);
		String apiVersion = getString(R.string.api_version);
		try {
			RestRequest restRequest = RestRequest.getRequestForSearch(apiVersion, sosl);
			client.sendAsync(restRequest, new AsyncRequestCallback() {
				
				@Override
				public void onSuccess(RestRequest request, RestResponse response) {
					try {
						setSearchResults(response);
						adapter.clear();
						adapter.addAll(searchResults);
					} catch (Exception e) {
						Log.e(SearchActivity.class.toString(), e.getMessage());
						displayMessage(e.getMessage());
					}
					adapter.notifyDataSetChanged();
				}

				@Override
				public void onError(Exception exception) {
					Log.e(SearchActivity.class.toString(), exception.getMessage());
					displayMessage(exception.getMessage());
				}
			});
		} catch (UnsupportedEncodingException e) {
			Log.e(SearchActivity.class.toString(), e.getMessage());
			displayMessage(e.getMessage());
		}
	}
	
	/**
	 * RestResponse から検索結果を設定します
	 * @param response 
	 * @throws IOException 
	 * @throws JSONException 
	 * @throws ParseException 
	 */
	private void setSearchResults(RestResponse response) throws ParseException, JSONException, IOException {
		searchResults = new ArrayList<LinkedHashMap<String,Object>>();
		
		JSONArray records = response.asJSONArray();
		for(int i = 0; i < records.length(); i++){
			boolean existsId = false;
			JSONObject root = records.getJSONObject(i);
			LinkedHashMap<String, Object> rec = new LinkedHashMap<String,Object>();
			for(CompactLayoutFieldItemVo fieldItemVo : compactLayoutVo.getFieldItemsVos()){
				for(CompactLayoutComponentVo componentVo : fieldItemVo.getComponentsVos()){
					CompactLayoutDetailVo detailVo = componentVo.getDetailVo();
					if(detailVo != null){
						rec.put(detailVo.getName(), getValue(detailVo, root));
						if(detailVo.getName().toLowerCase().equals("id")){
							existsId = true;
						}
					}
				}
			}
			if(!existsId){
				rec.put("Id", root.get("Id"));
			}
			searchResults.add(rec);
		}
	}
	
	/**
	 * 検索結果の項目の値を取得します
	 * @param detailVo
	 * @param jObj
	 * @return
	 * @throws JSONException
	 */
	private Object getValue(CompactLayoutDetailVo detailVo, JSONObject jObj) throws JSONException{
		Object obj = null;
		
		if(detailVo.getType().toLowerCase().equals("string")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("boolean")){
			obj = jObj.getBoolean(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("int")){
			obj = jObj.getInt(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("double")){
			if(!jObj.getString(detailVo.getName()).equals("null")){
				NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
				obj = format.format(jObj.getDouble(detailVo.getName()));
			}
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("date")){
			// TODO 日付をパース後、DateUtils でフォーマットする
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("datetime")){
			// TODO 日付をパース後、DateUtils でフォーマットする
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("base64")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("id")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("reference")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("currency")){
			if(!jObj.getString(detailVo.getName()).equals("null")){
				NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
				obj = format.format(jObj.getDouble(detailVo.getName()));
			}
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("textarea")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("percent")){
			if(!jObj.getString(detailVo.getName()).equals("null")){
				NumberFormat format = NumberFormat.getPercentInstance(Locale.getDefault());
				obj = format.format(jObj.getDouble(detailVo.getName()));
			}
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("phone")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("url")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("email")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("combobox")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("picklist")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("multipicklist")){
			obj = jObj.getString(detailVo.getName());
		}else if(detailVo.getType().toLowerCase(Locale.getDefault()).equals("anyType")){
			obj = jObj.getString(detailVo.getName());
		}else{
			obj = jObj.getString(detailVo.getName());
		}
		
		return obj;
	}
	
	/**
	 * SOSL を生成します
	 * @return
	 */
	private String createSOSL(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("FIND {%SEARCH_QUERY%} ");
		sb.append("RETURNING " + name + " ");
		sb.append("(");

		boolean existsId = false;
		for(CompactLayoutFieldItemVo fieldItemVo : compactLayoutVo.getFieldItemsVos()){
			for(CompactLayoutComponentVo componentVo : fieldItemVo.getComponentsVos()){
				CompactLayoutDetailVo detailVo = componentVo.getDetailVo();
				String field = null;
				if(detailVo != null){
					if(detailVo.getType().toLowerCase().equals("picklist")){
						field = "toLabel(" + detailVo.getName() + ")";
					}else{
						field = detailVo.getName();
					}
					sb.append(field);
					sb.append(',');
					
					if(detailVo.getName().toLowerCase().equals("id")){
						existsId = true;
					}
				}
			}
		}
		
		String sosl = sb.toString();
		if(!existsId){
			sosl += "Id,";
		}
		
		if(sosl.endsWith(",")){
			sosl = sosl.substring(0, sosl.length() - 1);
		}
		sosl += (")");
		
		Log.d("createSOSL", sosl);
		return sosl;
	}

	/**
	 * メッセージを Toast で表示します
	 * @param message
	 */
	private void displayMessage(String message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

}
