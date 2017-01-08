package com.jobCenter.interceptor;

import com.kangzz.mtool.util.StrUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述：拦截入参
 * 作者 ：kangzz
 * 日期 ：2016-11-30 00:02:44
 */
public class CommonControllerInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = Logger.getLogger(CommonControllerInterceptor.class);
    
    protected ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
    
    public HttpServletResponse getResponse() {
		return response.get();
	}

	public void setResponse(HttpServletResponse response) {
		this.response.set(response);
	}
	
    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     *
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, Object handler) throws Exception {
		String  aa = request.getCharacterEncoding();
		//设置请求唯一值
		String uuid = request.getParameter("uuid");
		if (StrUtil.isNotBlank(uuid)) {
			String oldThreadName = Thread.currentThread().getName();
			StringBuilder tn = new StringBuilder();
			tn.append(oldThreadName).append("_uuid:").append(uuid);
			Thread.currentThread().setName(tn.toString());
		}else{
            String oldThreadName = Thread.currentThread().getName();
            StringBuilder tn = new StringBuilder();
            tn.append(oldThreadName).append("_uuid:").append(System.currentTimeMillis());
            Thread.currentThread().setName(tn.toString());
        }
		//打印请求日志
		StringBuffer logUrlSb = new StringBuffer(request.getRequestURI());
		//登录请求不能打印日志
		if(logUrlSb.indexOf("login.do") > -1){
			return true;
		}
		logUrlSb.append("?");
		Map<String, String[]> params = request.getParameterMap();
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				logUrlSb.append(key);
				logUrlSb.append("=");
				logUrlSb.append(value);
				logUrlSb.append("&");
			}
		}
		logger.info(logUrlSb.toString());
		return true;
	}



    /* (non-Javadoc)
    * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
    * javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
    *
    *
    */
    @Override
    public void postHandle(HttpServletRequest request,
						   HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
    }




    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, Object, Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        String oldThreadName = Thread.currentThread().getName();
        if ( oldThreadName.indexOf("_uuid:") > -1) {
            Thread.currentThread().setName(oldThreadName.substring(0,
                    oldThreadName.indexOf("_uuid:")));
        }

    }
}
