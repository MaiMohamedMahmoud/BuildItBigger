package com.udacity.gradle.builditbigger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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


    @Test
    public void DisplayJokeTest() {
        final CountDownLatch signal = new CountDownLatch(1);

        JokesAsyncTask mJokesAsyncTask = new JokesAsyncTask(new JokesListener() {
            @Override
            public void onLoad(String joke) {
                result = joke;
                signal.countDown();
            }
        });
        mJokesAsyncTask.execute();

        try {
            signal.await();
            assertFalse(result.isEmpty());
           } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
