package com.niuma.mywechatmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niuma.mywechatmp.mapper.HomeworkMapper;
import com.niuma.mywechatmp.model.domain.Homework;
import com.niuma.mywechatmp.service.HomeworkService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author niumazlb
 * @description 针对表【homework】的数据库操作Service实现
 * @createDate 2023-04-08 09:50:34
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework>
        implements HomeworkService {

    @Override
    public List<Homework> getAllHomeWorkByClassName(String className) {
        LambdaQueryWrapper<Homework> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(className), Homework::getClassName, className);
        LocalDateTime now = LocalDateTime.now();

        queryWrapper.le(Homework::getBeginTime, now);
        queryWrapper.ge(Homework::getEndTime, now);

        List<Homework> list = this.list(queryWrapper);
        return list;
    }

    /*
    @Override
    public String getSendHomeworkMsg(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("作业 - 截止时间:\n%s ");

        List<Homework> homeworkList = getAllHomeWorkByClassName(className);
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

     */
}




