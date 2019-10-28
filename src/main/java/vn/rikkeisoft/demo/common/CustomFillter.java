package vn.rikkeisoft.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static vn.rikkeisoft.demo.common.Utils.getUserDetailsSecurityContext;

public class CustomFillter extends GenericFilterBean {

    @Autowired
    private DomainUserDetailsService userDetailsService;

    public CustomFillter(DomainUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails userDetails = getUserDetailsSecurityContext();

        if (userDetails != null) {
            try {
                UserDetails details = userDetailsService.loadUserByUsername(userDetails.getUsername());
                if (!details.getAuthorities().containsAll(userDetails.getAuthorities())
                        || userDetails.getAuthorities().size() != details.getAuthorities().size()) {
                    new SecurityContextLogoutHandler().logout(req, res, securityContext.getAuthentication());
                }
            } catch (Exception e) {
                new SecurityContextLogoutHandler().logout(req, res, securityContext.getAuthentication());
            }
        }
        chain.doFilter(req, res);
    }
}
