package com.niuma.mywechatmp.job;

import com.niuma.mywechatmp.model.domain.Course;
import com.niuma.mywechatmp.service.CourseService;
import com.niuma.mywechatmp.service.HomeworkService;
import com.niuma.mywechatmp.service.WxmpService;
import com.niuma.mywechatmp.wxmp.msgbuilder.CourseMsgBuilder;
import com.niuma.mywechatmp.wxmp.msgbuilder.HomeWorkMsgBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author niuma
 * @create 2023-04-06 22:41
 */
@Component
@Slf4j
public class SendTodayCourseJob {

    @Resource
    CourseMsgBuilder courseMsgBuilder;
    @Resource
    HomeWorkMsgBuilder homeWorkMsgBuilder;

    @Resource
    WxmpService wxmpService;

    @Scheduled(cron = "0 40 10 * * ?")
    public void run(){
        String courseMsg = courseMsgBuilder.buildMsg("20计算机科学与技术1班");

        String homeworkMsg = homeWorkMsgBuilder.buildMsg("20计算机科学与技术1班");

        String text = courseMsg + "\n" + homeworkMsg;
        wxmpService.sendAllMsg(text);
        log.info("群发消息定时任务.消息：{}",text);
    }

}
