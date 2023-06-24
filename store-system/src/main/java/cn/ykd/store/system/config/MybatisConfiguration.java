package cn.ykd.store.system.config;

import cn.ykd.store.system.mybatis.InsertUpdateTimeInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * mybatis配置文件
 */
@Configuration
@MapperScan("cn.ykd.store.system.mapper")
public class MybatisConfiguration {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct // 在方法上添加@PostConstruct注解，表示此方法是Spring Bean的生命周期方法的初始化方法，会在创建对象之后自动执行
    public void addInterceptor() {
        InsertUpdateTimeInterceptor interceptor = new InsertUpdateTimeInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}
