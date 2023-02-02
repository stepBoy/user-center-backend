package com.ycu.userCenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycu.userCenter.common.QueryPageVo;
import com.ycu.userCenter.common.ResResult;
import com.ycu.userCenter.model.domain.User;
import com.ycu.userCenter.model.request.UserLoginRequest;
import com.ycu.userCenter.model.request.UserPageQueryRequest;
import com.ycu.userCenter.model.request.UserRegisterRequest;
import com.ycu.userCenter.model.vo.SafetyUserVo;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface UserService extends IService<User> {

    ResResult<Long> userRegister(UserRegisterRequest userRegisterRequest);

    SafetyUserVo getSafetyUser(User user);


    ResResult<SafetyUserVo> userLogin(UserLoginRequest userLoginRequest, HttpServletRequest httpRequest);

     ResResult<QueryPageVo<SafetyUserVo>> getUsersByPage(UserPageQueryRequest userPageQueryRequest,HttpServletRequest servletRequest);

    public SafetyUserVo getUserById(Long id,HttpServletRequest servletRequest);

    public void checkAuth(HttpServletRequest servletRequest);

    SafetyUserVo getCurrentUser(HttpServletRequest servletRequest);

    void userLogout(HttpServletRequest servletRequest);
}
