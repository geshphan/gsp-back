package yemenshi.gsp.todo_list.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
public class AuthnInterceptor implements HandlerInterceptor {

    private static final int SESSION_TIME_LIMIT = 300;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (isLoggedIn(response, session)) {
            return false;
        }

        if (isSessionExpired(response, session)) {
            return false;
        }

        refreshLastAccessTime(session);

        return true;
    }

    private static void refreshLastAccessTime(HttpSession session) {
        session.setAttribute("accessTimeMillis", LocalDateTime.now());
    }

    private static boolean isSessionExpired(HttpServletResponse response, HttpSession session) throws IOException {
        LocalDateTime lastAccessTime = (LocalDateTime) session.getAttribute("accessTimeMillis");
        long duration = Duration.between(lastAccessTime, LocalDateTime.now()).getSeconds();
        boolean isExpiredSession = duration >= SESSION_TIME_LIMIT;
        if (isExpiredSession) {
            session.invalidate();
            setResponseForDeniedAccess(response);

            return true;
        }
        return false;
    }

    private static boolean isLoggedIn(HttpServletResponse response, HttpSession session) throws IOException {
        if (session.getAttribute("user") == null) {
            setResponseForDeniedAccess(response);

            return true;
        }
        return false;
    }

    private static void setResponseForDeniedAccess(HttpServletResponse response) throws IOException {
        ObjectMapper om = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(om.writeValueAsString("Not logged in or session has been invalidated."));
    }
}
