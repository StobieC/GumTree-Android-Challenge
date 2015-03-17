package com.gumtreeandroidtest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.gumtreeandroidtest.adapter.ItemListAdapter;
import com.gumtreeandroidtest.model.Item;

public class ItemActivity extends FragmentActivity implements LocationListener {

    private ShareActionProvider mShareActionProvider;
    private ListView mListView;
    private List<Item> mList;
    private Button btn_Call,btn_Email;

    public ItemActivity() {
        super();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_view_item);


        final LocationManager locationManager;
        final String bestProvider;
        String LOCATION_SERVICE="location" ;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        //criteria.setAccuracy();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);  // More accurate, GPS fix.
        bestProvider = locationManager.getBestProvider(criteria,true);



		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		mListView = (ListView) findViewById(R.id.itemList);
		btn_Call =(Button)findViewById(R.id.btnCall);
		btn_Email =(Button)findViewById(R.id.btnEmail);
		mList = getItemDescriptionList();

		BaseAdapter adapter = new ItemListAdapter(getApplicationContext(),
				mList, getSupportFragmentManager());

		mListView.setAdapter(adapter);
		mListView.setOnTouchListener(new AdapterView.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mListView.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}


		});

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

                Location location = locationManager.getLastKnownLocation(bestProvider);
                final double lon = location.getLongitude();
                final double lng = location.getLatitude();

				Item item = (Item) arg0.getItemAtPosition(arg2);
				if (item.getType().equals("loc-price")) {
					Intent intent = new Intent(
							android.content.Intent.ACTION_VIEW,
							Uri.parse("http://maps.google.com/maps?saddr=51.2123087,1.1365936&daddr=" + lon + "," + lng ));
					startActivity(intent);
				}
			}
		});

		//Call Button
		btn_Call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(Intent.ACTION_CALL);
				String phNum = "tel:" + "00000000";
				myIntent.setData(Uri.parse(phNum));
				startActivity( myIntent ) ;

			}
		});

		//Email Button
		btn_Email.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"asd@examplemail.com"});
				i.putExtra(Intent.EXTRA_SUBJECT, "Buy the call");
				i.putExtra(Intent.EXTRA_TEXT   , "I am interrested ");
				try {
				    startActivity(Intent.createChooser(i, "Send mail..."));
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(ItemActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private List<Item> getItemDescriptionList() {
		List<Item> mList = new ArrayList<Item>();
		Item mItem = null;
		/**
		 * This loop helps to populate item Listview with various layouts for a
		 * specific item
		 */
		for (int i = 0; i < 6; i++) {
			if (i == 0) {
				mItem = new Item();
				mItem.setType("pager");
			} else if (i == 1) {
				mItem = new Item();
				mItem.setType("title");
			} else if (i == 2) {
				mItem = new Item();
				mItem.setType("loc-price");
			} else if (i == 3) {
				mItem = new Item();
				mItem.setType("date");
			} else if (i == 4) {
				mItem = new Item();
				mItem.setType("desc-title");
			} else if (i == 5) {
				mItem = new Item();
				mItem.setType("description");
			} else if (i == 6) {
				mItem = new Item();
				mItem.setType("map");
			}
			mList.add(mItem);
		}

		return mList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem item = menu.findItem(R.id.action_share);

		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		mShareActionProvider.setShareIntent(getDefaultShareIntent());

		invalidateOptionsMenu();

		// Return true to display menu
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			ItemActivity.super.onBackPressed();
			return true;
		case R.id.action_favourite:
			Toast.makeText(getApplicationContext(),
					"Favourite checked: " + !item.isChecked(),
					Toast.LENGTH_SHORT).show();
			item.setChecked(!item.isChecked());
			item.setIcon(item.isChecked() ? R.drawable.ic_bookmark
					: R.drawable.ic_no_bookmark);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/** Returns a share intent */
	private Intent getDefaultShareIntent() {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "");
		intent.putExtra(Intent.EXTRA_TEXT, "");
		return intent;
	}

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}