package tools.coffee.utils;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import tools.coffee.domain.MsgSubmit;
import www.m01.tools.utils.HttpUtils;

/**
 * TcMasMsg
 *
 * @author: hyr
 * @date: 2023-01-04
 **/
@Slf4j
public class TcMasMsg {

    public static void main(String[] args) {
//        params();
        sendMsg();
    }

    private static void sendMsg() {
        String url = "http://112.35.1.155:1992/sms/norsubmit";
        String body = params();
        HttpRequest post = HttpUtil.createPost(url);
        post.body(body);
        String response = post.timeout(3 * 1000).execute().body();
        log.info("res : [{}]", response);
    }

    private static String params() {
        MsgSubmit msgSubmit = new MsgSubmit();
        msgSubmit.setEcName("太仓市水务局");
        msgSubmit.setApId("tcswj");
        msgSubmit.setSecretKey("Tcshdc123!");
        msgSubmit.setSign("sqh8kCgrd");
        msgSubmit.setMobiles("18068010605");
        msgSubmit.setContent("hyr 测试短信");
        msgSubmit.setAddSerial("");

        StringBuffer sb = new StringBuffer();
        sb.append(msgSubmit.getEcName());
        sb.append(msgSubmit.getApId());
        sb.append(msgSubmit.getSecretKey());
        sb.append(msgSubmit.getMobiles());
        sb.append(msgSubmit.getContent());
        sb.append(msgSubmit.getSign());
        sb.append(msgSubmit.getAddSerial());
        String mac = MD5.create().digestHex(sb.toString(), StandardCharsets.UTF_8);
        log.info("mac : [{}]", mac);
        msgSubmit.setMac(mac);

        String param = JSON.toJSONString(msgSubmit);
        log.info("param : [{}]", param);

        String encode = Base64.getEncoder().encodeToString(param.getBytes());
        log.info("encode : [{}]", encode);
        return encode;
    }
}
