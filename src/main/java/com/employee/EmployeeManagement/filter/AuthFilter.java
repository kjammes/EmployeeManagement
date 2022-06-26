package com.employee.EmployeeManagement.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthFilter implements Filter {

    @Value("${CLIENT.ID}")
    String STATIC_CLIENT_ID;

    @Value("${CLIENT.SECRET}")
    String STATIC_CLIENT_SECRET;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("[AuthFilter] URL = {}",  request.getRequestURL());
        String CLIENT_ID = request.getHeader("CLIENT_ID");
        String CLIENT_SECRET = request.getHeader("CLIENT_SECRET");
        if (CLIENT_ID == null || CLIENT_SECRET == null) {
            response.sendError(403, "CLIENT_ID & CLIENT_SECRET not supplied");
            return;
        }

        CLIENT_ID = CLIENT_ID.trim();
        CLIENT_SECRET = CLIENT_SECRET.trim();
        if (CLIENT_ID.equals("") || CLIENT_SECRET.equals("")) {
            response.sendError(403, "CLIENT_ID & CLIENT_SECRET shouldn't be empty");
            return;
        }


        log.info("CLIENT_ID = {} & CLIENT_SECRET = {}", CLIENT_ID, CLIENT_SECRET);
        log.info("STATIC_CLIENT_ID = {} & STATIC_CLIENT_SECRET = {}", STATIC_CLIENT_ID, STATIC_CLIENT_SECRET);
        if (!CLIENT_ID.equals(STATIC_CLIENT_ID) && !CLIENT_SECRET.equals(STATIC_CLIENT_SECRET)) {
            response.sendError(403, "Invalid CLIENT_ID & CLIENT_SECRET");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
