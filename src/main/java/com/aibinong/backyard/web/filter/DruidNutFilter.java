package com.aibinong.backyard.web.filter;

import org.nutz.mvc.NutFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ouwa on 16/2/17.
 */
public class DruidNutFilter extends NutFilter {
    protected Set<String> prefixs = new HashSet<String>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.indexOf(prefix) > 0) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        super.doFilter(req, resp, chain);
    }

    @Override
    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        prefixs.add("/druid/");
        prefixs.add("/assets/");
    }
}
