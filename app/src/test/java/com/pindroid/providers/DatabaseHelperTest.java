package com.pindroid.providers;

import com.pindroid.providers.BookmarkContentProvider.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.test.core.app.ApplicationProvider;

@RunWith(RobolectricTestRunner.class)
public class DatabaseHelperTest {

    private DatabaseHelper helper;

    @Before
    public void beforeEachTest() {
        helper = new DatabaseHelper(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void helper_createsDatabase() {
        helper.getWritableDatabase(); // at least should not crash
    }
}