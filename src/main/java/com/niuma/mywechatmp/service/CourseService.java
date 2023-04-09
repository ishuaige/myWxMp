package com.niuma.mywechatmp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.niuma.mywechatmp.model.domain.Course;

import java.util.List;

/**
* @author niumazlb
* @description 针对表【course】的数据库操作Service
* @createDate 2023-04-06 14:54:45
*/
public interface CourseService extends IService<Course> {
     /**
      * 获取今日课程
      * @param className
      * @return
      */
     List<Course> getTodayCourse(String className);

     /**
      * 获取需要发送的课程消息
      * @return
      */
   //  String getSendCourseMsg(String className);

     /**
      * 通过班级名获取班级课程信息（通过爬虫）
      * @param className
      * @return
      */
     List<Course> getCourseByClassNameUseCrawler(String className);
}
