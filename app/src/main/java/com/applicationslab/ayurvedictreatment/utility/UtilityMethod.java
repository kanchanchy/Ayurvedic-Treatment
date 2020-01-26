package com.applicationslab.ayurvedictreatment.utility;

import android.content.Context;
import android.net.ConnectivityManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 6/4/2016.
 */
public class UtilityMethod {

    public static boolean isNumberValid(String number) {
        boolean isValid = false;

        String expression = "^[0-9]*$";
        CharSequence inputStr = number;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean isDecimalNumberValid(String number) {
        boolean isValid = false;

        String expression = "^-?(0|[1-9]\\d*)(\\.\\d+)?$";
        CharSequence inputStr = number;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public boolean isEmailValid(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean isConnectedToInternet(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable() && conMgr
                .getActiveNetworkInfo().isConnected());
        return connected;

    }


}
