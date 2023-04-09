package com.niuma.mywechatmp;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.niuma.mywechatmp.model.domain.Course;
import com.niuma.mywechatmp.service.CourseService;
import com.niuma.mywechatmp.service.WxmpService;
import com.niuma.mywechatmp.utils.ConstantPropertiesUtil;
import org.apache.http.client.utils.HttpClientUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author niuma
 * @create 2023-03-17 9:17
 */
@SpringBootTest
public class CrawlerTest {

    @Resource
    CourseService courseService;

    @Resource
    WxmpService wxmpService;

    @Test
    public void testFetchPassage() {
        String className = "20计算机科学与技术1班";
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
//            System.out.println(course);
        });
//        courseService.saveBatch(courseList);
        System.out.println(courses);
    }

    @Test
    public void testGetTodayCourse() {
        List<Course> todayCourse = courseService.getTodayCourse("20计算机科学与技术1班");
        System.out.println(todayCourse);
    }


    @Test
    public void testAccessToken(){
        //拼接请求地址
        StringBuffer buffer = new StringBuffer();
        buffer.append("https://api.weixin.qq.com/cgi-bin/token");
        buffer.append("?grant_type=client_credential");
        buffer.append("&appid=%s");
        buffer.append("&secret=%s");
        //设置路径参数
        String url = String.format(buffer.toString(),
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //get请求
        try {
            String tokenString = HttpRequest.get(url).execute().body();
            Map map = JSONUtil.toBean(tokenString, Map.class);
            String accessToken = (String)map.get("access_token");
            System.out.println(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSendAllMsg(){
        StringBuilder sb = new StringBuilder();
        sb.append("班级：%s \n");
        sb.append("今日课程：\n%s");
        String className = "20计算机科学与技术1班";
        List<Course> todayCourse = courseService.getTodayCourse(className);
        StringBuilder courseSb = new StringBuilder();
        todayCourse.forEach(course -> {
            String courseSection = course.getCourseSection();
            String courseName = course.getCourseName();
            String courseAddressFull = course.getCourseAddress();
            String[] s = courseAddressFull.split("_");
            String courseAddressSimple = s[0]+s[1];
            String courseInfo = String.join("-", courseSection, courseName, courseAddressSimple);
            courseSb.append(courseInfo + "\n");
        });
        String text = String.format(sb.toString(), className, courseSb);
        wxmpService.sendAllMsg(text);
    }



}
