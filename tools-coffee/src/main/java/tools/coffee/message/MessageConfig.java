package tools.coffee.message;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Config
 *
 * @author: hyr
 * @date: 2022-04-11
 *
 * message.aliyun.accessKeyId=LTAI4G2axRMZ9jmC3E5qBF8E
 * # AccessKey Secret
 * message.aliyun.accessKeySecret=5geuhCHqZscMhb8MQRz5R08fgS2uQ9
 * # 访问的域名
 * message.aliyun.endpoint=dysmsapi.aliyuncs.com
 * # 预警短信签名
 * message.aliyun.warnSignName=上虞分局智慧环保指挥中心
 **/
public class MessageConfig {

    private String accessKeyId = "LTAI4G2axRMZ9jmC3E5qBF8E";
    private String accessKeySecret = "5geuhCHqZscMhb8MQRz5R08fgS2uQ9";
    private String endpoint = "dysmsapi.aliyuncs.com";
    private String warnSignName = "上虞分局智慧环保指挥中心";

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getWarnSignName() {
        return warnSignName;
    }

    public void setWarnSignName(String warnSignName) {
        this.warnSignName = warnSignName;
    }

    @Override
    public String toString() {
        return "MessageConfig{" +
            "endpoint='" + endpoint + '\'' +
            ", warnSignName='" + warnSignName + '\'' +
            '}';
    }
}
