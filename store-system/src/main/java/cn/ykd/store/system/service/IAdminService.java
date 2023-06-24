package cn.ykd.store.system.service;



import cn.ykd.store.system.pojo.dto.AdminAddNewDTO;
import cn.ykd.store.system.pojo.dto.AdminLoginDTO;
import cn.ykd.store.system.pojo.vo.AdminListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理管理员数据的业务接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Transactional
public interface IAdminService {

    /**
     * 管理员登录
     *
     * @param adminLoginDTO 管理员的登录信息，包含：用户名、密码（原文）
     * @return 登录成功的JWT数据
     */
//    String login(AdminLoginDTO adminLoginDTO);
    String login(AdminLoginDTO adminLoginDTO);



    /**
     * 添加管理员
     *
     * @param adminAddNewDTO 管理员数据
     */
    void addNew(AdminAddNewDTO adminAddNewDTO);

    /**
     * 删除管理员
     *
     * @param id 管理员id
     */
    void delete(Long id);

    /**
     * 启用管理员
     *
     * @param id 管理员id
     */
    void setEnable(Long id);

    /**
     * 禁用管理员
     *
     * @param id 管理员id
     */
    void setDisable(Long id);

    /**
     * 查询管理员列表
     *
     * @return 管理员列表
     */
    List<AdminListItemVO> list();

}
