package com.example.wewashadmin;

import com.example.wewashadmin.FragmentThings.CompletedOrdersFragment;
import com.example.wewashadmin.FragmentThings.DeliveredOrders;
import com.example.wewashadmin.FragmentThings.OrdersFragment;
import com.example.wewashadmin.FragmentThings.PendingOrdersFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new OrdersFragment();
            case 1:
                return new PendingOrdersFragment();
            case 2:
                return new CompletedOrdersFragment();
            case 3:
                return new DeliveredOrders();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
