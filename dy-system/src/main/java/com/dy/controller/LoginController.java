package com.dy.controller;

import com.dy.core.constant.GlobalConstants;
import com.dy.core.utils.AjaxResult;
import com.dy.domain.LoginUser;
import com.dy.domain.SysUser;
import com.dy.service.LoginService;
import com.dy.service.SysUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;


/**
 * 登录操作
 */
@Api(tags="登录操作接口")
@RestController
@RequestMapping()
public class LoginController extends BaseController{
    @Autowired
    private LoginService loginService;
    @Autowired
    private SysUserService userService;

    /**
     * 验证码
     */
    @Autowired
    private Producer producer;

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException{
        response.setHeader("Cache-Control","no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存验证码到session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);
    }
    /**
     * 登录验证
     */
//    @RequestMapping("/login")
//    public AjaxResult checkLoginUser(String username,String password){
//        LoginUser user = new LoginUser(username,password);
//        AjaxResult ajax;
//        if(sysUserService.checkLoginUser(user) != null){
//            ajax = AjaxResult.success();
//        }
//        else{
//            ajax = AjaxResult.error();
//        }
//        return ajax;
//    }

    @ApiOperation(value = "登录后返回token")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginUser loginUser){
        return loginService.login(loginUser.getUserName(),loginUser.getPassword());
    }


    @ApiOperation(value = "获取登录用户的信息")
    @GetMapping("getInfo")
    public SysUser getInfo(Principal principal){
        if(principal == null){
            return null;
        }
        String username = principal.getName();
        SysUser user = userService.getUserByUserName(username);
        user.setPassword(null);
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public AjaxResult logout(){
        return AjaxResult.success("退出成功！");
    }
}
