package com.thangdtran.proxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

public class PreParamsFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("This is PreParamsFilter");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String isLog = request.getParameter("log");
        if (!StringUtils.isEmpty(isLog) && isLog.equals("true")) {
            Map<String, String[]> params = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                log.info(String.format("%s : %s", entry.getKey(), Arrays.toString(entry.getValue())));
            }
        }
        return null;
    }
}
