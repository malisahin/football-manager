package com.works.gateway.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author mali.sahin
 * @since 2019-06-21.
 */
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "Post Filter";
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
    public Object run() throws ZuulException {
        System.out.println("Inside Post Filter");
        return null;
    }
}
