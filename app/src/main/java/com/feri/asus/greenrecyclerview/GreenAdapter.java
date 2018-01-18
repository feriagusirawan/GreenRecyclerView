package com.feri.asus.greenrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.TextView;


// COMPLETED (4) From GreenAdapter, extend RecyclerView.Adapter<NumberViewHolder>
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();

    // COMPLETED (3) Create a final private ListItemClickListener called mOnClickListener
    final private ListItemClickListener mOnClickListener;


    // COMPLETED (8) Add a private static int called viewHolderCount that will hold the total number of ViewHolders that are created
    /*
     * The number of ViewHolders that have been created. Typically, you can figure out how many
     * there should be by determining how many list items fit on your screen at once and add 2 to 4
     * to that number. That isn't the exact formula, but will give you an idea of how many
     * ViewHolders have been created to display any given RecyclerView.
     *
     * Here's some ASCII art to hopefully help you understand:
     *
     *    ViewHolders on screen:
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 0 |
     *        *-----------------------------*
     *        |         ViewHolder index: 1 |
     *        *-----------------------------*
     *        |         ViewHolder index: 2 |
     *        *-----------------------------*
     *        |         ViewHolder index: 3 |
     *        *-----------------------------*
     *        |         ViewHolder index: 4 |
     *        *-----------------------------*
     *        |         ViewHolder index: 5 |
     *        *-----------------------------*
     *        |         ViewHolder index: 6 |
     *        *-----------------------------*
     *        |         ViewHolder index: 7 |
     *        *-----------------------------*
     *
     *    Extra ViewHolders (off screen)
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 8 |
     *        *-----------------------------*
     *        |         ViewHolder index: 9 |
     *        *-----------------------------*
     *        |         ViewHolder index: 10|
     *        *-----------------------------*
     *        |         ViewHolder index: 11|
     *        *-----------------------------*
     *
     *    Total number of ViewHolders = 11
     */
    private static int viewHolderCount;

    // COMPLETED (1) Add a private int variable called mNumberItems
    private int mNumberItems;


    // COMPLETED (1) Add an interface called ListItemClickListener
    // COMPLETED (2) Within that interface, define a void method called onListItemClick that takes an int as a parameter
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }





    // COMPLETED (2) Create a constructor for GreenAdapter that accepts an int as a parameter for numberOfItems
    // COMPLETED (3) Store the numberOfItems parameter in mNumberItems
    // COMPLETED (4) Add a ListItemClickListener as a parameter to the constructor and store it in mOnClickListener
    public GreenAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        // COMPLETED (9) When a new GreenAdapter is created, set the viewHolderCount to 0
        viewHolderCount = 0;
    }

    // COMPLETED (5) Override the onCreateViewHolder method
    // COMPLETED (6) Create and return a new NumberViewHolder within this method
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        // COMPLETED (12) Set the text of viewHolderIndex to "ViewHolder index: " + viewHolderCount
        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        // COMPLETED (13) Use ColorUtils.getViewHolderBackgroundColorFromInstance and pass in a Context and the viewHolderCount
        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        // COMPLETED (14) Set the background color of viewHolder.itemView with the color from above
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        // COMPLETED (15) Increment viewHolderCount and log its value
        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }



    // COMPLETED (7) Override onBindViewHolder
    // COMPLETED (8) Within onBindViewHolder, call holder.bind and pass in the position
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }



    // COMPLETED (12) Create a class called NumberViewHolder that extends RecyclerView.ViewHolder
    class NumberViewHolder extends RecyclerView.ViewHolder
            // COMPLETED (5) Implement OnClickListener in the NumberViewHolder class
            implements OnClickListener {

        // COMPLETED (13) Within NumberViewHolder, create a TextView variable called listItemNumberView
        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;

        // COMPLETED (10) Add a TextView variable to display the ViewHolder index
        // Will display which ViewHolder is displaying this data
        TextView viewHolderIndex;



        // COMPLETED (14) Create a constructor for NumberViewHolder that accepts a View called itemView as a parameter
        public NumberViewHolder(View itemView) {
            // COMPLETED (15) Within the constructor, call super(itemView) and then find listItemNumberView by ID
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);

            // COMPLETED (11) Use itemView.findViewById to get a reference to tv_view_holder_instance
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }

        // COMPLETED (16) Within the NumberViewHolder class, create a void method called bind that accepts an int parameter called listIndex
        void bind(int listIndex) {
            // COMPLETED (17) Within bind, set the text of listItemNumberView to the listIndex
            // COMPLETED (18) Be careful to get the String representation of listIndex, as using setText with an int does something different
            listItemNumberView.setText(String.valueOf(listIndex));
        }

        // COMPLETED (6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
