package tools.dd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * DingTalkConfig
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
//@Component
//@ConfigurationProperties(prefix = "dtalk")
public class DingTalkConfig {

    /**
     * DomainName不同环境对应不同域名
     */
    private String domainName;

    /**
     * 应用的唯一标识key
     */
    private String appKey;

    /**
     * 应用的密钥
     */
    private String appSecret;

    /**
     * 租户id
     */
    private String tenantId;

    public String getDomainName() {
        return domainName;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
