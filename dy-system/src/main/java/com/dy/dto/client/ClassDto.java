package com.dy.dto.client;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ClassDto {
    /**
     * 班级id
     */
    private Long id;

    /**
     * 班级名称
     */
    private String className;

}
