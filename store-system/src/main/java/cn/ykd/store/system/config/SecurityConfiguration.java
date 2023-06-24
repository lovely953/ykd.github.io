package cn.ykd.store.system.config;

import cn.ykd.store.system.filter.JwtAuthorizationFilter;
import cn.ykd.store.system.web.JsonResult;
import cn.ykd.store.system.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
   private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //处理“需要通过认证，但是实际上未通过认证就发起请求导致的错误”
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                String message = "未检测到登录信息，请登录(在开发阶段看见此提示，请检查客户端是否携带jwt向服务端发起请求)";
                JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.ERR_UNAUTHORIZED,message);
                response.setContentType("application/json,charset=utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.println(JSON.toJSONString(jsonResult));
                printWriter.close();
            }
        });



        //白名单
        /**
         * 使用一个星号，可以匹配此层级的任意资源，例如：/admin/*,可以匹配/admin/delete,/admin/add-new
         * 但是不可以匹配多个层级，例如：/admin/*，不可以匹配/admin/9527/delete
         * 使用2个连续的星号，表示通配任何层级的任意资源，例如：/admin/**,可以匹配/admin/delete,/admin/9527/delete
         */
        String[] urls ={
                "/admins/login",
//                "/brands/**",
//                "/albums/**",
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs"

        };
        //解决复杂请求的跨域问题
        http.cors();
        //禁用伪造的跨域攻击这种机制
        http.csrf().disable();


        //配置URL的访问控制
        http.authorizeRequests()//配置URL的访问控制
               // .mvcMatchers(HttpMethod.OPTIONS,"/**")
              //  .permitAll()
                .mvcMatchers(urls)//匹配某些URL
                .permitAll()//直接许可不需要认证，直接访问
                .anyRequest()//任何请求
                .authenticated();//以上配置的请求是通过认证的

       http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        //调用formLogin()表示启用登录表单和登出，如果未调用此方法就没有登录表单和登出
        //如果未调用此方法，则没有登录表单页和登出页，且当视为”未通过认证时“将响应403
//        http.formLogin();
    }
}
