package com.ycu.userCenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户账号
     */
    @TableField("userAccount")
    private String userAccount;

    /**
     * 用户密码
     */
    @TableField("userPassword")
    private String userPassword;

    /**
     * 性别 0-男 1-女
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 头像
     */
    @TableField("avatarUrl")
    private String avatarUrl;

    /**
     * 状态 0 正常
     */
    @TableField("userStatus")
    private Integer userStatus;

    /**
     * 用户角色 0-普通用户 1-管理员
     */
    @TableField("userRole")
    private Integer userRole;



    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 逻辑删除 0-未删除 1-删除
     */
    @TableLogic
    @TableField("isDeleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}