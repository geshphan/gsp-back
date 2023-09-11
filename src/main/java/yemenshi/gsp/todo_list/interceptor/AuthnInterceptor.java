package yemenshi.gsp.todo_list.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AuthnInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        boolean isLoggedIn = session.getAttribute("user") != null;
        // FIXME: If session exists, compare between user information in session and request body.

        if (!isLoggedIn) {
            ObjectMapper om = new ObjectMapper();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(om.writeValueAsString("Not logged in or session has been invalidated."));

            return false;
        }

        return true;
    }
}
