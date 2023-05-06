package tools.coffee.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.HttpResponse;
import org.junit.Test;
import www.m01.tools.utils.HttpUtils;

/**
 * SignUtils
 *
 * @author: hyr
 * @date: 2023-05-06
 **/
@Slf4j
public class SignUtils {




    @Test
    public void test() {
        List<String> codes = Arrays.asList("9a475920db7540ffb248f3aaa7228f80_MHC2023011710");
        JSONArray array = new JSONArray();
        array.addAll(codes);
        log.info("{}", array.toString());
        log.info("{}", new Date().getTime());
    }


    @Test
    public void test01() {

        String start = "2023-04-28 00:10:00";
        String end = "2023-04-28 20:10:00";
        List<String> codes = Arrays.asList("9a475920db7540ffb248f3aaa7228f80_MHC2023011710");

        JSONObject query = new JSONObject();
        query.put("start", DateUtil.parse(start).getTime()/1000);
        query.put("end", DateUtil.parse(end).getTime()/1000);
        JSONArray array = new JSONArray();
        array.addAll(codes);
        query.put("codes", array);

//        String url = "http://58.211.58.123:9000/test-api-3.2.0/third-api/out_api/sw/data-provide/6dd8e0c0b0c211ed8caa0242ac110003/history-data";
//
//        HttpRequest post = HttpUtil.createPost(url + authString(query));
//
//        post.body(query.toJSONString());
//        authList(query).forEach(json -> {
//            post.form(json.getString("k"), json.getString("v"));
//        });

//        String body = post.execute().body();

//        String body = HttpUtil.post(url + authString(query), query.toJSONString());

        String body;
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("content-type", "application/json");
            HttpResponse httpResponse = HttpUtils.doPost("http://58.211.58.123:9000", "/test-api-3.2.0/third-api/out_api/sw/data-provide/6dd8e0c0b0c211ed8caa0242ac110003/history-data",
                headers, authMap(query), query.toJSONString());
            body = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JSON.toJSONString(body, SerializerFeature.PrettyFormat,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteDateUseDateFormat);
        log.info("\n{}", body);
    }

    public String authString(Map<String, Object> query) {
        String appKey = "71a8e08ca1122c61faff1abffcbc8226b9a2e940";
        String appSecret = "123456789";
        Long timestamp = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("appKey", appKey);
        paramMap.put("timestamp", timestamp);
        paramMap.put("nonce", nonce);
        paramMap.putAll(query);
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        buffer.deleteCharAt(buffer.length() - 1);
        String aa = buffer.toString();
        String sign = HmacUtils.hmacSha256Hex(appSecret, aa);
        System.out.println(aa + "&sign=" + sign);
        return String.format("?%s&sign=%s", aa, sign);
    }

    public List<JSONObject> authList(Map<String, Object> query) {
        String appKey = "71a8e08ca1122c61faff1abffcbc8226b9a2e940";
        String appSecret = "123456789";
        Long timestamp = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString().replaceAll("-","");
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("appKey",appKey);
        paramMap.put("timestamp",timestamp);
        paramMap.put("nonce",nonce);
        paramMap.putAll(query);
        StringBuffer buffer = new StringBuffer();
        for(Map.Entry<String,Object> entry: paramMap.entrySet()){
            buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        buffer.deleteCharAt(buffer.length()-1);
        String aa = buffer.toString();
        String sign = HmacUtils.hmacSha256Hex(appSecret, aa);
        String rs = String.format("?%s&sign=%s", aa, sign);
        System.out.println(rs);
        paramMap.put("sign", sign);
        List<JSONObject> js = new ArrayList<>();
        paramMap.forEach((k ,v) -> {
            JSONObject item = new JSONObject();
            item.put("k", k);
            item.put("v", v);
            js.add(item);
        });
        return js;
    }

    public Map<String, String> authMap(Map<String, Object> query) {
        String appKey = "71a8e08ca1122c61faff1abffcbc8226b9a2e940";
        String appSecret = "123456789";
        Long timestamp = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString().replaceAll("-","");
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("appKey",appKey);
        paramMap.put("timestamp",timestamp);
        paramMap.put("nonce",nonce);
        paramMap.putAll(query);
        StringBuffer buffer = new StringBuffer();
        for(Map.Entry<String,Object> entry: paramMap.entrySet()){
            buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        buffer.deleteCharAt(buffer.length()-1);
        String aa = buffer.toString();
        String sign = HmacUtils.hmacSha256Hex(appSecret, aa);
        String rs = String.format("?%s&sign=%s", aa, sign);
        System.out.println(rs);
        paramMap.put("sign", sign);
        Map<String, String> js = new TreeMap<>();
        paramMap.forEach((k ,v) -> {
            js.put(k, v.toString());
        });
        return js;
    }
}
