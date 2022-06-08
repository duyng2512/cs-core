package com.dng.cs.core.security.filter;

import com.dng.cs.core.exception.NoApiKeyException;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    static Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("api_key") == null) {

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print(gson.toJson(Map.of("message", "api_key not found")));
            response.getWriter().flush();

        } else {
            filterChain.doFilter(request, response);
        }
    }
}
