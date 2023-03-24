package www.m01.tools.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * HttpUtils
 *
 * @author: hyr
 * @date: 2022-08-02
 **/
public class HttpUtils {

    public static String post(String url, Map<String, String> headers, Map<String, Object> params) {
        HttpRequest post = HttpUtil.createPost(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach(post::header);
        }
        if (params != null && params.size() > 0) {
            post.form(params);
        }
        return post.timeout(3*1000).execute().body();
    }

    public static String postJson(String url, Map<String, String> headers, JSONObject json) {
        HttpRequest post = HttpUtil.createPost(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach(post::header);
        }
        post.body(json.toJSONString());
        return post.timeout(3*1000).execute().body();
    }

    public static String postForm(String url, Map<String, String> headers, JSONObject json) {
        HttpRequest post = HttpUtil.createPost(url);
        if (headers != null && headers.size() > 0) {
            headers.forEach(post::header);
        }
        json.forEach((k ,v) -> {
            post.form(k ,v);
        });
//        post.body(json.toJSONString());
        return post.timeout(3*1000).execute().body();
    }

}
