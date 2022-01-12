package com.clipboard.share.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/10/27 8:31]
 */
@TableName("t_item")
@Data
@Component
public class Clip {
    public Integer id;
    public String itemContent;
    public Date createTime;
    public Date updateTime;
}
