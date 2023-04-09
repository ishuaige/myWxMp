package com.niuma.mywechatmp;

import com.niuma.mywechatmp.model.domain.Homework;
import com.niuma.mywechatmp.service.HomeworkService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author niuma
 * @create 2023-04-08 10:02
 */
@SpringBootTest
public class HomeworkServiceTest {

    @Resource
    HomeworkService homeworkService;

    @Test
    public void testGetAllHomeWorkByClassName(){
        List<Homework> allHomeWorkByClassName =
                homeworkService.getAllHomeWorkByClassName("20计算机科学与技术1班");
        System.out.println(allHomeWorkByClassName);
    }

}
