package com.example.guide.adapter;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guide.R;
import com.example.guide.bean.Scenery;
import com.example.guide.utils.FileUtils;
import com.example.guide.utils.ImageUtils;

/**
 * @author cxm E-mail: 1993baicai@sina.com
 * @date ：2015-7-26 下午12:01:21
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class SceneryListAdapter extends BaseAdapter {

	private Context mContext;
	private List<Scenery> mList;
	private ViewHolder mViewHolder;
	private Scenery mScenery;

	public SceneryListAdapter(Context context, List<Scenery> list) {
		mContext = context;
		mList = list;
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Scenery getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		mViewHolder = null;
		mScenery = mList.get(position);

		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_scenery_list, new LinearLayout(mContext));
			mViewHolder.nameTextView = (TextView) convertView.findViewById(R.id.item_scenery_list_title_tv);
			mViewHolder.picImageView = (ImageView) convertView.findViewById(R.id.item_scenery_list_pic_iv);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		mViewHolder.nameTextView.setText(mScenery.getName());
		String picPath = FileUtils.getSDGuidePicPath() + mScenery.getPicUrl() + mScenery.getName() + ".jpg";
		Log.d("cxm", picPath);
		File mFile = new File(picPath);
		if (mFile.exists()) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 2;
			Bitmap bitmap = ImageUtils.ListViewBitmap(picPath);
			mViewHolder.picImageView.setImageBitmap(bitmap);
		} else {
			mViewHolder.picImageView.setImageResource(R.drawable.ic_no_img);
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView nameTextView;
		private ImageView picImageView;
	}
}
