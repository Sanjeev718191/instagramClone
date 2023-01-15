package com.example.instagramclone;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class StarterApplication  extends Application {

//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(new Parse.Configuration.Builder(this)
//                .applicationId("myappID")
//                // if defined
//                .clientKey("WawPNtdfhX4u")
//                .server("http:ec2-18-183-178-249.ap-northeast-1.compute.amazonaws.com/parse/")
//                .build()
//        );
//
//        ParseObject object = new ParseObject("ExampleObject");
//        object.put("myNumber", "123");
//        object.put("myString", "rob");
//
//        object.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException ex) {
//                if (ex == null) {
//                    Log.i("Parse Result", "Successssssssssssssssful!");
//                } else {
//                    Log.i("Parse Result", "Faaaaaaaaaaaaaaaaaaaailed" + ex.toString());
//                }
//            }
//        });
//
//        ParseUser.enableAutomaticUser();
//
//        ParseACL defaultACL = new ParseACL();
//        defaultACL.setPublicReadAccess(true);
//        defaultACL.setPublicWriteAccess(true);
//        ParseACL.setDefaultACL(defaultACL, true);
//    }
}
