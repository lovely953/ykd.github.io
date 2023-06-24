package cn.ykd.store.system.schedule;

import cn.ykd.store.system.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *# 计划任务 使缓存导致的数据一致
 */
@Slf4j
@Component
public class CacheSchedule {
    @Autowired
    private IBrandService brandService;

    public  CacheSchedule() {
        log.debug("创建计划任务类对象：CacheSchedule");
    }


    /**fixedRate：执行计划任务的间隔时间，以上一次执行的起始时间计时得到下次执行时间，以毫秒为单位
     *fixedDelay:执行计划任务的间隔时间，以上一次执行的起始时间计时得到下次执行时间，以毫秒为单位
     * cron: 使用一个字符串作为，此字符串是一个表达式，包含6-7个值，各值之间使用空格进行分隔
     *在cron表达式中各值从左到右分别表示：秒 分 时 日 月 周 （星期） [年]
     *在cron表达式中的各部分值都可以使用通配符
     *使用*作为通配符表达任意值
     *使用问号？表示不关心此值，此通配符只能用于“日”和“周”
     *例如："56 34 12 14 2 ？ 2023"表示在2023年2月14（无视星期几）12点34分56秒
     *在cron表达式中的各值还可以使用“x/y”格式的值，例如，在“分”对应的位置使用“1/5”，表示“分钟值为1时执行，且每间隔5分钟执行一次”
    */
    @Scheduled(fixedRate =30*60*1000 )
    public void rebuildCache() {
        log.debug("开始执行计划任务……");
         brandService.rebuildCache();
    }
}
