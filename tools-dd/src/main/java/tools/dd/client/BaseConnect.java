package tools.dd.client;

import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import tools.dd.config.DingTalkConfig;

/**
 * BaseConnect
 *
 * @author: hyr
 * @date: 2022-05-09
 **/
public class BaseConnect {

    protected static DingTalkConfig dingTalkConfig;

    public BaseConnect() {
        dingTalkConfig = new DingTalkConfig();
        dingTalkConfig.setDomainName("openplatform.dg-work.cn");
        dingTalkConfig.setAppKey("szhb2-41rnDI54YBF5xJ0IKDyENbI3");
        dingTalkConfig.setAppSecret("082AiJV1P12w3ZSqBGP1u5ma51QIUQeASRryJkG3");
        dingTalkConfig.setTenantId("50415305");

        dingTalkConfig.setDomainName("openplatform.dg-work.cn");
        dingTalkConfig.setAppKey("XINCHANGPROJECT-5b6Ms2STFveM3u");
        dingTalkConfig.setAppSecret("0yRGI2ibHYCWqPIVu6D9M0Vho9FBCi2hGT207wwG");
        dingTalkConfig.setTenantId("50415305");
    }

    public ExecutableClient getClient() {
        ExecutableClient executableClient = ExecutableClient.getInstance();
        executableClient.setAccessKey(dingTalkConfig.getAppKey());
        executableClient.setSecretKey(dingTalkConfig.getAppSecret());
        executableClient.setDomainName(dingTalkConfig.getDomainName());
        executableClient.setProtocal("https");
        executableClient.init();
        return executableClient;
    }

}
