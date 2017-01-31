package dw2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class MyFilter implements ContainerRequestFilter, Filter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String headerString = requestContext.getHeaderString("test");
		System.out.println("FILTER:" + headerString);
		requestContext.getHeaders().putSingle("test", "MyNewHeader");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Do filter");
		HttpServletRequest r = (HttpServletRequest) request;
		
		HttpServletRequest r2 = new HttpServletRequestWrapper(r) {
			
			public String getHeader(String name) {
				if(name.equals("test")) return "MyNewHeader";
				else return super.getHeader(name);
			};
		};
		
		chain.doFilter(r2, response);
	}

	@Override
	public void destroy() {
		
	}

}
