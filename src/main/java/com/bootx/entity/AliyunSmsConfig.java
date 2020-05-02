package com.bootx.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bootx_aliyun_sms_config")
public class AliyunSmsConfig extends BaseEntity<Long> {

  private String regionId;

  private String accessKeyId;

  private String secret;


  public String getRegionId() {
    return regionId;
  }

  public void setRegionId(String regionId) {
    this.regionId = regionId;
  }

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
