package com.ycu.userCenter.service.impl;
import java.util.Date;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ycu.userCenter.common.BusinessStatus;
import com.ycu.userCenter.common.QueryPageVo;
import com.ycu.userCenter.common.ResResult;
import com.ycu.userCenter.constant.UserConstant;
import com.ycu.userCenter.exception.BusinessException;
import com.ycu.userCenter.mapper.UserMapper;
import com.ycu.userCenter.model.domain.User;

import com.ycu.userCenter.model.request.UserLoginRequest;
import com.ycu.userCenter.model.request.UserPageQueryRequest;
import com.ycu.userCenter.model.request.UserRegisterRequest;
import com.ycu.userCenter.model.vo.SafetyUserVo;
import com.ycu.userCenter.service.UserService;
import com.ycu.userCenter.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户相关的服务
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 用户密码加密的盐值
     */

    private final String SALT = "yechunyu";


    @Override
    public ResResult<Long> userRegister(UserRegisterRequest userRegisterRequest) {



        //1. 校验

        String userAccount = userRegisterRequest.getUserAccount();

        String userPassword = userRegisterRequest.getUserPassword();

        String userCheckedPassword = userRegisterRequest.getUserCheckedPassword();

        // 账号密码不能为空 "" ,"   "
        if (StringUtils.isAnyBlank(userAccount, userPassword, userCheckedPassword)) {


            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "账号密码不能为空");
        }

        validateUserAccount(userAccount);

        if (userPassword.length() < 6 || userCheckedPassword.length() < 6) {

            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "密码长度需大于等于6");
        }

        if (!userPassword.equals(userCheckedPassword)) {

            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "校验密码需与设置密码相同");

        }

        //注册账户不能重复

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("userAccount", userAccount);

        long userAccountNum = this.count(userQueryWrapper);

        if (userAccountNum > 0) {

            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "用户账号重复");

        }


        //2. 密码加密


        String safetyUserPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //3.数据库保存

        User user = new User();

        user.setUserAccount(userAccount);

        user.setUserPassword(safetyUserPassword);

        this.save(user);


        Long userId = user.getId();

        return ResResult.success(userId);


    }



    @Override
    public ResResult<SafetyUserVo> userLogin(UserLoginRequest userLoginRequest, HttpServletRequest httpRequest) {


        //1.参数校验

        String userAccount = userLoginRequest.getUserAccount();

        String userPassword = userLoginRequest.getUserPassword();

        // 账号密码不能为空 "" ,"   "
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {


            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "账号密码不能为空");
        }

        validateUserAccount(userAccount);

        if (userPassword.length() < 6) {

            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "密码长度需大于等于6");
        }



        //2 判断是否登录成功

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("userAccount", userAccount);

        String safetyUserPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        userQueryWrapper.eq("userPassword", safetyUserPassword);

        User user = this.getOne(userQueryWrapper);

        if (user == null) {

            throw new BusinessException(BusinessStatus.LOGIN_ERROR, "登录失败");

        }

        //正常状态的用户才能登录成功
        if (user.getUserStatus() != 0) {
            throw new BusinessException(BusinessStatus.LOGIN_ERROR, "用户状态异常");
        }


        //todo 登录限流

        //3.登录成功后设置用户的登录态

        //脱敏用户信息

        SafetyUserVo safetyUser = this.getSafetyUser(user);

        httpRequest.getSession().setAttribute(UserConstant.USER_LOGIN_STATE,safetyUser);


        //5.返回脱敏的用户信息

        return ResResult.success(safetyUser);

    }

    @Override
    public ResResult<QueryPageVo<SafetyUserVo>> getUsersByPage(UserPageQueryRequest userPageQueryRequest,HttpServletRequest servletRequest) {

        //1.鉴权

        checkAuth(servletRequest);


        //2.拼装查询条件

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        String userName = userPageQueryRequest.getUserName();

        String userAccount = userPageQueryRequest.getUserAccount();

        Integer gender = userPageQueryRequest.getGender();

        String phone = userPageQueryRequest.getPhone();

        String email = userPageQueryRequest.getEmail();

        Integer userStatus = userPageQueryRequest.getUserStatus();

        Integer userRole = userPageQueryRequest.getUserRole();

        Date userRegisterStartTime = userPageQueryRequest.getUserRegisterStartTime();

        Date userRegisterEndTime = userPageQueryRequest.getUserRegisterEndTime();

        //userName

        if(!StringUtils.isBlank(userName)){

            userQueryWrapper.like("userName",userName);

        }

        //userAccount
        if(!StringUtils.isBlank(userAccount)){

            userQueryWrapper.like("userAccount",userAccount);

        }

        //gender

        if(gender!=null){

            userQueryWrapper.eq("gender",gender);

        }

        //phone
        if(!StringUtils.isBlank(phone)){

            userQueryWrapper.like("phone",phone);

        }

        //userRole

        if(userRole!=null){

            userQueryWrapper.eq("userRole",userRole);

        }

        //email

        if(!StringUtils.isBlank(email)){

            userQueryWrapper.like("email",email);

        }


        //userStatus

        if(userStatus!=null){

            userQueryWrapper.eq("userStatus",userStatus);

        }

        //userRegisterStartTime userRegisterEndTime

        if(userRegisterStartTime!=null){

            userQueryWrapper.ge("createTime",userRegisterStartTime);

        }

        if(userRegisterEndTime!=null){

            userQueryWrapper.le("createTime",userRegisterEndTime);

        }



        //3.封装查询结果

        Integer currentPage = userPageQueryRequest.getCurrentPage();

        Integer pageSize = userPageQueryRequest.getPageSize();

        Page<User> userPage = new Page<>(currentPage,pageSize);

        IPage<User> page = this.page(userPage, userQueryWrapper);

        //脱敏

        IPage<SafetyUserVo> convertPage = page.convert(this::getSafetyUser);

        QueryPageVo<SafetyUserVo> userQueryPageVo = PageUtils.getQueryPageVo(convertPage);

        return ResResult.success(userQueryPageVo);

    }



    @Override
    public SafetyUserVo getUserById(Long id,HttpServletRequest servletRequest) {

        if(id==null||id<=0){

            throw new BusinessException(BusinessStatus.PARAMS_ERROR,"请求参数错误");

        }

        this.checkAuth(servletRequest);

        User user = this.getById(id);

        return this.getSafetyUser(user);
    }

    @Override
    public SafetyUserVo getCurrentUser(HttpServletRequest servletRequest) {

        SafetyUserVo sessionUser = getSessionUser(servletRequest);

        Long userId = sessionUser.getId();

        User user = this.getById(userId);


        return this.getSafetyUser(user);
    }




    @Override
    public void userLogout(HttpServletRequest servletRequest) {

        //移除用户的登录态
       servletRequest.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);










    }




    private SafetyUserVo getSessionUser(HttpServletRequest servletRequest) {
        SafetyUserVo sessionUser = (SafetyUserVo) servletRequest.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);

        if(sessionUser==null){

            throw new BusinessException(BusinessStatus.NO_LOGIN_ERROR,"未登录");

        }
        return sessionUser;
    }

    @Override
    public SafetyUserVo getSafetyUser(User user) {

        if (user == null) {

            throw new RuntimeException("user不能为空");

        }

        SafetyUserVo safetyUserVo = new SafetyUserVo();

        safetyUserVo.setId(user.getId());

        safetyUserVo.setUserName(user.getUserName());

        safetyUserVo.setUserAccount(user.getUserAccount());

        safetyUserVo.setGender(user.getGender());

        safetyUserVo.setPhone(user.getPhone());

        safetyUserVo.setEmail(user.getEmail());

        safetyUserVo.setAvatarUrl(user.getAvatarUrl());

        safetyUserVo.setUserStatus(user.getUserStatus());

        safetyUserVo.setUserRole(user.getUserRole());

        safetyUserVo.setCreateTime(user.getCreateTime());


        return safetyUserVo;


    }

    private void validateUserAccount(String userAccount) {
        if (userAccount.length() < 4) {
            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "账号长度需大于等于4");
        }

        //用户账号不能存在特殊字符

        String reg = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        Matcher matcher = Pattern.compile(reg).matcher(userAccount);

        if (matcher.find()) {

            throw new BusinessException(BusinessStatus.PARAMS_ERROR, "用户账号不能存在特殊字符");

        }
    }

    @Override
    public void checkAuth(HttpServletRequest servletRequest) {
        SafetyUserVo sessionUser = this.getSessionUser(servletRequest);

        if(sessionUser.getUserRole()!=UserConstant.ADMIN_ROLE){

            throw new BusinessException(BusinessStatus.NO_AUTH_ERROR,"没有权限");
        }
    }


}




