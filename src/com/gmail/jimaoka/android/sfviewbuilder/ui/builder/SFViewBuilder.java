package com.gmail.jimaoka.android.sfviewbuilder.ui.builder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.gmail.jimaoka.android.sfviewbuilder.vo.CompactLayoutVo;

/**
 * View をビルドするクラス
 * @author junji imaoka
 *
 */
public class SFViewBuilder {
	public static final int MP = LayoutParams.MATCH_PARENT;
	public static final int WC = LayoutParams.WRAP_CONTENT;

	public static View createSearchResultItemView(Context context, CompactLayoutVo compactLayoutVo, String name, Object value) {
		// TODO 取りあえず TextView で返す
		// データ型に応じた View を返すこと
		if(value == null || value.toString().equals("null")){
			value = "";
		}else{
			value = value.toString().replace("\n", "");
		}
		View view = createTextView(context, value.toString(), "");
		return view;
	}
	
	/**
	 * TextView を取得します
	 * @param context
	 * @param text
	 * @param tag
	 * @return
	 */
	public static TextView createTextView(Context context, String text, String tag){
		TextView tv = new TextView(context);
		tv.setText(text);
		LayoutParams param = (LayoutParams) new LayoutParams(WC, WC);
		tv.setLayoutParams(param);
		if(!TextUtils.isEmpty(tag)){
			tv.setTag(tag);
		}
		return tv;
	}
}
