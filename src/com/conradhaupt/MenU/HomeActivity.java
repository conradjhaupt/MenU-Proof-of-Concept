package com.conradhaupt.MenU;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_home);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragment_frame, new HomeFragment());
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		System.out.println("Action bar button was pressed!");
		System.out.println(item.getItemId());
		switch (item.getItemId())
		{
		case R.id.menu_search:
			FragmentTransaction ft = this.getSupportFragmentManager()
					.beginTransaction();
			ft.replace(R.id.fragment_frame, new RestaurantFragment());
			ft.setCustomAnimations(R.anim.fragment_change_enter,
					R.anim.fragment_change_exit);
			ft.addToBackStack(null);
			ft.commit();
			break;
		case android.R.id.home:
			break;
		case R.id.menu_drop_about:
			break;
		case R.id.menu_drop_account:
			break;
		case R.id.menu_drop_menu:
			break;
		case R.id.menu_drop_settings:
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
