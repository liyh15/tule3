package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 清理登录注册界面的浏览器缓存的拦截器
 * 
 * @author 李元浩
 */
public class LoginFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Pragrma", "no-cache");
		response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
