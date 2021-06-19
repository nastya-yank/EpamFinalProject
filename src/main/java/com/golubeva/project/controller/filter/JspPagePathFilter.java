package com.golubeva.project.controller.filter;

import com.golubeva.project.controller.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code JspPagePathFilter} class represents jsp page path filter.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class JspPagePathFilter implements Filter {
    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX);
    }

    @Override
    public void destroy() {
    }
}
