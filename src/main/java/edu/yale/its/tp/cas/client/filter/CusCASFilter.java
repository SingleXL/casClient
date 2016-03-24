package edu.yale.its.tp.cas.client.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.yale.its.tp.cas.client.filter.CASFilter;

public class CusCASFilter extends CASFilter {
	
	private String  excludeUrls;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		excludeUrls = config.getInitParameter("edu.yale.its.tp.cas.client.filter.excludeUrls");
		super.init(config);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws ServletException, IOException {
		
		if (excludeUrls != null) {

			String realURl = ((HttpServletRequest) request).getRequestURI();

			boolean isExit = false;
			String[] urls = excludeUrls.split(";");
			for (String url : urls) {
				if (realURl.contains(url)) {
					isExit = true;
					break;
				}
			}

			if (isExit) {
				fc.doFilter(request, response);
				return;
			}

		}
		super.doFilter(request, response, fc);
	}

}
