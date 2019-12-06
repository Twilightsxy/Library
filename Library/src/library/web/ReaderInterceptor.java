package library.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_EXCLUSIONPeer;

import library.domain.Reader;

public class ReaderInterceptor implements HandlerInterceptor {

	private final static Log log = LogFactory.getLog(ReaderInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		log.debug("3.Called after rendering the view");

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		log.debug("2.Called after handler method request completion, before rendering the view");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		log.debug("1.Called before handler method");
		// 获取session
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute("reader");
		// 判断session中是否有用户数据，如果有，则返回true，继续向下执行
		if (reader != null && reader.getReaderNo() != null) {
			return true;
		}
		// 不符合条件的转发到登录页面
		String url = request.getContextPath();
		response.sendRedirect(url + "/login/reader");
		return false;
	}

}
