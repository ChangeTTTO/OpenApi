//package com.pn.config;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        // 在请求处理之前进行调用（Controller方法调用之前）
//        // 如果返回false，则请求不会到达Controller
//        // 获取当前会话是否已经登录，返回true=已登录，false=未登录
//
//        if (request.getSession().getAttribute("user")!=null) {
//            return true;
//        }else {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//            ModelAndView modelAndView) throws Exception {
//        // 在请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
//        System.out.println("Post Handle method is Calling");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
//        System.out.println("Request and Response is completed");
//    }
//}
