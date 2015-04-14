package com.joelsharin.brianesch.hex;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameList extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expandListView;
    List<String> dataHeaders;
    HashMap<String, List<String>> dataChildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        if(!this.isLoggedIn()) {
            Intent loginIntent = new Intent(getApplicationContext(), LoginMenu.class);
            startActivity(loginIntent);
        }
        //Get the listView
        expandListView = (ExpandableListView) findViewById(R.id.expandableListView);

        //prepare list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, dataHeaders, dataChildren);

        //Setting list adapter
        expandListView.setAdapter(listAdapter);

        expandListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isLoggedIn(){
        return false;
    }

    public void prepareListData(){
        dataHeaders = new ArrayList<String>();
        dataChildren = new HashMap<String, List<String>>();

        // Adding child data
        dataHeaders.add("Top 250");
        dataHeaders.add("Now Showing");
        dataHeaders.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        dataChildren.put(dataHeaders.get(0), top250); // Header, Child data
        dataChildren.put(dataHeaders.get(1), nowShowing);
        dataChildren.put(dataHeaders.get(2), comingSoon);
    }

}
