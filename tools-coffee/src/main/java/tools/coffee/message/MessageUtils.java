package tools.coffee.message;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.teaopenapi.models.Config;
import darabonba.core.client.ClientOverrideConfiguration;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessageClient
 *
 * @author: hyr
 * @date: 2022-04-11
 **/
@Slf4j
public class MessageUtils {


    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private static MessageConfig messageConfig;

    public static void warnMsgSend(String templateCode, String[] phoneNums, String params)
        throws Exception {
        warnMsgSend(templateCode, phoneNums, params, false);
    }

    public static void warnMsgSend(String templateCode, String[] phoneNums, String params,
        boolean async)
        throws Exception {
        if (phoneNums == null || phoneNums.length == 0) {
            return;
        }

        String phones = String.join(",", phoneNums);
        if (async) {
            AsyncConnect connect = new AsyncConnect();
            connect.send(phones, messageConfig.getWarnSignName(), templateCode, params);
        } else {
            SyncConnect connect = new SyncConnect();
            connect.send(phones, messageConfig.getWarnSignName(), templateCode, params);
        }
    }

    /**
     * 同步发送
     */
    static class SyncConnect {

        public SyncConnect() {
            if (messageConfig == null) {
                messageConfig = new MessageConfig();
            }
        }

        /**
         * 使用AK&SK初始化账号Client
         *
         * @return Client
         * @throws Exception
         */
        public com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
            Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(messageConfig.getAccessKeyId())
                // 您的AccessKey Secret
                .setAccessKeySecret(messageConfig.getAccessKeySecret());
            // 访问的域名
            config.endpoint = messageConfig.getEndpoint();
            return new com.aliyun.dysmsapi20170525.Client(config);
        }

        public void send(String phoneNumbers, String signName, String templateCode,
            String templateParams) throws Exception {
            com.aliyun.dysmsapi20170525.Client client = createClient();
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumbers)
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setTemplateParam(templateParams);
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            logger
                .info("短信发送, template code : {}, response :  code {}, message {}", templateCode,
                    sendSmsResponse.getBody().getCode(),
                    sendSmsResponse.getBody().getMessage());
            if (!"OK".equals(sendSmsResponse.getBody().getCode())) {
                throw new Exception(String
                    .format("短信发送失败，错误码：%s，错误信息：%s", sendSmsResponse.getBody().getCode(),
                        sendSmsResponse.getBody().getMessage()));
            }
        }
    }

    /**
     * 异步发送
     */
    static class AsyncConnect {

        private AsyncConnect() {
            if (messageConfig == null) {
                messageConfig = new MessageConfig();
            }
        }

        private void send(String phoneNumbers, String signName, String templateCode,
            String templateParams)
            throws ExecutionException, InterruptedException {
            // HttpClient Configuration
            /*HttpClient httpClient = new ApacheAsyncHttpClientBuilder()
                    .connectionTimeout(Duration.ofSeconds(10)) // Set the connection timeout time, the default is 10 seconds
                    .responseTimeout(Duration.ofSeconds(10)) // Set the response timeout time, the default is 20 seconds
                    .maxConnections(128) // Set the connection pool size
                    .maxIdleTimeOut(Duration.ofSeconds(50)) // Set the connection pool timeout, the default is 30 seconds
                    // Configure the proxy
                    .proxy(new ProxyOptions(ProxyOptions.Type.HTTP, new InetSocketAddress("<your-proxy-hostname>", 9001))
                            .setCredentials("<your-proxy-username>", "<your-proxy-password>"))
                    // If it is an https connection, you need to configure the certificate, or ignore the certificate(.ignoreSSL(true))
                    .x509TrustManagers(new X509TrustManager[]{})
                    .keyManagers(new KeyManager[]{})
                    .ignoreSSL(false)
                    .build();*/

            // Configure Credentials authentication information, including ak, secret, token
            StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(messageConfig.getAccessKeyId())
                .accessKeySecret(messageConfig.getAccessKeySecret())
                //.securityToken("<your-token>") // use STS token
                .build());
            // Anonymous access method (requires API support)
            // AnonymousCredentialProvider provider = AnonymousCredentialProvider.create();

            // Configure the Client
            AsyncClient client = AsyncClient.builder()
                .region("cn-qingdao") // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                    ClientOverrideConfiguration.create()
                        .setEndpointOverride(messageConfig.getEndpoint())
                    //.setReadTimeout(Duration.ofSeconds(30))
                )
                .build();
            try {
                // Parameter settings for API request
//                com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest
//                    .builder()
//                    .phoneNumbers(phoneNumbers)
//                    .signName(signName)
//                    .templateCode(templateCode)
//                    .templateParam(templateParams.toString())
//                    // Request-level configuration rewrite, can set Http request parameters, etc.
//                    // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
//                    .build();
//
//                // Asynchronously get the return value of the API request
//                CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
//                // Synchronously get the return value of the API request
//                SendSmsResponse resp = response.get();
                // Asynchronous processing of return values
                /*response.thenAccept(resp -> {
                    System.out.println(new Gson().toJson(resp));
                }).exceptionally(throwable -> { // Handling exceptions
                    System.out.println(throwable.getMessage());
                    return null;
                });*/
            } finally {
                // Finally, close the client
                client.close();
            }

        }
    }

}
