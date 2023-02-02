package com.ycu.userCenter.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPageQueryRequest extends BasePageQueryRequest implements Serializable {
    private static final long serialVersionUID = 3537409306847769110L;


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
     * 用户角色
     */

    private Integer  userRole;


    /**
     * 电话号码
     */

    private String phone;

    /**
     * 邮箱
     */

    private String email;



    /**
     * 状态 0 正常
     */

    private Integer userStatus;

    /**
     * 注册起始时间
     */

    private Date userRegisterStartTime;

    /**
     * 注册结束时间
     */

    private Date userRegisterEndTime;

}
