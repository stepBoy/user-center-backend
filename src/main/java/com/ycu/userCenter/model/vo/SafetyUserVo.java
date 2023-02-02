package com.ycu.userCenter.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SafetyUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */

    private String userName;

    /**
     * 用户账号
     */

    private String userAccount;



    /**
     * 性别
     */

    private Integer gender;

    /**
     * 电话号码
     */

    private String phone;

    /**
     * 邮箱
     */

    private String email;

    /**
     * 头像
     */

    private String avatarUrl;

    /**
     * 状态 0 正常
     */

    private Integer userStatus;

    /**
     * 用户角色
     */

    private Integer userRole;

    /**
     * 创建时间
     */

    private Date createTime;


}
