package com.conradhaupt.MenU;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.conradhaupt.MenU.cards.FeaturedCard;
import com.fima.cardsui.objects.Card;
import com.fima.cardsui.views.CardUI;

public class HomeFragment extends Fragment
{
	CardUI card;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Home fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	public void onResume()
	{
		card = (CardUI) this.getActivity().findViewById(R.id.cardsView);
		card.setSwipeable(true);
		card.addCard(new FeaturedCard("Featured"));
		card.refresh();
		super.onResume();
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_home, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		default:
		}
		return true;
	}

}
