package com.niuma.mywechatmp.wxmp.msgbuilder;

import com.niuma.mywechatmp.model.domain.Homework;
import com.niuma.mywechatmp.service.HomeworkService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author niuma
 * @create 2023-04-08 14:25
 */
@Component
public class HomeWorkMsgBuilder implements MsgBuilder {

    @Resource
    HomeworkService homeworkService;

    @Override
    public String buildMsg(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("作业 - 截止时间:\n%s ");
        List<Homework> homeworkList = homeworkService.getAllHomeWorkByClassName(className);
        if (homeworkList.isEmpty()) {
            return "没有作业了喔~";
        }
        StringBuilder homeworkSb = new StringBuilder();
        homeworkList.forEach(homework -> {
            String courseName = homework.getCourseName();
            Date endTime = homework.getEndTime();
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            String endTimeStr = DateFormatUtils.format(endTime, strDateFormat);
            String join = String.join(" - ", courseName, endTimeStr);
            homeworkSb.append(join).append("\n");
        });
        String homeworkMsg = String.format(sb.toString(), homeworkSb);
        return homeworkMsg;
    }
}
