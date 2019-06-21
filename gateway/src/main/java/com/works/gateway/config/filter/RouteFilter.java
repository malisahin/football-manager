package com.works.gateway.config.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * @author mali.sahin
 * @since 2019-06-21.
 */
public class RouteFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "Route Filter";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("Inside Route Filter");
        return null;
    }
}
