package com.bootx.service;

import com.aliyuncs.CommonResponse;
import com.bootx.entity.AliyunSmsConfig;

import java.util.Map;

public interface AliSmsService {

  /**
   * 调用短信AddSmsSign申请短信签名。
   *
   * 您可以通过短信服务API接口或短信服务控制台申请短信签名，签名需要符合个人用户签名规范或企业用户签名规范。
   *
   * 短信签名审核流程请参考签名审核流程。
   *
   * 说明 个人用户每天最多可以申请一个短信签名，适用场景默认为通用。企业用户每天最多可以申请100个签名。
   */
  CommonResponse addSmsSign(AliyunSmsConfig aliyunSmsConfig,String signName,String signSource,String remark);

  /**
   * 添加短信模版
   */
  CommonResponse addSmsTemplate(AliyunSmsConfig aliyunSmsConfig,String templateType,String templateName,String templateContent,String remark);

  /**
   * 调用接口DeleteSmsSign删除短信签名。
   *
   * 说明
   *  不支持删除正在审核中的签名。
   *  短信签名删除后不可恢复，请谨慎操作。
   */
  CommonResponse deleteSmsSign(AliyunSmsConfig aliyunSmsConfig,String signName);

  /**
   * 删除短信模版
   */
  CommonResponse deleteSmsTemplate(AliyunSmsConfig aliyunSmsConfig,String templateCode);

  /**
   *  调用接口ModifySmsSign修改未审核通过的短信签名，并重新提交审核。
   *
   * 申请短信签名后，如果签名未通过审核，可以通过接口ModifySmsSign修改短信签名，并重新申请，提交审核。
   *
   * 签名需要符合个人用户签名规范或企业用户签名规范。短信签名审核流程请参考签名审核流程。
   *
   * @param
   *  signName
   *    签名内容。
   * @param
   *  signSource
   *  签名来源。其中：
   *
     * 0：企事业单位的全称或简称。
     * 1：工信部备案网站的全称或简称。
     * 2：APP应用的全称或简称。
     * 3：公众号或小程序的全称或简称。
     * 4：电商平台店铺名的全称或简称。
     * 5：商标名的全称或简称
     * 说明 签名来源为1时，请在申请说明中添加网站域名，加快审核速度。
   * @param
   *  remark
   *    短信签名申请说明。请在申请说明中详细描述您的业务使用场景，申请工信部备案网站的全称或简称请在此处填写域名，长度不超过200个字符。
   *
   */
  CommonResponse modifySmsSign(AliyunSmsConfig aliyunSmsConfig,String signName,String signSource,String remark);

  /**
   * 修改短信模版
   */
  CommonResponse modifySmsTemplate(AliyunSmsConfig aliyunSmsConfig);

  /**
   * 调用QuerySendDetails接口查看短信发送记录和发送状态。
   *
   * 通过调用QuerySendDetails接口，可以根据短信发送日期查看发送记录和短信内容，也可以添加发送流水号，根据流水号查询指定日期指定请求的发送详情。
   *
   * 如果指定日期短信发送量较大，可以分页查看。指定每页显示的短信详情数量和查看的页数，即可分页查看发送记录。
   */
  CommonResponse querySendDetails(AliyunSmsConfig aliyunSmsConfig);

  /**
   * 查询短信短信签名
   */
  CommonResponse querySmsSign(AliyunSmsConfig aliyunSmsConfig);

  /**
   * 查询短信模版
   */
  CommonResponse querySmsTemplate(AliyunSmsConfig aliyunSmsConfig);

  /**
   * 调用SendBatchSms接口批量发送短信。
   *
   * SendBatchSms接口是短信批量发送接口，支持在一次请求中分别向多个不同的手机号码发送不同签名的短信。手机号码等参数均为JSON格式，字段个数相同，一一对应，短信服务根据字段在JSON中的顺序判断发往指定手机号码的签名。
   *
   * 如果您需要往多个手机号码中发送同样签名的短信，请使用SendSms接口实现。
   *
   * 调用该接口发送短信时，请注意：
   *
   * 发送短信会根据发送量计费，价格请参考计费说明。
   * 在一次请求中，最多可以向100个手机号码分别发送短信。
   */
  CommonResponse sendBatchSms(AliyunSmsConfig aliyunSmsConfig);

  /**
   * 调用SendSms发送短信。
   *
   * SendSms接口是短信发送接口，支持在一次请求中向多个不同的手机号码发送同样内容的短信。
   *
   * 如果您需要在一次请求中分别向多个不同的手机号码发送不同签名和模版内容的短信，请使用SendBatchSms接口。
   *
   * 调用该接口发送短信时，请注意：
   *
   * 发送短信会根据发送量计费，价格请参考计费说明。
   * 在一次请求中，最多可以向1000个手机号码发送同样内容的短信。
   */
  CommonResponse sendSms(AliyunSmsConfig aliyunSmsConfig, String[] phoneNumbers, String signName, String templateCode, Map<String,String> templateParam, String smsUpExtendCode, String outId);
}
