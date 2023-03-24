package tools.coffee.message;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * SendTest
 *
 * @author: hyr
 * @date: 2023-01-17
 **/
@Slf4j
public class SendTest {

    public static void main(String[] args) {
        send26();
    }

    public static void send26() {
        String templateCode = "SMS_238468419";
        String received = "18068010605";
        JSONObject params = new JSONObject();
        params.put("outPutName", "标排口");
        params.put("companyName", "浙江金塔克斯科技有限公司");
        params.put("time", "2023年01月17日06时");
        params.put("factor", "4");
        try {
            MessageUtils
                .warnMsgSend(templateCode,
                    received.split(","),
                    params.toJSONString());
        } catch (Exception e) {
            log.error("error!", e);
        }
    }

    public static void send22() {
        String templateCode = "SMS_238468413";
        String received = "18068010605";
        JSONObject params = new JSONObject();
        params.put("outPutName", "标排口");
        params.put("companyName", "浙江金塔克斯科技有限公司");
        params.put("time", "2023年01月17日06时至09时");
        params.put("factor", "4");
        try {
            MessageUtils
                .warnMsgSend(templateCode,
                    received.split(","),
                    params.toJSONString());
        } catch (Exception e) {
            log.error("error!", e);
        }
    }

}
