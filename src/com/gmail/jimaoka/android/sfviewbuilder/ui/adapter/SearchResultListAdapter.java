package com.gmail.jimaoka.android.sfviewbuilder.ui.adapter;

import java.util.LinkedHashMap;
import java.util.List;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.gmail.jimaoka.android.sfviewbuilder.R;
import com.gmail.jimaoka.android.sfviewbuilder.ui.builder.SFViewBuilder;
import com.gmail.jimaoka.android.sfviewbuilder.vo.CompactLayoutVo;

/**
 * 検索結果表示用 ArrayAdapter です
 * @author junji imaoka
 *
 */
public class SearchResultListAdapter extends ArrayAdapter<LinkedHashMap<String, Object>> {
	private Context context;
	private LayoutInflater inflater;
	private CompactLayoutVo compactLayoutVo;
	private List<LinkedHashMap<String, Object>> searchResults;

	public SearchResultListAdapter(Context context, int resource, 
			List<LinkedHashMap<String, Object>> searchResults, CompactLayoutVo compactLayoutVo) {
		super(context, resource, searchResults);
		this.context = context;
		this.searchResults = searchResults;
		this.compactLayoutVo = compactLayoutVo;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinkedHashMap<String, Object> recordMap = searchResults.get(position);

		if(convertView == null){
			convertView = inflater.inflate(R.layout.search_result_list_item, null);
			for(String key : recordMap.keySet()){
				Object data = recordMap.get(key);
				View itemView = SFViewBuilder.createSearchResultItemView(context, compactLayoutVo, key, data);
				((LinearLayout)convertView).addView(itemView);
			}
		}
		return convertView;
	}
	
}
