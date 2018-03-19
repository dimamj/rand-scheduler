package ru.randscheduler.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by dimaMJ on 31.01.2018
 */
public class UserCookieUtils {

    public static final String COOKIE_ID = "rs-id";

    public static Cookie createUserCookie(String val) {
        Cookie cookie = new Cookie(COOKIE_ID, val);
        cookie.setPath("/");
        cookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(365));
        return cookie;
    }

    public static boolean hasUserCookie(HttpServletRequest request) {
        return ServletUtils.getCookieVal(request, COOKIE_ID) != null;
    }

    public static String getUserIdVal(HttpServletRequest request) {
        return ServletUtils.getCookieVal(request, COOKIE_ID);
    }
}
