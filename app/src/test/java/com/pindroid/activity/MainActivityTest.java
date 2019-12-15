package com.pindroid.activity;

import static org.robolectric.Robolectric.buildActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

  private ActivityController<Main> controller;

  @Before
  public void beforeEachTest() {
    controller = buildActivity(Main.class);
  }

  @Test
  public void activity_starts() { // just basic test checking that activity doesn't crash
    controller.create().start().resume();
  }
}
