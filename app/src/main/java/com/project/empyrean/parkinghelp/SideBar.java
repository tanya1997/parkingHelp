package com.project.empyrean.parkinghelp;

import android.view.MenuItem;

/**
 * Created by empyrean on 01.11.2016.
 */

public class SideBar {
    static int selectedItem;
    final static int ItemSity = 0;
    final static int ItemAutodrome = 1;
    final static int ItemPdd = 2;
    final static int ItemFeedback = 3;
    public void setSelectedItemMenu(MenuItem item){
        switch (item.getItemId()) {
            case R.id.parking_city_item:
                selectedItem = ItemSity;
                break;
            case R.id.parking_autodrome_item:
                selectedItem = ItemAutodrome;
                break;
            case R.id.parking_regulations_item:
                selectedItem = ItemPdd;
                break;
            case R.id.feedback_item:
                selectedItem = ItemFeedback;
                break;
        }
    }
    public int getSelectedItem(){
        return selectedItem;
    }

}
