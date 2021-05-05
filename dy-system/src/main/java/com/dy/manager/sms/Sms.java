package com.dy.manager.sms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author cxj
 */
@Getter
public class Sms {
    /** 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
    private String sign = "望幽的读书笔记";

    /** 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
    private String senderid = null;

    /** 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
    @Setter
    private String session = "xxx";

    /** 短信码号扩展号: 默认未开通，如需开通请联系 [sms helper] */
//    private String extendcode = "xxx";

    /** 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID */
    private String templateID = "934213";

    /** 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
     * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
    @Setter
    private String[] phoneNumbers;

    /** 模板参数: 若无模板参数，则设置为空*/
    @Setter
    private String[] templateParams;

    public Sms(String phone, String captcha){
        phone = "+86"+phone;
        String[] phones = {phone};
        this.phoneNumbers = phones;
        String[] params = {captcha};
        templateParams = params;
    }
}
