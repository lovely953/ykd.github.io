package cn.ykd.store.system.filter;

import cn.ykd.store.system.security.LoginPrincipal;
import cn.ykd.store.system.web.JsonResult;
import cn.ykd.store.system.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import jdk.nashorn.internal.ir.RuntimeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
//通用组件

/**
 * 处理jwt的过滤器类
 * <p>
 * 此类的主要职责
 * 接受客户端可能提交的JWT
 * 尝试解析客户端提交的JWT
 * 将解析得到的结果存入到SecurityContext中
 */
@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    @Value("${ykd.jwt.secret-key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 清除SecurityContext中的数据
        SecurityContextHolder.clearContext();

        //根据业内惯用的做法，客户端应该将JWT保存在请求头中（request header）中的名为Authorization的属性中
        String jwt = request.getHeader("Authorization");
        log.debug("尝试接收客户端携带的JWT数据,JWT:{}", jwt);
        //判断客户端是否提交了有效的jwt
        if (!StringUtils.hasText(jwt) || jwt.length() < 113) {
            //直接放行
            filterChain.doFilter(request, response);
            //重要，结束方法
            return;
        }

        //尝试解析jwt
        //问题二：解析过程中可能抛出异常
        Claims claims = null;
        try {
                claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(jwt)
                        .getBody();
        } catch (UnsupportedJwtException e) {
                e.printStackTrace();
        } catch (ExpiredJwtException e) {
            response.setContentType("application/json;charset=utf-8");
            Integer code = 8000;
            String message = "登录过期重新登陆！";
            log.warn("解析jwt出现问题,消息：{}",message);
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED, message);
            PrintWriter printwriter = response.getWriter();
            printwriter.println(JSON.toJSON(jsonResult));
            printwriter.close();
            return;
        }  catch (MalformedJwtException e) {
            response.setContentType("application/json;charset=utf-8");
            Integer code = 8000;
            String message = "非法访问！";
            log.warn("解析jwt出现问题,消息：{}",message);
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_MALFORMED, message);
            PrintWriter printwriter = response.getWriter();
            printwriter.println(JSON.toJSON(jsonResult));
            printwriter.close();
            return;
        } catch (SignatureException e) {
            response.setContentType("application/json;charset=utf-8");
            Integer code = 8000;
            String message = "非法访问！";
            log.warn("解析jwt出现问题,消息：{}",message);
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_SIGNATURE, message);
            PrintWriter printwriter = response.getWriter();
            printwriter.println(JSON.toJSON(jsonResult));
            printwriter.close();
            return;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        Long id = claims.get("id",Long.class);
        String username = claims.get("username",String.class);
        String authoritiesJsonString = claims.get("authoritiesJsonString", String.class);
        log.debug("从jwt中解析的管理员id:{}", id);
        log.debug("从jwt中解析的管理员username:{}", username);
        log.debug("从JWT中解析得到的管理员权限列表JSON：{}", authoritiesJsonString);

        //将json格式的权限列表转换成Authentication需要的类型（ Collection<GrantedAuthority>）
        List<SimpleGrantedAuthority> authorities =
                JSON.parseArray(authoritiesJsonString, SimpleGrantedAuthority.class);
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("暂时给个山寨权限");
        authorities.add(simpleGrantedAuthority);
        //基于解析JWT的结果创建认证信息
        //问题三：是否应该使用“用户名”那么简单的数据作为当事人
        //问题四：如何得到当前管理员的权限
        LoginPrincipal principal = new LoginPrincipal();
        principal.setId(id);
        principal.setUsername(username);
        Object credentials = null;//应该为null
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                credentials, authorities);

        //将解析得到的结果存入到SecurityContext中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);



        //过滤器链继承向后执行，即：放行
        //如果没有执行如下代码，表示“阻止”，即此请求的处理过程结束，在浏览器中显示一片空白
        filterChain.doFilter(request, response);
    }
}
