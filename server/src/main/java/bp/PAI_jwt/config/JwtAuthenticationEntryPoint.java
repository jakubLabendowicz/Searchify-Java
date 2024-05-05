package bp.PAI_jwt.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;
    private static final int UNAUTHORIZED_STATUS_CODE = HttpServletResponse.SC_UNAUTHORIZED;
    private static final String UNAUTHORIZED_MESSAGE = "Unauthorized";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        sendUnauthorizedResponse(response);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED_STATUS_CODE, UNAUTHORIZED_MESSAGE);
    }
}
