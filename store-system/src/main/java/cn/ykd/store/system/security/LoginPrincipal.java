package cn.ykd.store.system.security;

import lombok.Data;

import java.io.Serializable;

/**
 * 认证信息当事人类型，就是成功通过认证的用户信息
 */
@Data
public class LoginPrincipal implements Serializable {
    /**
     * 当事人id
     */
    private Long id;
    /**
     * 当事人用户名
     */
    private String username;
}
