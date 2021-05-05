package com.dy.service.impl;


import com.dy.common.constant.GlobalConstants;
import com.dy.common.constant.UserConstants;
import com.dy.manager.sms.Sms;
import com.dy.manager.sms.SmsManager;
import com.dy.service.MessageService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.cr.v20180321.models.SmsTemplate;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.AddSmsTemplateRequest;
import com.tencentcloudapi.sms.v20190711.models.AddSmsTemplateResponse;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private Producer producer;

    @Autowired
    private SmsManager smsManager;

    @Override
    public boolean sendMessage(String phone, HttpServletRequest request) {
        String captcha = producer.createText();
        //保存手机号和验证码到session
        request.getSession().setAttribute(GlobalConstants.SMS_CAPTCHA_SESSION_KEY,captcha);
        request.getSession().setAttribute(UserConstants.PHONE,phone);
        Sms sms = new Sms(phone,captcha);
        if(smsManager.sendSms(sms)){
            return true;
        }
        return false;
    }
}