package vn.rikkeisoft.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Xac dinh day la lop dung de cau hinh
@Configuration
//Tich hop Spring Security voi Spring Boot
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // inject de cau hinh
    @Autowired
    private DomainUserDetailsService domainUserDetailsService;

    // De su dung duoc PasswordEncoder, phai cau hinh de PasswordEncoder thanh 1 Bean
    // Ma hoa mat khau bang thuat toan BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Set sevice find user in Database
        // Set password follow PasswordEncoder.
        auth.userDetailsService(domainUserDetailsService).passwordEncoder(passwordEncoder());

    }

    //Cau hinh chi tiet ve bao mat
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Vo hieu hoa yeu cau gia mao
                .csrf().disable()
                //Phan quyen request
                //antMatchers() Khai bao duong dan cua request
                //permitAll() Cho tat ca User duoc truy cap
                //hasAnyRole(roleName) Chi cho phep cac user co GrantedAuthority la roleName moi truy cap
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/registration").permitAll()
                .antMatchers("/","/products/").hasAnyRole("MEMBER", "ADMIN")
                .antMatchers("/admin","/accounts/","/orders/").hasAnyRole("ADMIN")
                .and()
                //Dang nhap
                .formLogin()
                //Duong dan den trang dang nhap
                .loginPage("/login")
                //Gia tri thuoc tinh name cua 2 input username va pass
                .usernameParameter("username")
                .passwordParameter("password")
                //Duong dan khi login thanh cong
                .defaultSuccessUrl("/accounts/")
                //Duong dan khi login that bai
                .failureUrl("/login?error")
                .and()
                //Cau hinh cho Logout Page
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                //Nguoi dung ko co quyen truy cap => redirect ve trang 403
                .csrf().disable().exceptionHandling().accessDeniedPage("/403");
    }

    //Cho phep de mo Css, Js, Image trong resources
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }
}
