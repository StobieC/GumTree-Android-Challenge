package com.gumtreeandroidtest.adapter;

import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gumtreeandroidtest.R;
import com.gumtreeandroidtest.model.Item;

public class ItemListAdapter extends BaseAdapter {

	private final List<Item> mList;
	private final Context mContext;
	private final FragmentManager mFragmentManager;
	private final LayoutInflater mLayoutInflater;

	public ItemListAdapter(Context context, List<Item> list,
			FragmentManager fragmentManager) {
		mContext = context;
		mList = list;
		mFragmentManager = fragmentManager;
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int position) {

		Item model = mList.get(position);
		int currentLayoutType = -1;

		if (model.getType().equals("pager")) {
			currentLayoutType = 0;
		} else if (model.getType().equals("title")) {
			currentLayoutType = 1;
		} else if (model.getType().equals("loc-price")) {
			currentLayoutType = 2;
		} else if (model.getType().equals("date")) {
			currentLayoutType = 3;
		} else if (model.getType().equals("desc-title")) {
			currentLayoutType = 4;
		} else if (model.getType().equals("description")) {
			currentLayoutType = 5;
		} else if (model.getType().equals("map")) {
			currentLayoutType = 6;
		}

		return currentLayoutType;

	}

    @TargetApi(16)
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		View convertView = view;
		ViewPagerHolder mViewPagerHolder;
		ViewTitleHolder mViewTitleHolder;
		ViewPlacePriceHolder mViewPlacePriceHolder;
		ViewDescriptionHolder mViewDescriptionHolder;

		if (getItemViewType(position) == 0) {

			if (convertView == null) {

				mViewPagerHolder = new ViewPagerHolder();

				convertView = mLayoutInflater.inflate(R.layout.pager_row_item,
						null);

				ViewPager mPager = (ViewPager) convertView
						.findViewById(R.id.list_pager);

				mPager.setBackground(mContext.getResources().getDrawable(
						R.drawable.image6));

				PagerAdapter mPagerAdapter = new ViewPagerAdapter(
						mFragmentManager);

				mPager.setAdapter(mPagerAdapter);

				mViewPagerHolder.mButton = (Button) convertView
						.findViewById(R.id.itemPicBtn);
				mViewPagerHolder.mViewPager = (ViewPager) convertView
						.findViewById(R.id.list_pager);

				convertView.setTag(mViewPagerHolder);
				convertView.setTag(R.id.itemPicBtn, mViewPagerHolder.mButton);
				convertView
						.setTag(R.id.list_pager, mViewPagerHolder.mViewPager);

			} else {
				mViewPagerHolder = (ViewPagerHolder) convertView.getTag();
			}
		} else if (getItemViewType(position) == 1) {

			if (convertView == null) {

				mViewTitleHolder = new ViewTitleHolder();

				convertView = mLayoutInflater.inflate(R.layout.title_row_item,
						null);

				TextView title = (TextView) convertView
						.findViewById(R.id.itemTitle);

				title.setText("Yamaha SG 2500 (Wineburst, 1980’s with OHSC, near mint condition)");
				convertView.setTag(mViewTitleHolder);
				convertView.setTag(R.id.itemTitle, mViewTitleHolder.mTextView);

			} else {
				mViewTitleHolder = (ViewTitleHolder) convertView.getTag();
			}

		} else if (getItemViewType(position) == 2) {

			if (convertView == null) {

				mViewPlacePriceHolder = new ViewPlacePriceHolder();

				convertView = mLayoutInflater.inflate(
						R.layout.place_price_row_item, null);

				ImageView icon = (ImageView) convertView
						.findViewById(R.id.itemMapIcon);
				TextView location = (TextView) convertView
						.findViewById(R.id.itemLocation);
				TextView price = (TextView) convertView
						.findViewById(R.id.itemPrice);

				icon.setImageResource(R.drawable.ic_loc);
				location.setText("Canterbury, Kent");
				price.setText("£2,250");

				convertView.setTag(mViewPlacePriceHolder);
				convertView.setTag(R.id.itemMapIcon,
						mViewPlacePriceHolder.mImageView);
				convertView.setTag(R.id.itemLocation,
						mViewPlacePriceHolder.mPlace);
				convertView
						.setTag(R.id.itemPrice, mViewPlacePriceHolder.mPrice);

			} else {
				mViewPlacePriceHolder = (ViewPlacePriceHolder) convertView
						.getTag();
			}

		} else if (getItemViewType(position) == 3) {

			if (convertView == null) {

				mViewTitleHolder = new ViewTitleHolder();

				convertView = mLayoutInflater.inflate(
						R.layout.date_posted_row_item, null);

				TextView date = (TextView) convertView
						.findViewById(R.id.itemDatePosted);

				date.setText("23/02/2015");

				convertView.setTag(mViewTitleHolder);
				convertView.setTag(R.id.itemDatePosted,
						mViewTitleHolder.mTextView);
				convertView.setTag(R.id.itemDatePosted,
						mViewTitleHolder.mTextView);

			} else {
				mViewTitleHolder = (ViewTitleHolder) convertView
						.getTag();
			}

		}

		else if (getItemViewType(position) == 4) {
			if (convertView == null) {

				mViewTitleHolder = new ViewTitleHolder();

				convertView = mLayoutInflater.inflate(
						R.layout.desc_title_item_row, null);

				TextView descTitle = (TextView) convertView
						.findViewById(R.id.itemDescrTitle);

				descTitle.setText("Description");

				convertView.setTag(mViewTitleHolder);
				convertView.setTag(R.id.itemDescrTitle,
						mViewTitleHolder.mTextView);
				convertView.setTag(R.id.itemDescrTitle,
						mViewTitleHolder.mTextView);

			} else {
				mViewTitleHolder = (ViewTitleHolder) convertView
						.getTag();
			}

		} else if (getItemViewType(position) == 5) {

			if (convertView == null) {

				mViewDescriptionHolder = new ViewDescriptionHolder();

				convertView = mLayoutInflater.inflate(R.layout.desc_row_item,
						null);

				TextView title = (TextView) convertView
						.findViewById(R.id.itemDescription);

				title.setText(" "
						+ new String(
								"Yamaha SG 2500 (Wineburst, 1980’s with OHSC, near mint condition) \n" +
                                        "\n" +
                                        "This is a rare Yamaha SG 2500 and the example of a top quality build guitar. Killer looks and great sound whichever way you set the tone/volume. It is considered a hybrid between the SG2000 and the SG3000, as such only produced in small number in 1983-4 \n" +
                                        "\n" +
                                        "It sports a Mahogany body with maple cap, with a proper thru neck, ebony fret board, mother of pearl split-wing fret-board position markers. Gold plated Hardware, Twin hum-buckers, coil tapping option. The guitar is fairly heavy, with lots and lots of sustain, partly due to the brass sustain plate fitted under bridge. \n" +
                                        "\n" +
                                        "The Yamaha 2000 series, SG-2000 and SG2500 is end of the the evolution from the renowned Yamaha SG 175 in the 1970’s that was made famous by musicians such as Carlos Santana and further used by Stuart Adamson, Trevor Horn, Al DiMeola, Phil Manzanera and Bob Marley to name but a few. \n" +
                                        "\n" +
                                        "Although the guitar is quite heavy, it is a joy to play with a low action. The two powerful AlnicoV shielded double humbuckers create a loud clear and clean sound, but wait till you set your Valve Amplifier to overdrive....The SG2500 springs to life and you don’t want to put it down again. Vintage original hard case included. "));

				convertView.setTag(mViewDescriptionHolder);
				convertView.setTag(R.id.itemDescription,
						mViewDescriptionHolder.mTextView);

			} else {
				mViewTitleHolder = (ViewTitleHolder) convertView.getTag();
			}

		}

		return convertView;
	}

	public static class ViewPagerHolder {
		public ViewPager mViewPager;
		public Button mButton;

	}

	public static class ViewTitleHolder {
		public TextView mTextView;
	}

	public static class ViewPlacePriceHolder {
		public ImageView mImageView;
		public TextView mPlace;
		public TextView mPrice;
	}

	public static class ViewDescriptionHolder {
		public TextView mTextView;
	}
}
