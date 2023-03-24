package tools.coffee.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.stylesheets.LinkStyle;
import tools.coffee.domain.ExamItem;
import www.m01.tools.utils.HttpUtils;

/**
 * CallUtils
 *
 * @author: hyr
 * @date: 2022-08-10
 **/
@Slf4j
public class CallUtils {

    public static void main(String[] args) {
        JSONArray data = callCoffee();



        List<ExamItem> exams = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            exams.add(JSONObject.parseObject(data.getString(i), ExamItem.class));
        }
        Map<Integer, List<ExamItem>> collect = exams.stream()
            .collect(Collectors.groupingBy(e -> e.getType()));
        log.info("type : {}", collect.keySet());
        WordUtils.generate("中级咖啡师笔试习题一（有答案）", "中级咖啡师笔试一", collect.get(0), collect.get(3));
        WordUtils.generateNoAnswer("中级咖啡师笔试习题一（无答案）", "中级咖啡师笔试一", collect.get(0), collect.get(3));


        data = callCoffee2();
        exams = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            exams.add(JSONObject.parseObject(data.getString(i), ExamItem.class));
        }
        collect = exams.stream()
            .collect(Collectors.groupingBy(e -> e.getType()));
        log.info("type : {}", collect.keySet());
        WordUtils.generate("中级咖啡师笔试习题二（有答案）", "中级咖啡师笔试二", collect.get(0), collect.get(3));
        WordUtils.generateNoAnswer("中级咖啡师笔试习题二（无答案）", "中级咖啡师笔试二", collect.get(0), collect.get(3));
    }

    public static JSONArray callCoffee() {
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%221825dad27851c-06649b66cd1fc4-26021a51-2073600-1825dad27861b6%22%7D; ko_token=616e79567e0cfe628681d28cdae86759; shop_version_type=4; sa_jssdk_2015_appjushlz798326_h5_xiaoeknow_com=%7B%22distinct_id%22%3A%22u_6211dbcc922a3_Z7p7V1huOW%22%2C%22first_id%22%3A%221825dad27851c-06649b66cd1fc4-26021a51-2073600-1825dad27861b6%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%7D; dataUpJssdkCookie={\"wxver\":\"\",\"net\":\"\",\"sid\":\"1660114289834_xddjri\"}; logintime=1660114577");
        headers.put("content-type", "application/x-www-form-urlencoded");
        headers.put("origin", "https://appjushlz798326.h5.xiaoeknow.com");
        headers.put("referer", "https://appjushlz798326.h5.xiaoeknow.com/evaluation_wechat/examination/detail/ex_61b81568284ec_K3Q3tQa5?content_app_id=");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        Map<String, Object> params = new HashMap<>();
        params.put("bizData[app_id]", "appjushlz798326");
        params.put("bizData[user_id]", "u_6211dbcc922a3_Z7p7V1huOW");
        params.put("bizData[exam_id]", "ex_61b81568284ec_K3Q3tQa5");
        params.put("bizData[uexam_id]", "uexam_62f35692149fc_x2vqifjaYK");
        String post = HttpUtils.post(
            "https://appjushlz798326.h5.xiaoeknow.com/evaluation_wechat/exam/get_question_list", headers,
            params);
        JSONArray data = JSONObject.parseObject(post).getJSONArray("data");
        ExamItem examItem = JSONObject.parseObject(data.getString(0), ExamItem.class);
        log.info("size : {}, one exam : {}", data.size(), examItem);
        return data;
    }

    public static JSONArray callCoffee2() {
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%221825dad27851c-06649b66cd1fc4-26021a51-2073600-1825dad27861b6%22%7D; ko_token=616e79567e0cfe628681d28cdae86759; shop_version_type=4; sa_jssdk_2015_appjushlz798326_h5_xiaoeknow_com=%7B%22distinct_id%22%3A%22u_6211dbcc922a3_Z7p7V1huOW%22%2C%22first_id%22%3A%221825dad27851c-06649b66cd1fc4-26021a51-2073600-1825dad27861b6%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%7D; dataUpJssdkCookie={\"wxver\":\"\",\"net\":\"\",\"sid\":\"1660114289834_xddjri\"}; logintime=1660114577");
        headers.put("content-type", "application/x-www-form-urlencoded");
        headers.put("origin", "https://appjushlz798326.h5.xiaoeknow.com");
        headers.put("referer", "https://appjushlz798326.h5.xiaoeknow.com/evaluation_wechat/examination/detail/ex_61b81568284ec_K3Q3tQa5?content_app_id=");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        Map<String, Object> params = new HashMap<>();
        params.put("bizData[app_id]", "appjushlz798326");
        params.put("bizData[user_id]", "u_6211dbcc922a3_Z7p7V1huOW");
        params.put("bizData[exam_id]", "ex_62ea38e00b7e9_b09MnyRY");
        params.put("bizData[uexam_id]", "uexam_62f3749b69651_c1S3b89Iaf");
        String post = HttpUtils.post(
            "https://appjushlz798326.h5.xiaoeknow.com/evaluation_wechat/exam/get_question_list", headers,
            params);

        JSONArray data = JSONObject.parseObject(post).getJSONArray("data");
        ExamItem examItem = JSONObject.parseObject(data.getString(0), ExamItem.class);
        log.info("size : {}, one exam : {}", data.size(), examItem);
        return data;
    }


}
