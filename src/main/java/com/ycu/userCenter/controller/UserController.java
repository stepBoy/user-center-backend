package com.ycu.userCenter.controller;


import com.ycu.userCenter.common.BusinessStatus;
import com.ycu.userCenter.common.QueryPageVo;
import com.ycu.userCenter.common.ResResult;
import com.ycu.userCenter.exception.BusinessException;
import com.ycu.userCenter.model.domain.User;
import com.ycu.userCenter.model.request.UserLoginRequest;
import com.ycu.userCenter.model.request.UserPageQueryRequest;
import com.ycu.userCenter.model.request.UserRegisterRequest;
import com.ycu.userCenter.model.vo.SafetyUserVo;
import com.ycu.userCenter.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/userRegister")
    public ResResult<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){

        if(userRegisterRequest==null){

            throw new BusinessException(BusinessStatus.PARAMS_ERROR,"请求参数不能为空");

        }

        return userService.userRegister(userRegisterRequest);



    }

    @PostMapping("/userLogin")

    public ResResult<SafetyUserVo> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest servletRequest){

        if(userLoginRequest==null){

            throw new BusinessException(BusinessStatus.PARAMS_ERROR,"请求参数不能为空");


        }

      return this.userService.userLogin(userLoginRequest,servletRequest);




    }

    @PostMapping("/getUsersByPage")

    public ResResult<QueryPageVo<SafetyUserVo>> getUsersByPage(@RequestBody UserPageQueryRequest userPageQueryRequest
                                ,HttpServletRequest servletRequest){

        if(userPageQueryRequest==null){

            throw new BusinessException(BusinessStatus.PARAMS_ERROR,"请求参数错误");

        }

        return this.userService.getUsersByPage(userPageQueryRequest,servletRequest);

    }

    @GetMapping("/getUserById/{id}")

    public ResResult<SafetyUserVo> getUserById(@PathVariable Long id,HttpServletRequest servletRequest){

        if(id==null||id<=0){
            throw new BusinessException(BusinessStatus.PARAMS_ERROR,"输入参数有误");
        }

        SafetyUserVo user = this.userService.getUserById(id,servletRequest);

        return ResResult.success(user);




    }

    @GetMapping("/getCurrentUser")

    public ResResult<SafetyUserVo> getCurrentUser(HttpServletRequest servletRequest){

        SafetyUserVo currentUser =  this.userService.getCurrentUser(servletRequest);

        return ResResult.success(currentUser);



    }

    @PostMapping("/userLogout")

    public ResResult<?> userLogout(HttpServletRequest servletRequest){

        this.userService.userLogout(servletRequest);

        return ResResult.success();

    }

}
