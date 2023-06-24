package cn.ykd.store.system.service.Impl;

import cn.ykd.store.system.mapper.Brand_cateporyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class IBrand_categoryServiceImpl {
    @Autowired
    private Brand_cateporyMapper mapper;
}
