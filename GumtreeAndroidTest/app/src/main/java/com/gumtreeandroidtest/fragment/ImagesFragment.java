package com.gumtreeandroidtest.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gumtreeandroidtest.R;

@SuppressLint("ValidFragment")
public class ImagesFragment extends Fragment {

    public ImagesFragment(){

    }

	private View mView;
	private final int mPictures[] = { R.drawable.image6, R.drawable.image2,
			R.drawable.image1, R.drawable.image4, R.drawable.image5,
			R.drawable.image3, R.drawable.image7, R.drawable.image8,
			R.drawable.image9 };

	static int mPosition;
	private ImageView mImageView;


	public ImagesFragment(int position) {
		mPosition = position;
		}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mView = inflater.inflate(R.layout.fragment_pager_display, container,
				false);
		mImageView = (ImageView) mView.findViewById(R.id.itemImage);
		mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		mImageView.setImageResource(mPictures[mPosition]);

		return mView;
	}
}