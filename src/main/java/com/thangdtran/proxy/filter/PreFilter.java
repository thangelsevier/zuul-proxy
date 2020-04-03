package com.thangdtran.proxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class PreFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String isFilter = request.getParameter("filter");
        return !StringUtils.isEmpty(isFilter) && isFilter.equals("true");
    }

    @Override
    public Object run() throws ZuulException {
        log.info("This is PreFilter");
        return null;
    }
}
