package com.marscode.pwn.javajoker;

import java.util.Random;

public class Joke {

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

    public String getJoke() {
        String randomStr = jokeArr[new Random().nextInt(jokeArr.length)];
        return randomStr;
    }
}