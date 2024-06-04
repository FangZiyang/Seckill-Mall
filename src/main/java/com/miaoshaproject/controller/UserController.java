package com.miaoshaproject.controller;

import com.miaoshaproject.controller.Viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.impl.UserServiceImpl;
import com.miaoshaproject.service.model.UserModel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {


    @Autowired
    UserServiceImpl userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        // return user Info to front user by id
        UserModel userModel = userService.getUserById(id);

        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        //Convert core domain model user objects into UI-ready view objects.
        UserVO userVO = convertFromModel(userModel);

        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

    //the interface of user gets otp sms
    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
        //random generate opt code
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);


        //Associate the OTP authentication code with the corresponding user's cell phone number, and bind his cell phone number to the OTPCODE using httpsession.
        //TODO: this method need to be changed, the otpCode could be write into NoSQL like:redis
        httpServletRequest.getSession().setAttribute(telphone, otpCode);


        //将OTP验证码通过短信通道发送给用户,省略
        System.out.println("telphone = " + telphone + " & otpCode = " + otpCode);


        return CommonReturnType.create(null);
    }

    //用户注册接口
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //Verify that the cell phone number matches the corresponding otpcode.
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);

        //TODO :  I can not get optcode from session
        if (!com.alibaba.druid.util.StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "SMS CAPTCHA does not match");
        }
        //The process of user registration
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender.byteValue());
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        // Encrypt password before setting it
        String encryptedPassword = encodeByMd5(password);
        userModel.setEncrptPassword(encryptedPassword);
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException {
        // Determination of calculation methods
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // Perform MD5 hash
        byte[] hashedBytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        // Encode hashed bytes to Base64
        String base64Encoded = Base64.getEncoder().encodeToString(hashedBytes);
        // Encrypted string
        return base64Encoded;
    }


    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //check input
        if (org.apache.commons.lang3.StringUtils.isEmpty(telphone) ||
                StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //User login service, used to verify the legitimacy of the user login.
        String encryptedPassword = encodeByMd5(password);
        UserModel userModel = userService.validateLogin(telphone, encryptedPassword);

        String uuidToken = UUID.randomUUID().toString();
        uuidToken = uuidToken.replace("-", "");


        //Establishing the link between the token and the user's login state
        redisTemplate.opsForValue().set(uuidToken, userModel);
        redisTemplate.expire(uuidToken, 1, TimeUnit.HOURS);

        //Add login credentials to the session where the user logged in successfully
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(uuidToken);
    }

}
