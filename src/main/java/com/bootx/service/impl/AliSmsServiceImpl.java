package com.bootx.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.bootx.entity.AliyunSmsConfig;
import com.bootx.service.AliSmsService;
import com.bootx.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AliSmsServiceImpl implements AliSmsService {


  private IAcsClient getClient(AliyunSmsConfig aliyunSmsConfig){
    DefaultProfile profile = DefaultProfile.getProfile(aliyunSmsConfig.getRegionId(), aliyunSmsConfig.getAccessKeyId(), aliyunSmsConfig.getSecret());
    IAcsClient client = new DefaultAcsClient(profile);
    return client;
  }

  private CommonRequest getCommonRequest(AliyunSmsConfig aliyunSmsConfig,Map<String,String> params,String sysAction){
    CommonRequest request = new CommonRequest();
    request.setSysMethod(MethodType.POST);
    request.setSysDomain("dysmsapi.aliyuncs.com");
    request.setSysVersion("2017-05-25");
    request.setSysAction(sysAction);
    request.putQueryParameter("RegionId", aliyunSmsConfig.getRegionId());

    for (String key:params.keySet()) {
      request.putQueryParameter(key,params.get(key));
    }

    return request;
  }


  private CommonResponse response(AliyunSmsConfig aliyunSmsConfig,Map<String,String> params,String sysAction){
    try {
      CommonResponse response = getClient(aliyunSmsConfig).getCommonResponse(getCommonRequest(aliyunSmsConfig,params,sysAction));
      return response;
    } catch (com.aliyuncs.exceptions.ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public CommonResponse addSmsSign(AliyunSmsConfig aliyunSmsConfig,String signName,String signSource,String remark) {
    Map<String,String> params = new HashMap<>();
    params.put("SignName",signName);
    params.put("SignSource",signSource);
    params.put("Remark",remark);
    return response(aliyunSmsConfig,params,"AddSmsSign");
  }

  @Override
  public CommonResponse addSmsTemplate(AliyunSmsConfig aliyunSmsConfig,String templateType,String templateName,String templateContent,String remark) {
    Map<String,String> params = new HashMap<>();
    params.put("TemplateType", templateType);
    params.put("TemplateName", templateName);
    params.put("TemplateContent", templateContent);
    params.put("Remark", remark);


    return response(aliyunSmsConfig,params,"AddSmsTemplate");
  }

  @Override
  public CommonResponse deleteSmsSign(AliyunSmsConfig aliyunSmsConfig,String signName) {
    Map<String,String> params = new HashMap<>();
    params.put("SignName", signName);

    return response(aliyunSmsConfig,params,"DeleteSmsSign");
  }

  @Override
  public CommonResponse deleteSmsTemplate(AliyunSmsConfig aliyunSmsConfig,String templateCode) {
    Map<String,String> params = new HashMap<>();
    params.put("TemplateCode", templateCode);

    return response(aliyunSmsConfig,params,"DeleteSmsTemplate");
  }

  @Override
  public CommonResponse modifySmsSign(AliyunSmsConfig aliyunSmsConfig,String signName,String signSource,String remark) {
    Map<String,String> params = new HashMap<>();
    params.put("SignName",signName);
    params.put("SignSource",signSource);
    params.put("Remark",remark);

    return response(aliyunSmsConfig,params,"ModifySmsSign");
  }

  @Override
  public CommonResponse modifySmsTemplate(AliyunSmsConfig aliyunSmsConfig) {
    Map<String,String> params = new HashMap<>();
    // params.put("TemplateCode", templateCode);


    return response(aliyunSmsConfig,params,"ModifySmsTemplate");
  }

  @Override
  public CommonResponse querySendDetails(AliyunSmsConfig aliyunSmsConfig) {
    Map<String,String> params = new HashMap<>();
    // params.put("TemplateCode", templateCode);

    return response(aliyunSmsConfig,params,"QuerySendDetails");
  }

  @Override
  public CommonResponse querySmsSign(AliyunSmsConfig aliyunSmsConfig) {

    Map<String,String> params = new HashMap<>();
   //  params.put("TemplateCode", templateCode);


    return response(aliyunSmsConfig,params,"QuerySmsSign");
  }

  @Override
  public CommonResponse querySmsTemplate(AliyunSmsConfig aliyunSmsConfig) {
    Map<String,String> params = new HashMap<>();
    // params.put("TemplateCode", templateCode);

    return response(aliyunSmsConfig,params,"QuerySmsTemplate");
  }

  @Override
  public CommonResponse sendBatchSms(AliyunSmsConfig aliyunSmsConfig) {
    Map<String,String> params = new HashMap<>();
    // params.put("TemplateCode", templateCode);

    return response(aliyunSmsConfig,params,"SendBatchSms");
  }

  @Override
  public CommonResponse sendSms(AliyunSmsConfig aliyunSmsConfig,String[] phoneNumbers,String signName,String templateCode,Map<String,String> templateParam,String smsUpExtendCode,String outId) {
    Map<String,String> params = new HashMap<>();
    params.put("TemplateCode", templateCode);
    params.put("PhoneNumbers", StringUtils.join(phoneNumbers,","));
    params.put("SignName", signName);
    params.put("TemplateParam", JsonUtils.toJson(templateParam));
    if(StringUtils.isNoneEmpty(smsUpExtendCode)){
      params.put("SmsUpExtendCode", smsUpExtendCode);
    }
    params.put("OutId", outId);

    return response(aliyunSmsConfig,params,"SendSms");
  }


  public static void main(String[] args) {
    // 您的验证码${code}，该验证码5分钟内有效，请勿泄漏于他人！
    String signName="金尚成";
    String templateCode = "SMS_174814740";
    Map<String,String> templateParam = new HashMap<>();
    templateParam.put("code","123456");
    //String smsUpExtendCode="smsUpExtendCode";
    String outId="outId";

    AliyunSmsConfig aliyunSmsConfig = new AliyunSmsConfig();
    aliyunSmsConfig.setAccessKeyId("Gi1uk3OIE2JQXSSb");
    aliyunSmsConfig.setSecret("RRfYbEBMMJjzZkuXMCBkfhOAoLJ2q2");
    aliyunSmsConfig.setRegionId("cn-hangzhou");
    CommonResponse commonResponse = new AliSmsServiceImpl().sendSms(aliyunSmsConfig,new String[]{
      "19971579891"
    },signName,templateCode,templateParam,null,outId);

    Map<String,String> result = JsonUtils.toObject(commonResponse.getData(), new TypeReference<Map<String,String>>() {
    });

    System.out.println(result);
  }
}
