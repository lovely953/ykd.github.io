package cn.ykd.store.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.logging.Logger;

@Slf4j
@SpringBootTest
public class Sl4fjTests {
    //private Logger logger= (Logger) LoggerFactory.getLogger(Sl4fjTests.class);
    @Test
    void test(){
        log.trace("这是trace级别的日志");
        log.debug("这是debug级别的日志");
        log.info("这是info级别的日志");
        log.warn("这是warn级别的日志");
        log.error("这是error级别的日志");
    }
}
