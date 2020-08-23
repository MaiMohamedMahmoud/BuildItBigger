package com.udacity.gradle.builditbigger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import javax.security.auth.callback.Callback;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.rule.ActivityTestRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(AndroidJUnit4.class)
public class JokesAsyncTaskTest {

//    @Rule
//    public ActivityTestRule<MainActivity> mainActivty = new ActivityTestRule<>(MainActivity.class);

    String result = null;
    int jokeArrIndex = 0;
    String[] jokeArr = new String[]{
            "You can't trust atoms......They make up everything!",
            "What do you call a cold dog?.....A chili dog!",
            "Why don't eggs tell each other jokes?....They'd crack each other up!",
            "How do you fix a broken tomato?...Tomato paste!",
            "Why did the hipster burn his tongue on coffee?...Because he drank it before it was cool!",
            "Why did the farmer win an award.....He was out standing in his field!",
            "What do sea monsters eat?....Fish and ships!",
            "What did the daddy tomato say to the baby tomato?...Ketchup!",
            "What did the ocean say to the shore?.....Nothing, it just waved!",
            "What's a plant's favorite drink?.....Root beer!"};

    @Test
    public void DisplayJokeTest() {
        final CountDownLatch signal = new CountDownLatch(1);

        JokesAsyncTask mJokesAsyncTask = new JokesAsyncTask(new JokesListener() {
            @Override
            public void onLoad(String joke) {
                result = joke;
                if(Arrays.asList(jokeArr).contains(result)) {
                    jokeArrIndex = Arrays.asList(jokeArr).indexOf(result);
                }
                signal.countDown();
            }
        });
        mJokesAsyncTask.execute();

        try {
            signal.await();
            assertFalse(result.isEmpty());
            assertEquals(jokeArr[jokeArrIndex],result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
