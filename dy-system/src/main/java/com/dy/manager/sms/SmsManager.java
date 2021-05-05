package com.dy.manager.sms;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 短信服务
 * @author cxj
 */
@Service
public class SmsManager {
    @Value("${tencentcloud.sms.secretId}")
    private String secretId;
    @Value("${tencentcloud.sms.secretKey}")
    private String secretKey;
    /** 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
    @Value("${tencentcloud.sms.appId}")
    private String appId;

    /**
     * 发送短信
     */
    public boolean sendSms(Sms sms){
        try {
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey
             * 本示例采用从环境变量读取的方式，需要预先在环境变量中设置这两个值
             * 您也可以直接在代码中写入密钥对，但需谨防泄露，不要将代码复制、上传或者分享给他人
             * CAM 密钥查询：https://console.cloud.tencent.com/cam/capi
             */
            Credential cred = new Credential(secretId, secretKey);
            /* 实例化 SMS 的 client 对象
             * 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "");
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();
            /* 填充请求参数，这里 request 对象的成员变量即对应接口的入参
             * 您可以通过官网接口文档或跳转到 request 对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台：https://console.cloud.tencent.com/smsv2
             * sms helper：https://cloud.tencent.com/document/product/382/3773 */
            req.setSmsSdkAppid(appId);

            req.setSign(sms.getSign());

            req.setSenderId(sms.getSenderid());

            req.setSessionContext(sms.getSession());

//            req.setExtendCode(sms.getExtendcode());

            req.setTemplateID(sms.getTemplateID());

            req.setPhoneNumberSet(sms.getPhoneNumbers());

            req.setTemplateParamSet(sms.getTemplateParams());
            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);
            // 输出 JSON 格式的字符串回包
            //System.out.println(SendSmsResponse.toJsonString(res));
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            //System.out.println(res.getRequestId());

            //TODO:封装对回执信息的操作

            if("Ok".equals(res.getSendStatusSet()[0].getCode())){
                return true;
            }
            return false;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return false;
        }
    }
}
