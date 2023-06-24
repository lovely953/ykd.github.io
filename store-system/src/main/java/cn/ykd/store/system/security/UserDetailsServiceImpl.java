package cn.ykd.store.system.security;

import cn.ykd.store.system.mapper.AdminMapper;
import cn.ykd.store.system.pojo.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 处理认证框架的一个组件
     */
    @Autowired
    private AdminMapper adminMapper;



//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        //假设存在可用的账号信息：用户名（root） 密码（123456）
//        if ("root".equals(s)) {
//            UserDetails userDetails = User.builder()
//                    .username("root")
//                    .password("123456")
//                    .disabled(false)//禁用
//                    .accountLocked(false)//账号是否锁定
//                    .accountExpired(false)//账号是否过期
//                    .authorities("暂时给个山寨权限，暂时没有防止报错")
//                    .build();
//            return userDetails;
//        }
//        return null;
//    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //使用参数S作为参数，调用AdminMapper里面的getAdminInfoByUsername
        //判断查询结果是否为null
        log.debug("Spring Security调用了loadUserByUsername()方法，参数{}",s);
        AdminLoginInfoVO loginInfo = adminMapper.getLoginInfoByUsername(s);
        log.debug("从数据库查询用户名{}匹配的信息，结果:{}",s,loginInfo);
        //是：无此用户名对应的账号信息，返回null
        if (loginInfo == null) {
            return null;//暂时
        }


        List<String> permissions = loginInfo.getPermissions();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }

        AdminDetails adminDetails = new AdminDetails(
                loginInfo.getId(),
                loginInfo.getUsername(),
                loginInfo.getPassword(),
                loginInfo.getEnable() == 1,
                authorities
        );
        log.debug("即将向spring Security返回AdminDetails对象:{}",adminDetails);
        return adminDetails;
        //返回UserDetails对象
        //username来自查询结果
        //password（暂时不能用密码为123456）来自查询结果
        //disable:来自查询结果中的enable，判断enable是否为0
        //accountExpired等：参考之前的demo,将各值写死
    }

}
