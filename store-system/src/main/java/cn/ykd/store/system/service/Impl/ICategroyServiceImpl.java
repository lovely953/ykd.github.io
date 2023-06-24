package cn.ykd.store.system.service.Impl;

import cn.ykd.store.system.ex.ServiceException;
import cn.ykd.store.system.mapper.Brand_cateporyMapper;
import cn.ykd.store.system.mapper.CategoryMapper;
import cn.ykd.store.system.mapper.Category_attridute_templateMapper;
import cn.ykd.store.system.mapper.SpuMapper;
import cn.ykd.store.system.pojo.dto.CategoryAddNewDTO;
import cn.ykd.store.system.pojo.dto.CategoryUpdateDTO;
import cn.ykd.store.system.pojo.entity.Category;
import cn.ykd.store.system.pojo.vo.CategoryListitemVO;
import cn.ykd.store.system.pojo.vo.CategoryStandardVO;
import cn.ykd.store.system.service.ICategoryService;
import cn.ykd.store.system.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class ICategroyServiceImpl implements ICategoryService {
    @Autowired
     private CategoryMapper mapper;
    @Autowired
    private Brand_cateporyMapper brand_cateporyMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private Category_attridute_templateMapper category_attridute_templateMapper;

    @Override

    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        log.debug("开始处理【添加类别】的业务，参数：{}", categoryAddNewDTO);
        // 调用Mapper对象的countByName()检查类别名称是否已经被占用
        String name = categoryAddNewDTO.getName();
        int countByName = mapper.countByName(name);
        if (countByName > 0) {
            // 是：抛出异常（ERR_CONFLICT）
            String message = "添加类别失败，尝试添加的类别名称【" + name + "】已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Integer depth = 1;
        CategoryStandardVO parentCategory = null;
        Long parentId = categoryAddNewDTO.getParent_id();
        if (parentId != 0) {
            // 如果parentId不是0，调用Mapper对象的getStandardById()查询类别详情
            parentCategory = mapper.getCategoryStandardById(parentId);
            // 判断查询结果是否为null
            if (parentCategory == null) {
                // 是：父级类别不存在，抛出异常（NOT_FOUND）
                String message = "添加类别失败，选定的父级类别不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
            } else {
                depth = parentCategory.getDepth() + 1;
            }
        }

        // 创建Category类的对象
        Category category = new Category();
        // 将参数对象中的各属性值复制到以上新创建的Category类的对象中
        BeanUtils.copyProperties(categoryAddNewDTO, category);
        // 补全Category类的对象的属性值：depth
        category.setDepth(depth);
        // 补全Category类的对象的属性值：isParent >>> 固定为0
        category.setIs_parent(0);
        // 调用Mapper对象的insert()方法插入类别数据
        log.debug("即将插入数据：{}", category);
        int rows = mapper.insert(category);
        if (rows != 1) {
            String message = "添加类别失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        // 如果现在添加的类别的父级类别不是0
        if (parentId != 0) {
            // 如果父级类别的isParent仍为0
            if (parentCategory.getIs_parent() == 0) {
                // 则将父级类别的isParent更新为1
                Category updateParentCategory = new Category();
                updateParentCategory.setId(parentId);
                updateParentCategory.setIs_parent(1);
                rows = mapper.update(updateParentCategory);
                if (rows != 1) {
                    String message = "添加类别失败，服务器忙，请稍后再次尝试！";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERR_UPDATE, message);
                }
            }
        }
    }

    @Override

    public void delete(Long id) {
        log.debug("开始处理【删除类别】的业务，参数:{}",id);
        //根据id查询id是否存在。调用mapper.countbyid
        CategoryStandardVO currentCategory = mapper.getCategoryStandardById(id);
        if (currentCategory == null) {
            //抛出异常
            String message = "删除失败，尝试查询的id不存在";
            log.warn("删除失败，尝试查询的id不存在");
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        //判断以上查询结果中的is_parent是否为1
        //是：当前尝试删除是父亲类别，抛出异常ERR_CONFLICT
        if(currentCategory.getIs_parent() ==1){
            //抛出异常
            String message = "删除失败，当前尝试删除是父亲类别";
            log.warn("删除失败，当前尝试删除是父亲类别");
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        //用BrandCategoryMapper对象的countByCategory()统计
        //判断结果是否大于0
        //是：当前尝试删除的类别关联到了某些品牌，抛出异常ERR_CONFLICT
        int result = brand_cateporyMapper.countByCategory(id);
        if (result > 0) {
            //抛出异常
            String message = "删除失败，当前尝试删除的类别关联到了某些品牌";
            log.warn("删除失败，当前尝试删除的类别关联到了某些品牌");
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //用category_attridute_templateMapper对象的countByCategory()统计
        //判断结果是否大于0
        //是：当前尝试删除的类别关联到了某些模板属性，抛出异常ERR_CONFLICT
        int count1 = category_attridute_templateMapper.countByCategory(id);
        if (count1 > 0) {
            //抛出异常
            String message = "删除失败，当前尝试删除的类别关联到了某些模板属性";
            log.warn("删除失败，当前尝试删除的类别关联到了某些模板属性");
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //用spuMapper对象的countByCategory()统计
        //判断结果是否大于0
        //是：当前尝试删除的类别关联到了某些spu，抛出异常ERR_CONFLICT
        int count = spuMapper.countByCategory(id);
        if (count > 0) {
            //抛出异常
            String message = "删除失败，当前尝试删除的类别关联到了某些模板spu";
            log.warn("删除失败，当前尝试删除的类别关联到了某些模板spu");
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        {
            //调用mapper的deleteByid()
            log.debug("即将删除，id：{}", id);
            int rows = mapper.deleteById(id);
            if (rows != 1) {
                String message = "修改类别失败，服务器忙，请稍后再次尝试！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_UPDATE, message);
            }
        }
        //从此前的查询结果中找到当前删除的类别的父级类别ID
        //判断父级类别id是否不为0
        Long parentId = currentCategory.getParent_id();
        if( parentId != 0){
              //调用mapper对象的countByParentId()统计
              //判断统计结果是否等于0
              //是：将父级类别的isparent跟新为0
             int count2 = mapper.countByParentId(currentCategory.getParent_id());
             if (count2 == 0) {
             Category updateCategory = new Category();
             updateCategory.setId(parentId);
             updateCategory.setIs_parent(0);
                 int rows = mapper.update(updateCategory);
                 if (rows != 1) {
                     String message = "修改类别失败，服务器忙，请稍后再次尝试！";
                     log.warn(message);
                     throw new ServiceException(ServiceCode.ERR_UPDATE, message);
                 }
            }
        }
    }

    @Override
    public void updateInfoById(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        log.debug("开始处理【通过id修改类别】的业务，参数id:{},新数据：{}",id,categoryUpdateDTO);
        CategoryStandardVO categoryStandard= mapper.getCategoryStandardById(categoryUpdateDTO.getId());
        //调用mapper.getstandardById统计查询
        //判断传来的id是否为空
        //是：抛出异常
        if (categoryStandard == null) {
            //抛出异常
            String message = "删除失败，尝试查询的id不存在";
            log.warn("删除失败，尝试查询的id不存在");
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        //调用countByName统计查询名称
        //判断名称是否大于0
        //是：抛出异常
        int countByName = mapper.countByName(categoryUpdateDTO.getName());
        if (countByName  > 0) {
            // 是：抛出异常（ERR_CONFLICT）
            String message = "修改类别失败，尝试修改的类别名称【" + categoryUpdateDTO.getName() + "】已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //创建category对象 赋值给category
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateDTO,category);
        category.setId(id);
        //调用mapper.update
        mapper.update(category);
    }






    @Override
    public List<CategoryListitemVO> list() {
        log.debug("开始处理【查询类别】的业务，无参数");
        return mapper.list();
    }

    @Override
    public List<CategoryListitemVO> listByParent_id(Long parent_id) {
        log.debug("开始处理【根据父级id{}"+parent_id+"查询子级列表】的业务，无参数");
        //调用mapper的listByParent_id进行查询
        List<CategoryListitemVO> list = mapper.listByParent_id(parent_id);
        return list;
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        CategoryStandardVO categoryStandard = mapper.getCategoryStandardById(id);
        if (categoryStandard == null) {
           //抛出异常
            String message = "删除失败，尝试查询的id不存在";
            log.warn("删除失败，尝试查询的id不存在");
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
        return categoryStandard;
    }


    @Override
    public void setEnable(Long id) {
        updateEnableById(id,1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id,0);
    }

    private void updateEnableById(Long id,Integer enable){
        String enableText[] ={"禁用","启用"};
        log.debug("开始处理【启用/禁用功能】");
        //调用mapper对象执行getStandard()方法来查询
        //判断结果是否为空
        //是：抛出异常 not_found
        CategoryStandardVO categoryStandard = mapper.getCategoryStandardById(id);
        if (categoryStandard == null) {
            String message = enableText[enable] + "类别失败，尝试访问的类别数据不存在！";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        //判断enable的状态是否和传来的状态一致
        //是：抛出异常conflict
        if (categoryStandard.getEnable() == enable) {
            String message = enableText[enable]+"失败，当前状态已处于"+enableText[enable]+"状态";
            log.warn(enableText[enable]+"失败，当前状态已处于"+enableText[enable]+"状态");
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }
        //创建category对象，并将2个参数的值封装起来
        //mapper.update，获取返回结果是否返回预期值
        Category category = new Category();
        category.setId(id);
        category.setEnable(enable);
        int rows = mapper.update(category);
        if (rows != 1) {
            String message =  enableText[enable]+"类别失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void enIs_display(Long id) {
        updateIs_displayById(id,1);
    }

    @Override
    public void disIs_display(Long id) {
        updateIs_displayById(id,0);
    }

    private  void updateIs_displayById(Long id,Integer is_display){
        String is_displayText[] = {"禁用","启用"};
        //通过mapper.getStrandardById获取值，
        //判断是否为空
        //是：数据找不到
        CategoryStandardVO categoryStandard = mapper.getCategoryStandardById(id);
        if (categoryStandard == null) {
            String message = is_displayText[is_display] + "导航栏失败，尝试访问的类别数据不存在！";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        //判断获取的值的is_display是与数据库数据一致
        //是：抛出异常
        if (categoryStandard.getIs_display() == is_display) {
            String message = is_displayText[is_display]+"导航栏失败，当前状态已处于"+is_displayText[is_display]+"状态";
            log.warn(message);
            throw  new ServiceException(ServiceCode.ERR_CONFLICT,message);
        }

        //创建category对象，将两个值封装起来
        Category category = new Category();
        category.setId(id);
        category.setIs_display(is_display);
        //调用mapper.update（）
        mapper.update(category);
    }





}
