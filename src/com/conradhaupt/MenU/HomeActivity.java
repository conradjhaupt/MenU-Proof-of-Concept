package com.conradhaupt.MenU;

import com.conradhaupt.MenU.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class HomeActivity extends FragmentActivity implements OnClickListener
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// This code sets the App theme
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		int themeValue = Integer.parseInt(pref.getString(
				"theme_listpreference", "-1"));
		switch (themeValue)
		{
		case 0:
			this.setTheme(R.style.holo_light);
			break;
		case 1:
			this.setTheme(R.style.holo_light_darkactionbar);
			break;
		default:
			System.out.println("Preference value is not assigned to a theme.");
			break;
		}

		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_home);

		// This code assigns the sliding menu parameters
		RelativeLayout drawer = (RelativeLayout) this
				.findViewById(R.id.activity_home_navigation_drawer);
		if (!pref.getBoolean("smallslidingmenu_checkbox", false))
		{
			LayoutParams param = (LayoutParams) drawer.getLayoutParams();
			param.width = (int) this.getResources().getDimension(
					R.dimen.activity_home_drawer_width_large);
			drawer.setLayoutParams(param);
			View menu = this.getLayoutInflater().inflate(R.layout.sliding_menu,
					null);
			drawer.addView(menu);
		} else
		{
			LayoutParams param = (LayoutParams) drawer.getLayoutParams();
			param.width = (int) this.getResources().getDimension(
					R.dimen.activity_home_drawer_width_small);
			drawer.setLayoutParams(param);
			View menu = this.getLayoutInflater().inflate(
					R.layout.sliding_menu_small, null);
			drawer.addView(menu);
		}

		// This code assigns the current fragment
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.activity_home_fragment_frame, new HomeFragment(),
				"HomeFragment");

		// This code assigns the onClickListener to all views requiring it
		int[] viewIDs = { R.id.slidingmenu_home_button,
				R.id.slidingmenu_about_button,
				R.id.slidingmenu_featured_button,
				R.id.slidingmenu_information_panel,
				R.id.slidingmenu_restaurant_button,
				R.id.slidingmenu_setting_button,
				R.id.slidingmenu_account_button };
		for (int i = 0; i < viewIDs.length; i++)
		{
			(findViewById(viewIDs[i])).setOnClickListener(this);
		}
	}

	@Override
	public void onResume()
	{
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		DrawerLayout menu = (DrawerLayout) this
				.findViewById(R.id.activity_home_drawer);
		switch (Integer.parseInt(pref.getString(
				"slidingmenuside_listpreference", "-1")))
		{
		case 0:
			System.out.println("Setting slide menu to slide from the left.");
			// slide.setMode(SlidingMenu.LEFT);
			// slide.setShadowDrawable(R.drawable.menusliding_shadow_left);
			break;
		case 1:
			System.out.println("Setting slide menu to slide from the right.");
			// slide.setMode(SlidingMenu.RIGHT);
			// slide.setShadowDrawable(R.drawable.menusliding_shadow_right);
			break;
		default:
			System.out
					.println("Setting slide menu to slide from the left as no setting has been defined.");
			// slide.setMode(SlidingMenu.LEFT);
			// slide.setShadowDrawable(R.drawable.menusliding_shadow_left);
			break;

		}
		super.onResume();
	}

	@Override
	public void onBackPressed()
	{
		System.out.println("Back pressed!");
		if (this.getFragmentManager().getBackStackEntryCount() == 0)
		{
			super.onBackPressed();
		} else
		{
			this.getFragmentManager().popBackStack();
		}
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
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		switch (item.getItemId())
		{
		case android.R.id.home:
			// getSlidingMenu().showMenu();
			break;
		case R.id.menu_dropdown:
			// getSlidingMenu().showMenu();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void onHomeMenuClicked(View v)
	{
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isAncestral = false;
		isAncestral = pref.getBoolean("homenewinstance_switch", false);
		if (!isAncestral)
		{
			if (!this
					.getSupportFragmentManager()
					.getBackStackEntryAt(
							this.getSupportFragmentManager()
									.getBackStackEntryCount() - 1).getName()
					.equals("HomeFragment"))
			{
				this.instantiateFragment(new HomeFragment(), true, isAncestral,
						"HomeFragment", R.anim.fragment_change_enter,
						R.anim.fragment_change_exit, null);
			}
		} else
		{
			try
			{
				this.getSupportFragmentManager()
						.popBackStack(
								this.getSupportFragmentManager()
										.getBackStackEntryAt(0).getId(),
								this.getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			} catch (NullPointerException e)
			{

			}
		}
		// this.getSlidingMenu().showContent();
		System.out.println("Home has been pressed.");
	}

	public void onFeaturedMenuClicked(View v)
	{
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		System.out.println("Featured");
	}

	public void onRestaurantsMenuClicked(View v)
	{
		// this.instantiateFragment(new RestaurantBrowserFragment(), true, true,
		// "RestaurantFragment", R.anim.fragment_change_enter,
		// R.anim.fragment_change_exit, null);
		// this.getSlidingMenu().showContent();
		System.out.println("Restaurant has been pressed.");
	}

	public void onAccountsMenuClicked(View v)
	{
		this.instantiateFragment(new AccountFragment(), true, true,
				"AccountFragment", R.anim.fragment_change_enter,
				R.anim.fragment_change_exit, null);
		// this.getSlidingMenu().showContent();
		System.out.println("Account has been pressed.");
	}

	public void onSettingsMenuClicked(View v)
	{
		System.out.println("Settings has been pressed");
		// getSlidingMenu().showContent();
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	public void onAboutMenuClicked(View v)
	{
		this.instantiateFragment(new AboutFragment(), true, true,
				"AboutFragment", R.anim.fragment_change_enter,
				R.anim.fragment_change_exit, null);
		// this.getSlidingMenu().showContent();
		System.out.println("About has been pressed.");
	}

	public void onInformationClicked(View view)
	{
		System.out.println("Information panel has been pressed");
		// getSlidingMenu().showContent();
		AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
		aboutDialogFragment.show(this.getSupportFragmentManager(),
				"AboutDialog");
	}

	@Override
	public void onClick(View v)
	{
		System.out.println("onClick run for view with id " + v.getId() + "!");
		switch (v.getId())
		{
		case R.id.slidingmenu_home_button:
			this.onHomeMenuClicked(v);
			break;
		case R.id.slidingmenu_featured_button:
			this.onFeaturedMenuClicked(v);
			break;
		case R.id.slidingmenu_restaurant_button:
			this.onRestaurantsMenuClicked(v);
			break;
		case R.id.slidingmenu_account_button:
			this.onAccountsMenuClicked(v);
			break;
		case R.id.slidingmenu_setting_button:
			this.onSettingsMenuClicked(v);
			break;
		case R.id.slidingmenu_about_button:
			this.onAboutMenuClicked(v);
			break;
		case R.id.slidingmenu_information_panel:
			this.onInformationClicked(v);
			break;
		default:
			return;
		}
	}

	public void instantiateFragment(Fragment newFragment,
			boolean addToBackStack, boolean isAncestral, String tag,
			int introAnimation, int outroAnimation, Bundle arguments)
	{
		boolean isCurrentFragment = false;

		try
		{
			// Check if the current fragment is the one being instantiated
			isCurrentFragment = this
					.getSupportFragmentManager()
					.getBackStackEntryAt(
							this.getSupportFragmentManager()
									.getBackStackEntryCount() - 1).getName()
					.equals(tag);
			if (!isCurrentFragment)
			{
				// If the fragment is ancestral then check for previous
				// instantiations of the fragment
				if (isAncestral)
				{
					// The current fragment is not the one being instantiated
					// Attempt to pop back to an instance of the fragment, if
					// there
					try
					{
						this.getSupportFragmentManager()
								.popBackStack(
										tag,
										this.getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);

						// Check if the requested fragment was in the BackStack
						// and
						// has been brought forward
						isCurrentFragment = this
								.getSupportFragmentManager()
								.getBackStackEntryAt(
										this.getSupportFragmentManager()
												.getBackStackEntryCount() - 1)
								.getName().equals(tag);

						if (isCurrentFragment)
						{
							System.out
									.println("The fragment was in the BackStack and has been navigated to.");
							return;
						}
					} catch (Exception e)
					{
						System.out.println("Error thrown on popping!");
					}
				}
			} else
			{
				System.out
						.println("The fragment is already the current one, doing nothing!");
				return;
			}
		} catch (NullPointerException e)
		{
			System.out.println("You're at the front page, instantiate away!");
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("You're at the front page, instantiate away!");
		}

		// Since the fragment does not exist in the backstack or
		// as the current fragment then instantiate it
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(introAnimation, outroAnimation, outroAnimation,
				introAnimation);
		newFragment.setArguments(arguments);
		ft.replace(R.id.activity_home_fragment_frame, newFragment);
		if (addToBackStack)
		{
			ft.addToBackStack(tag);
		}
		ft.commit();
	}
}
