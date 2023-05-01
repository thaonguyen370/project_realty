package com.example.realtyservice.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_SSO_REGEX =
            Pattern.compile("^([0-9]{1,20})");

    public static boolean validateMail(String emailStr) {
        if (emailStr == null || emailStr.equals("")) {
            return true;
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateSso(String sso) {
        if (sso == null || sso.equals("")) {
            return true;
        }
        Matcher matcher = VALID_SSO_REGEX.matcher(sso);
        return matcher.find();
    }
}
