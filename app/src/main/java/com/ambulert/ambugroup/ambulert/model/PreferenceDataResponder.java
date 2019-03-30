package com.ambulert.ambugroup.ambulert.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceDataResponder {

    static final String pref_responderid = "responder_id";
    static final String pref_firstame = "responder_firstname";
    static final String pref_middlename = "responder_middlename";
    static final String pref_lastname = "responder_lastname";
    static final String pref_status = "responder_status";

    public static SharedPreferences getSharedPreferences(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    //  setting preference
    public static void setLoggedInResponderId(Context ctx, String responderid)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_responderid);
        editor.putString(pref_responderid, responderid);
        editor.commit();
    }
    public static void setLoggedInResponderFirstname(Context ctx, String firstname)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_firstame);
        editor.putString(pref_firstame, firstname);
        editor.commit();
    }
    public static void setLoggedInResponderMiddlename(Context ctx, String middlename)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_middlename);
        editor.putString(pref_middlename, middlename);
        editor.commit();
    }
    public static void setLoggedInResponderLastname(Context ctx, String lastname)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_lastname);
        editor.putString(pref_lastname, lastname);
        editor.commit();
    }

    //    getting shared preferences
    public static String getLoggedInResponderId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_responderid, "");
    }
    public static String getLoggedInResponderFirstname(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_firstame, "");
    }
    public static String getLoggedInResponderMiddlename(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_middlename, "");
    }
    public static String getLoggedInResponderLastname(Context ctx)
    {
        return getSharedPreferences(ctx).getString(pref_lastname, "");
    }

    //  status
    public static void setResponderLoggedInStatus(Context ctx, boolean status)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_status);
        editor.putBoolean(pref_status, status);
        editor.commit();
    }

    public static boolean getResponderLoggedInStatus(Context ctx)
    {
        return getSharedPreferences(ctx).getBoolean(pref_status, false);
    }

    //    clearing preferences
    public static void clearLoggedInResponder(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(pref_responderid);
        editor.remove(pref_firstame);
        editor.remove(pref_middlename);
        editor.remove(pref_lastname);

        editor.commit();
    }
}
