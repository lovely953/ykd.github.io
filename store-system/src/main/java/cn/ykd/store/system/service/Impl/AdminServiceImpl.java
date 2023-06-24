package cn.ykd.store.system.service.Impl;


import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.AdminMapper;
import cn.ykd.store.system.mapper.AdminRoleMapper;
import cn.ykd.store.system.pojo.dto.AdminAddNewDTO;
import cn.ykd.store.system.pojo.dto.AdminLoginDTO;
import cn.ykd.store.system.pojo.entity.Admin;
import cn.ykd.store.system.pojo.entity.AdminRole;
import cn.ykd.store.system.pojo.vo.AdminListItemVO;
import cn.ykd.store.system.pojo.vo.AdminStandardVO;
import cn.ykd.store.system.security.AdminDetails;
import cn.ykd.store.system.service.IAdminService;
import cn.ykd.store.system.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.internal.dynalink.support.NameCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 处理管理员数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Value("${ykd.jwt.secret-key}")
    private String secretKey;
    @Value("${ykd.jwt.duration-in-minute}")
    private long durationInMinute;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AdminServiceImpl() {
        log.debug("创建业务对象：AdminServiceImpl");
    }

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理【添加管理员】的业务，参数：{}", adminLoginDTO);
        //执行认证
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                adminLoginDTO.getUsername(), adminLoginDTO.getPassword());
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("通过认证，结果：{}", authenticateResult);

        //将通过认证的管理员的相关信息存入到JWt中
        //准备生成JWT的相关数据
        Date date = new Date(System.currentTimeMillis() + durationInMinute * 60 * 1000);
        AdminDetails principal = (AdminDetails) authenticateResult.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",principal.getId());
        claims.put("username",principal.getUsername());
        //权限列表转换成JSON格式的字符串
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        String authoritiesJsonString = JSON.toJSONString(authorities);
        log.debug("权限列表，字符串值：{}",authorities);
        log.debug("权限列表，json结果：{}",authoritiesJsonString);
        claims.put("authoritiesJsonString",authoritiesJsonString);
        //生成jwt
        String jwt = Jwts.builder()
                //header
                .setHeaderParam("alg","HS256")
                .setHeaderParam("typ","JWT")
                //playload
                .setClaims(claims)
                //Signature
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                //完成
                .compact();
        //返回JWT
        log.debug("返回JWT数据，JWT：{}",jwt);
        return jwt;
    }
    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理【添加管理员】的业务，参数：{}", adminAddNewDTO);

        // 检查角色ID的数组中是否包含1号角色
        Long[] roleIds = adminAddNewDTO.getRoleIds();
        for (int i = 0; i < roleIds.length; i++) {
            if (roleIds[i] == 1) {
                String message = "添加管理员失败，非法访问！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        {
            // 从参数对象中取出用户名
            String username = adminAddNewDTO.getUsername();
            // 调用adminMapper.countByUsername()执行统计
            int count = adminMapper.countByUsername(username);
            // 判断统计结果是否大于0
            if (count > 0) {
                // 是：抛出异常（ERR_CONFLICT）
                String message = "添加管理员失败，尝试使用的用户名已经被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        {
            // 从参数对象中取出手机号码
            String phone = adminAddNewDTO.getPhone();
            // 调用adminMapper.countByPhone()执行统计
            int count = adminMapper.countByPhone(phone);
            // 判断统计结果是否大于0
            if (count > 0) {
                // 是：抛出异常（ERR_CONFLICT）
                String message = "添加管理员失败，尝试使用的手机号码已经被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        {
            // 从参数对象中取出电子邮箱
            String email = adminAddNewDTO.getEmail();
            // 调用adminMapper.countByEmail()执行统计
            int count = adminMapper.countByEmail(email);
            // 判断统计结果是否大于0
            if (count > 0) {
                // 是：抛出异常（ERR_CONFLICT）
                String message = "添加管理员失败，尝试使用的电子邮箱已经被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 创建Admin对象
        Admin admin = new Admin();
        // 复制参数DTO对象中的属性到实体对象中
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        // 设置初始登录次数
        admin.setLoginCount(0);
        // 处理密码加密
        String rawPassword = admin.getPassword();

        String encodedPassword = passwordEncoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        // 调用adminMapper.insert()方法插入管理员数据
        int rows = adminMapper.insert(admin);
        if (rows != 1) {
            String message = "添加管理员失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

//        // 向管理员与角色的关联表中批量插入关联数据
        AdminRole[] adminRoleList = new AdminRole[roleIds.length];
        for (int i = 0; i < adminRoleList.length; i++) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            adminRole.setRoleId(roleIds[i]);
            adminRoleList[i] = adminRole;
        }
        rows = adminRoleMapper.insertBatch(adminRoleList);
        if (rows != adminRoleList.length) {
            String message = "添加管理员失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【根据id删除删除管理员】的业务，参数：{}", id);
        // 不允许删除1号管理员
        if (id == 1) {
            String message = "删除管理员失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查尝试删除的数据是否存在
        Object queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "删除管理员失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 执行删除--管理员表
        log.debug("即将执行删除数据，参数：{}", id);
        int rows = adminMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除管理员失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // 执行删除--管理员与角色的关联表
        rows = adminRoleMapper.deleteByAdminId(id);
        if (rows < 1) {
            String message = "删除管理员失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id, 0);
    }

    @Override
    public List<AdminListItemVO> list() {
        log.debug("开始处理【查询管理员列表】的业务，参数：无");
        List<AdminListItemVO> list = adminMapper.list();

        Iterator<AdminListItemVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            AdminListItemVO admin = iterator.next();
            if (admin.getId() == 1) {
                iterator.remove();
                break;
            }
        }

        return list;
    }

    private void updateEnableById(Long id, Integer enable) {
        String[] enableText = {"禁用", "启用"};
        log.debug("开始处理【{}管理员】的业务，ID：{}，目标状态：{}", enableText[enable], id, enable);
        // 不允许调整1号管理员的启用状态
        if (id == 1) {
            String message = enableText[enable] + "管理员失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查数据是否存在
        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = enableText[enable] + "管理员失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 检查当前状态是否与参数表示的状态相同
        if (queryResult.getEnable().equals(enable)) {
            String message = enableText[enable] + "管理员失败，当前管理员已经处于"
                    + enableText[enable] + "状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 准备执行更新
        Admin admin = new Admin();
        admin.setId(id);
        admin.setEnable(enable);
        log.debug("即将修改数据，参数：{}", admin);
        int rows = adminMapper.update(admin);
        if (rows != 1) {
            String message = enableText[enable] + "管理员失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

}
