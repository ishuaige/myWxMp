package com.niuma.mywechatmp.service.impl;


import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niuma.mywechatmp.mapper.CourseMapper;
import com.niuma.mywechatmp.model.domain.Course;
import com.niuma.mywechatmp.service.CourseService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author niumazlb
 * @description 针对表【course】的数据库操作Service实现
 * @createDate 2023-04-06 14:54:45
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {


    @Override
    public List<Course> getTodayCourse(String className) {

        // 1. 获取距离本学期开学到现在是第几周 （注意 +1）
        LocalDate startDate = LocalDate.of(2023, 2, 20);
        LocalDate now = LocalDate.now();
        long week = ChronoUnit.WEEKS.between(startDate, now) + 1;
        //System.out.println(week); // 6 -> 第六周

        // 2. 拿到今天是周几
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();
        //System.out.println(dayOfWeekValue); // 4 -> 周四

        // 3. 查询数据库
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Course::getClassName, className);
        queryWrapper.eq(Course::getCourseWeek, dayOfWeekValue);
        List<Course> courseList = this.list(queryWrapper);

        // 4. 筛选出需要上的课
        List<Course> needCourse = courseList.stream().filter(course -> {
            int currentWeekType = (week % 2) != 0 ? 1 : 2;
            // 不是单双周都有的课，也不符合本周类型的，不要
            int needWeekType = course.getWeekType();
            if (needWeekType != 0 && needWeekType != currentWeekType) {
                return false;
            }
            // 拿到每节课需要上课的周次，并判断本周需不需要上课
            String[] courseWeekly = course.getCourseWeekly().split(",");
            // 遍历每一组时间
            for (String weekly : courseWeekly) {
                if (!weekly.contains("-")) {
                    if (Integer.parseInt(weekly) != week) {
                        continue;
                    }
                }
                String[] startAndEndWeek = weekly.split("-");
                int startWeek = Integer.parseInt(startAndEndWeek[0]);
                int endWeek = Integer.parseInt(startAndEndWeek[1]);
                if (week >= startWeek && week <= endWeek) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return needCourse;
    }

    /*
    @Override
    public String getSendCourseMsg(String className){
        StringBuilder sb = new StringBuilder();
        sb.append("班级：%s \n");
        sb.append("今日课程：\n%s");
        List<Course> todayCourse = this.getTodayCourse(className);
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
*/


    @Override
    public List<Course> getCourseByClassNameUseCrawler(String className) {
        String url = String.format(
                "xxxx"
                , className);

        String result2 = HttpRequest.get(url)
                .execute().body();
        //将返回结果解析成一个map
        Map<String, Object> map = JSONUtil.toBean(result2, Map.class);
        JSONArray courses = (JSONArray) map.get("data");
        List<Course> courseList = new ArrayList<>();
        courses.forEach(o -> {
            JSONObject jo = (JSONObject) o;
            Course course = JSONUtil.toBean(jo, Course.class);
            course.setWeekType(Integer.parseInt(jo.getStr("course_danshuang")));
            courseList.add(course);
        });
        return courseList;
    }
}




