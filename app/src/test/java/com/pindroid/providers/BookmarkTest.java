package com.pindroid.providers;

import com.pindroid.providers.BookmarkContent.Bookmark;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Date;

import static com.artemzin.assert_parcelable.AssertParcelable.assertThatObjectParcelable;

@RunWith(RobolectricTestRunner.class)
public class BookmarkTest {
    @Test
    public void bookmark_isParcelable() {
        final Bookmark bookmark = new Bookmark();
        bookmark.setUrl("http://pindroid.in");
        bookmark.setDescription("Pindroid!");
        bookmark.setShared(true);
        bookmark.setToRead(false);
        bookmark.setTime(new Date().getTime());
        bookmark.setTagString("tag1, tag2");
        bookmark.setAccount("test_account");

        assertThatObjectParcelable(bookmark);
    }
}