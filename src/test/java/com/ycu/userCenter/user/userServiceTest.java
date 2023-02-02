package com.ycu.userCenter.user;


import com.ycu.userCenter.common.QueryPageVo;
import com.ycu.userCenter.common.ResResult;
import com.ycu.userCenter.exception.BusinessException;
import com.ycu.userCenter.model.domain.User;
import com.ycu.userCenter.model.request.UserLoginRequest;
import com.ycu.userCenter.model.request.UserPageQueryRequest;
import com.ycu.userCenter.model.request.UserRegisterRequest;
import com.ycu.userCenter.model.vo.SafetyUserVo;
import com.ycu.userCenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.function.Function;

@SpringBootTest
@Slf4j
public class userServiceTest {

    @Resource
    UserService userService;



//    @Test
//    public void insertTest(){
//
//        User user = new User();
//
//        user.setUserName("tom");
//        user.setUserAccount("123456");
//        user.setUserPassword("13465");
//        user.setGender(1);
//        user.setPhone("1324564651");
//        user.setEmail("123@qq.com");
//        user.setAvatarUrl("http://123");
//
//        boolean res = userService.save(user);
//
//        Assertions.assertTrue(res);
//
//
//    }
//
//    @Test
//    public void deleteTest(){
//
//        User user = new User();
//
//        user.setId(2L);
//
//        boolean res = userService.removeById(user);
//
//        Assertions.assertTrue(res);
//
//
//    }
//
//   @Test
//    public void userRegisterTest(){
//
//       UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
//
//       userRegisterRequest.setUserAccount("");
//
//       userRegisterRequest.setUserPassword("123456");
//
//       userRegisterRequest.setUserCheckedPassword("123456");
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//       userRegisterRequest.setUserAccount("  ");
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//       userRegisterRequest.setUserAccount("jack%");
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//
//       userRegisterRequest.setUserAccount("jac");
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//       userRegisterRequest.setUserAccount("jack1");
//
//       userRegisterRequest.setUserPassword("132");
//
//       userRegisterRequest.setUserCheckedPassword("132");
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//       userRegisterRequest.setUserPassword("123456");
//
//       userRegisterRequest.setUserCheckedPassword("1234567");
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//       userRegisterRequest.setUserAccount("haha");
//
//       userRegisterRequest.setUserPassword("123456");
//
//       userRegisterRequest.setUserCheckedPassword("123456");
//
//       userService.userRegister(userRegisterRequest);
//
//       Assertions.assertThrows(BusinessException.class,()->userService.userRegister(userRegisterRequest));
//
//
//   }

   @Test
    public void userLoginTest(){

       UserLoginRequest userLoginRequest = new UserLoginRequest();


       //1. 测试未注册用户登录



       //2. 测试用户密码输入错误登录

       //3. 测试用户状态异常登录

       //4.测试正常登录 session 内的内容和返回的数据


   }

//   @Test
//   public void getUsersByPageTest(){
//
////       UserPageQueryRequest userPageQueryRequest = new UserPageQueryRequest();
////
////       userPageQueryRequest.setPageSize(1);
////
////       ResResult<QueryPageVo<SafetyUserVo>> res = userService.getUsersByPage(userPageQueryRequest);
////
////       QueryPageVo<SafetyUserVo> data = res.getData();
////
////       Assertions.assertEquals(1,data.getTotalPage());
////
////       Assertions.assertEquals(2,data.getRecords().size());
//
//
//   }

//   @Test
//   public void getUserByIdTest(){
//
////        Long id = 5L;
////
////       SafetyUserVo user = userService.getUserById(id);
////
////       Assertions.assertNotNull(user);
//
//
//   }



}
