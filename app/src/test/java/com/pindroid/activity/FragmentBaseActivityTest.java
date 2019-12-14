package com.pindroid.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.pindroid.Constants;
import com.pindroid.application.PindroidApplication;
import com.pindroid.authenticator.AuthenticatorActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Robolectric.buildActivity;

@RunWith(RobolectricTestRunner.class)
public class FragmentBaseActivityTest {

    private ActivityController<TestActivity> controller;

    @Before
    public void beforeEachTest() {
        controller = buildActivity(TestActivity.class);
    }

    @Test
    public void activity_startsAuthenticationOnNoAccount() {
        controller.create().start().resume();

        final Intent intent = ShadowApplication.getInstance().getNextStartedActivity();
        assertThat(intent.getComponent().getClassName()).isEqualTo(AuthenticatorActivity.class.getName());
    }

    @Test
    public void activity_setsSubtitleOnMultipleAccounts() {
        controller.create();
        addPinboardAccount("test");
        addPinboardAccount("test_2");

        controller.start().resume();

        final CharSequence subtitle = controller.get().getSupportActionBar().getSubtitle();
        assertThat(subtitle).isEqualTo("test_2");
    }

    private void addPinboardAccount(@NonNull String name) {
        final AccountManager am = AccountManager.get(RuntimeEnvironment.application);
        final Account account = new Account(name, Constants.ACCOUNT_TYPE);
        am.addAccountExplicitly(account, "password", null);
        ((PindroidApplication) RuntimeEnvironment.application).setUsername(name);
    }

    public static class TestActivity extends FragmentBaseActivity {

        @Override
        protected void startSearch(String query) {
            throw new IllegalStateException("Shouldn't happen in this test");
        }

        @Override
        protected void changeAccount() {
            // no-op in test
        }
    }
}