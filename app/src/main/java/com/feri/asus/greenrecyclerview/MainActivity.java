package com.feri.asus.greenrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// COMPLETED (8) Implement GreenAdapter.ListItemClickListener from the MainActivity
public class MainActivity extends AppCompatActivity
        implements GreenAdapter.ListItemClickListener {

    // COMPLETED (1) Create a private static final int called NUM_LIST_ITEMS and set it equal to 100
    private static final int NUM_LIST_ITEMS = 100;

    // COMPLETED (2) Create a GreenAdapter variable called mAdapter
    private GreenAdapter mAdapter;
    // COMPLETED (3) Create a RecyclerView variable called mNumbersList
    private RecyclerView mNumbersList;

    // COMPLETED (9) Create a Toast variable called mToast to store the current Toast
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // COMPLETED (4) Use findViewById to store a reference to the RecyclerView in mNumbersList
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        // COMPLETED (5) Create a LinearLayoutManager variable called layoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        // COMPLETED (6) Use setLayoutManager on mNumbersList with the LinearLayoutManager we created above
        mNumbersList.setLayoutManager(layoutManager);

        // COMPLETED (7) Use setHasFixedSize(true) to designate that the contents of the RecyclerView won't change an item's size
        mNumbersList.setHasFixedSize(true);



        // COMPLETED (8) Store a new GreenAdapter in mAdapter and pass it NUM_LIST_ITEMS
        // mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
        // COMPLETED (13) Pass in this as the ListItemClickListener to the GreenAdapter constructor
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);

        // COMPLETED (9) Set the GreenAdapter you created on mNumbersList
        mNumbersList.setAdapter(mAdapter);
    }

    // COMPLETED (7) Override onCreateOptionsMenu
    // COMPLETED (8) Use getMenuInflater().inflate to inflate the menu
    // COMPLETED (9) Return true to display this menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // COMPLETED (10) Override onOptionsItemSelected
    // COMPLETED (11) Within this method, get the ID from the MenuItem
    // COMPLETED (12) If the ID equals R.id.action_refresh, create and set a new adapter on the RecyclerView and return true
    // COMPLETED (13) For now, for all other IDs, return super.onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_refresh:
                //mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
// COMPLETED (10) Override ListItemClickListener's onListItemClick method
    @Override
    public void onListItemClick(int clickedItemIndex) {
        // COMPLETED (11) In the beginning of the method, cancel the Toast if it isn't null
        if (mToast != null) {
            mToast.cancel();
        }

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
}


