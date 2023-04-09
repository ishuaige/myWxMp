package com.niuma.mywechatmp.service;

import com.niuma.mywechatmp.model.domain.Homework;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author niumazlb
* @description 针对表【homework】的数据库操作Service
* @createDate 2023-04-08 09:50:34
*/
public interface HomeworkService extends IService<Homework> {

    /**
     * 通过班级名称获取所有未过期的作业
     * @param className
     * @return
     */
    List<Homework> getAllHomeWorkByClassName(String className);

    /**
     * 获取发送作业通知的内容
     * @param className
     * @return
     */
   // String getSendHomeworkMsg(String className);

}
