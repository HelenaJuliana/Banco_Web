package com.ifpb.pdist.bancoweb.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ifpb.pdist.bancoweb.model.Correntista;

@WebFilter(filterName = "LoginFilter", urlPatterns = { "/contas/*", "/correntistas/*" })
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession(false);

		String uri = httpRequest.getRequestURI();

		if (httpSession != null) {
			Correntista loginUser = (Correntista) httpSession.getAttribute("usuario");
			if (loginUser == null) {
				redirectLogin(httpRequest, httpResponse, uri);
				return;
			} else {
				chain.doFilter(request, response);
			}
		} else {
			redirectLogin(httpRequest, httpResponse, uri);
		}

	}

	private void redirectLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String uri)
			throws IOException {
		String baseUrl = httpRequest.getContextPath();
		String paginaLogin = baseUrl + "/login";
		httpResponse.sendRedirect(httpResponse.encodeRedirectURL(paginaLogin));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
