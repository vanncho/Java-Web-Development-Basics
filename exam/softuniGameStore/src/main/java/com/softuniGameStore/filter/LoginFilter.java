package com.softuniGameStore.filter;

import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.entities.enumerations.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/games", "/delete/*", "/edit/*", "/edit/*", "/add-game"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        UserLoginModel userLoginModel = (UserLoginModel) session.getAttribute("loggedUser");

        if(userLoginModel == null || userLoginModel.getRole() != Role.ADMIN){

            ((HttpServletResponse)servletResponse).sendRedirect("/");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
