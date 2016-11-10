package top.kass.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        String prefix = request.getContextPath();

        String[] IGNORE_URI = {prefix + "/login", prefix + "/register"};
        String[] UNLOGIN_URI = {prefix + "/login", prefix + "/register"};

        HttpSession session = request.getSession();

        if (url.startsWith(prefix + "/api")) {
            if (url.equals(prefix + "/api/auth/login") ||
                    url.equals(prefix + "/api/auth/register") ||
                    url.equals(prefix + "/api/auth/unlogin")) {
                return true;
            }
            if (session.getAttribute("id") == null) {
                response.sendRedirect(prefix + "/api/auth/unlogin");
                return false;
            }
            return true;
        }

        if (session.getAttribute("id") != null) {
            for (String s : UNLOGIN_URI) {
                if (url.equals(s)) {
                    response.sendRedirect(prefix + "/");
                    return false;
                }
            }

        } else {
            boolean flag = false;
            for (String s : IGNORE_URI) {
                if (url.equals(s)) {
                    flag = true;
                }
            }
            if (!flag) {
                response.sendRedirect(prefix + "/login");
                return false;
            }
        }
        return true;
    }

}
