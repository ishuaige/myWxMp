package com.niuma.mywechatmp.wxmp.msgbuilder;

import com.niuma.mywechatmp.model.domain.Course;
import com.niuma.mywechatmp.service.CourseService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author niuma
 * @create 2023-04-08 14:23
 */
@Component
public class CourseMsgBuilder implements MsgBuilder{

    @Resource
    CourseService courseService;

    @Override
    public String buildMsg(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("班级：%s \n");
        sb.append("今日课程：\n%s");
        List<Course> todayCourse = courseService.getTodayCourse(className);
        if(todayCourse.isEmpty()){
            return "今天没有课喔~";
        }
        StringBuilder courseSb = new StringBuilder();
        todayCourse.forEach(course -> {
            String courseSection = course.getCourseSection();
            String courseName = course.getCourseName();
            String courseAddressFull = course.getCourseAddress(); // 博雅楼_401_什么什么教室
            String[] s = courseAddressFull.split("_");
            String courseAddressSimple = s[0]+s[1]; // 博雅楼401
            String courseInfo = String.join(" - ", courseSection, courseName, courseAddressSimple);
            courseSb.append(courseInfo).append("\n");
        });
        String text = String.format(sb.toString(), className, courseSb);
        return text;
    }
}
