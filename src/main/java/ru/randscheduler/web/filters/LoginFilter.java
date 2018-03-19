package ru.randscheduler.web.filters;

import com.google.common.collect.Sets;
import org.springframework.web.bind.ServletRequestUtils;
import ru.randscheduler.tools.ServletUtils;
import ru.randscheduler.tools.UserCookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by dimaMJ on 29.01.2018
 */
@WebFilter
public class LoginFilter implements Filter {

    private static final Set<String> ignoreUri = Sets.newHashSet("/login", "/static");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;

            String requestUri = request.getRequestURI().toLowerCase();
            if (!UserCookieUtils.hasUserCookie(request) && ignoreUri.stream().filter(requestUri::startsWith).count() == 0) {
                ((HttpServletResponse) servletResponse).sendRedirect("/login");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
