package ru.randscheduler.tools;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dimaMJ on 30.01.2018
 */
public class ServletUtils {

    private ServletUtils() {
    }

    @Nullable
    public static String getCookieVal(HttpServletRequest request, @NotNull String cookieName) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (cookieName.equals(cookie.getName())) return cookie.getValue();
        }

        return null;
    }

}
