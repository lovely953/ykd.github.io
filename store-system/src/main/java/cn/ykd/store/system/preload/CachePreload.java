package cn.ykd.store.system.preload;

import cn.ykd.store.system.mapper.BrandMapper;
import cn.ykd.store.system.pojo.vo.BrandStrandardVO;
import cn.ykd.store.system.pojo.vo.BrandlistitemVO;
import cn.ykd.store.system.repo.IBrandRedisRepository;
import cn.ykd.store.system.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 缓存预加载
 */
@Slf4j
@Deprecated//已过期但能用
//@Component
public class CachePreload implements ApplicationRunner {

    @Autowired
    private IBrandService brandService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("开始执行CachePreload.run()");
        brandService.rebuildCache();


    }
}
