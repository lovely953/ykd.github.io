package cn.ykd.store.system.service.Impl;

import cn.ykd.store.system.mapper.PictureMapper;
import cn.ykd.store.system.service.IPictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class IPictureServiceImpl implements IPictureService {
    @Autowired
    private PictureMapper mapper;
}
