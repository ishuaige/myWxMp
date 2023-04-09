package com.niuma.mywechatmp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @author niumazlb
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 班级名
     */
    private String className;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 周次
     */
    private String courseWeekly;

    /**
     * 上课的时间（周几）
     */
    private Integer courseWeek;

    /**
     * 第几节课（1-2）
     */
    private String courseSection;

    /**
     * 单双周（0-单双周，1-单周）
     */
    private Integer weekType;

    /**
     * 上课地址
     */
    private String courseAddress;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}