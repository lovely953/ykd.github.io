package cn.ykd.store.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class StoreSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DataSource dataSource;//导java.sql包中的接口
    //测试数据库连接
    @Test
    void getConnection() throws Throwable{
        dataSource.getConnection();//待用geiConnection()时会连接数据库，则可以判断配置的连接信息是否正确
        System.out.println("mysql连接成功");
    }
}
