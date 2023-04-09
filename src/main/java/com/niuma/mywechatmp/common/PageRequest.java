package com.niuma.mywechatmp.common;
import lombok.Data;

/**
 * 分页请求
 *
 * @author niumazlb

 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;


}
