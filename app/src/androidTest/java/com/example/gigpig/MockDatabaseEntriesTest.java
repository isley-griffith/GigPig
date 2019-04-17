package com.example.gigpig;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MockDatabaseEntriesTest {

    /**
     * When hard coding values, database does not behaive the same, i.e this could break sorting
     * algorithms
     */
    @Test
    public void createMockDataBaseEntries() {
        User user = new User();
        ArrayList<String> tags = new ArrayList<>();
        tags.add("goat");
        tags.add("clean");

        Job job = new Job("Goats",
                "database: Need someone who is experienced with goat care to work one day cleaning stalls" +
                        ", feeding etc.",
                50.0, user, tags, null);

        DatabaseHelper.writeNewJob(job);

        tags = new ArrayList<>();
        tags.add("line");
        tags.add("long");

        job = new Job("Long job",
                "database: This is to text a long job with multiple lines\n" +
                        "Here's another line\n" +
                        "another one\n" +
                        "the idea is that the cells will adjust their size automatically based on the" +
                        "size of the job posting",
                12.0, user, tags, null);

        DatabaseHelper.writeNewJob(job);

        tags = new ArrayList<>();
        tags.add("find");
        tags.add("cats");
        tags.add("lost");

        job = new Job("Lost cat",
                "database: REWARD: Need someone to find my cat. Lost somewhere in monument creek." +
                        "He responds to Jimmy",
                100.0, user, tags, null);

        DatabaseHelper.writeNewJob(job);

        tags = new ArrayList<>();
        tags.add("an");
        tags.add("nothing");

        job = new Job("A nothing thing",
                "database: Test sort alphabetically",
                0.0, user, tags, null);

        DatabaseHelper.writeNewJob(job);

        tags = new ArrayList<>();
        tags.add("clean");
        tags.add("windex");

        job = new Job("Clean with my cleaning supplies",
                "database: Need someone with a lot of cleaning experience to clean my kitchen floor" +
                        "\n I will supply cleaning materials",
                20.0, user, tags, null);

        DatabaseHelper.writeNewJob(job);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.gigpig", appContext.getPackageName());
    }
}
