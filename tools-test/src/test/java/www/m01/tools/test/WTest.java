package www.m01.tools.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import www.m01.tools.utils.HttpUtils;

/**
 * WTest
 *
 * @author: hyr
 * @date: 2022-08-02
 **/
@Slf4j
public class WTest {

    public static void main(String[] args) {
        test02();

    }

    private static void test01() {
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "sajssdk_2015_new_user_appjushlz798326_h5_xiaoeknow_com=1; sensorsdata2015jssdkcross=%7B%22%24device_id%22%3A%221825dad27851c-06649b66cd1fc4-26021a51-2073600-1825dad27861b6%22%7D; sa_jssdk_2015_appjushlz798326_h5_xiaoeknow_com=%7B%22distinct_id%22%3A%221825dad27851c-06649b66cd1fc4-26021a51-2073600-1825dad27861b6%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%7D; ko_token=915b4e1fdf1e7eee34a076d86259b781; logintime=1659429262; dataUpJssdkCookie={\"wxver\":\"\",\"net\":\"\",\"sid\":\"1659429036969_skwkow\"}");
        headers.put("content-type", "application/x-www-form-urlencoded");
        headers.put("origin", "https://appjushlz798326.h5.xiaoeknow.com");
        headers.put("referer", "https://appjushlz798326.h5.xiaoeknow.com/evaluation_wechat/examination/detail/ex_61b81568284ec_K3Q3tQa5?content_app_id=");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        Map<String, Object> params = new HashMap<>();
        params.put("bizData[exam_id]", "ex_61b81568284ec_K3Q3tQa5");
        params.put("bizData[come_type]", 1);
        String post = HttpUtils.post(
            "https://appjushlz798326.h5.xiaoeknow.com/evaluation_wechat/exam/join_exam", headers,
            params);

        JSONObject data = JSONObject.parseObject(post).getJSONObject("data");
        JSONArray list = data.getJSONArray("exam_list");
        System.out.println(list.get(0));
    }


    private static void test02() {
        String pattern  = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,20}$";
        // 最少8个最多十个字符，至少1个大写字母，1个小写字母，1个数字和1个特殊字符：
        String pattern2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}";
        String password1 = "asd123asxsxd";
        String password2 = "asd123asx1#%A";
        String password3 = "at3^*SR~!@#$%^&*,.";
        check1(password1);
        check2(password1);
        check1(password2);
        check2(password2);
        check1(password3);
        check2(password3);
    }

    private static void check1(String content) {
        /**
         * 1.密码必须由小写字母、大写字母、数字、特殊符号组成，区分大小写
         * 2.特殊符号包含（._~!@#$%^&*,）
         * 3.密码长度为8-20位
         */
        String pattern  = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[._~!@#$%^&*,])[A-Za-z0-9._~!@#$%^&*,]{8,20}$";
        log.info("{}, check1 : {}", content, Pattern.matches(pattern, content));
    }

    private static void check2(String content) {
        String pattern2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}";
        log.info("{}, check2 : {}", content, Pattern.matches(pattern2, content));
    }


}
