package com.ambulert.ambugroup.ambulert.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceDataUser {
    static final String pref_userid = "user_lawyerid";
    static final String pref_firstame = "user_firstname";
    static final String pref_middlename = "user_middlename";
    static final String pref_lastname = "user_lastname";
    static final String pref_email = "user_email";
    static final String pref_status = "user_status";
    static final String pref_fcm = "user_fcm";

    public static SharedPreferences getSharedPreferences(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    //  setting preference
    public static void setLoggedInUserid(Context ctx, String userid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_userid);
        editor.putString(pref_userid, userid);
        editor.commit();
    }
    public static void setLoggedInFirstname(Context ctx, String firstname)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_firstame);
        editor.putString(pref_firstame, firstname);
        editor.commit();
    }
    public static void setLoggedInMiddlename(Context ctx, String middlename)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_middlename);
        editor.putString(pref_middlename, middlename);
        editor.commit();
    }
    public static void setLoggedInLastname(Context ctx, String lastname)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_lastname);
        editor.putString(pref_lastname, lastname);
        editor.commit();
    }
    public static void setLoggedInEmail(Context ctx, String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_email);
        editor.putString(pref_email, email);
        editor.commit();
    }

    public static void setLoggedInFCM(Context ctx, String fcm)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_fcm);
        editor.putString(pref_fcm, fcm);
        editor.commit();
    }

    //    getting shared preferences
    public static String getLoggedInUserid(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_userid, "");
    }
    public static String getLoggedInFirstname(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_firstame, "");
    }
    public static String getLoggedInMiddlename(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_middlename, "");
    }
    public static String getLoggedInLastname(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_lastname, "");
    }
    public static String getLoggedInEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_email, "");
    }
    public static String getLoggedInFCM(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_fcm, "");
    }

    //  status
    public static void setUserLoggedInStatus(Context ctx, boolean status)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_status);
        editor.putBoolean(pref_status, status);
        editor.commit();
    }

    public static boolean getUserLoggedInStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getBoolean(pref_status, false);
    }

    //    clearing preferences
    public static void clearLoggedInUser(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_userid);
        editor.remove(pref_firstame);
        editor.remove(pref_middlename);
        editor.remove(pref_lastname);
        editor.remove(pref_email);
        editor.remove(pref_status);
        editor.remove(pref_fcm);

        editor.commit();
    }
}
