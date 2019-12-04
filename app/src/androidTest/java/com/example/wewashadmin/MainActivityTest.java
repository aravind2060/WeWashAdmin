package com.example.wewashadmin;


import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> obj = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mactivity=null;
    @Before
    public void setUp() throws Exception {
        mactivity = obj.getActivity();
    }

    @Test
    public void testLaunch() {
        View v = mactivity.findViewById(R.id.Toolbar_ActivityMain);
        assertNotNull(v);
    }

    @After
    public void tearDown() throws Exception {
        mactivity=null;
    }
}