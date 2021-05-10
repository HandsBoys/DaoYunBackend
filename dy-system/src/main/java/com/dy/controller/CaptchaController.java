package com.dy.controller;

import com.dy.common.constant.GlobalConstants;
import com.dy.common.redis.RedisCacheUtils;
import com.dy.common.utils.AjaxResult;
import com.dy.service.MessageService;
import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {
    /**
     * 验证码
     */
    @Autowired
    private Producer producer;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @PostMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control","no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        //TODO: UUID
        redisCacheUtils.setCacheObject(GlobalConstants.IMAGE_CAPTCHA_SESSION_KEY, text, 2, TimeUnit.MINUTES);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);
    }

    @Operation()
    @Parameter(name = "phone", description = "电话号码", in = ParameterIn.QUERY)
    @PostMapping("/message")
    public AjaxResult<String> getSmsCaptcha(String phone){
        if(messageService.sendMessage( phone )){
            return AjaxResult.success("短信发送成功!");
        }
        return AjaxResult.error("短信发送失败!");
    }
}
