package com.niuma.mywechatmp.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author niumazlb
 * @TableName homework
 */
@TableName(value ="homework")
@Data
public class Homework implements Serializable {
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
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}