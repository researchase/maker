package com.mechatronase.maker;

/**
 * Created by ashwin on 26/12/15.
 */

import com.parse.Parse;



public class Application extends android.app.Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ta8TGXoWA3rJoyiJ7YX7KUCNT";
    private static final String TWITTER_SECRET = "FsTGTzmA2nmd6cEnSXNPZb7AYcx7k3z699vMzwhYp7MN0babgl";


    @Override
    public void onCreate() {
        super.onCreate();
         Parse.initialize(this);
    }

}