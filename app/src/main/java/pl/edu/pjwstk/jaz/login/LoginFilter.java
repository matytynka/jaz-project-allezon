package pl.edu.pjwstk.jaz.login;

import pl.edu.pjwstk.jaz.users.UserSession;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("*")
public class LoginFilter extends HttpFilter {

    @Inject
    UserSession userSession;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        boolean CSSExists = req.getRequestURI().contains(".css");

        if (req.getRequestURI().contains("editsection.xhtml")) {
            if (userSession.loggedUser()) {
                if (userSession.getProfile().getUsername().equals("admin")) {
                    chain.doFilter(req, res);
                } else {
                    res.sendRedirect(req.getContextPath()+"/login.xhtml");
                }
            } else {
                res.sendRedirect(req.getContextPath()+"/accessdenied.xhtml");
            }
        } else {

            if (userSession.loggedUser() || req.getRequestURI().contains("login.xhtml") || req.getRequestURI().contains("register.xhtml") || req.getRequestURI().contains("addauction.xhtml") || req.getRequestURI().contains("/api") || CSSExists) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
            }
        }
    }
}