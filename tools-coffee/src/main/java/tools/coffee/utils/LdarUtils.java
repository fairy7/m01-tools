package tools.coffee.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import www.m01.tools.utils.HttpUtils;

/**
 * LdarUtils
 *
 * @author: hyr
 * @date: 2022-09-28
 **/
@Slf4j
public class LdarUtils {


//    private final static String base_url = "https://syzhhb.csdn.shangyu.gov.cn";

    private final static String base_url = "http://58.210.34.82:2081/";

    private static Integer num = 3;

    public static void main(String[] args) {
        for (int i = 0; i < num; i++) {
            log.info("index : {}", i);
            String pkid = create();
            createAct(pkid);
        }
//        createAct("21-浙江秦燕科技股份有限公司-33330682000831-COD-20221011180000-test");
    }

    private static String create() {
        JSONObject params = new JSONObject();
        params.put("featureId", "17");
        params.put("address", "零直排地址" + mockInt(503,504));
        params.put("complaint_subject", "Our solar system orbits the center of the Milky Way galaxy at about 515,000 mph (828,000 kph). We’re in one of the galaxy’s four spiral arms.");
        params.put("enterpriseId", "enterpriseId4");
        params.put("enterpriseName", "零直排公司" + mockInt(503, 504));
        params.put("socialCreditCode", "91330604MA2D6WQWXM");
        params.put("eventLevel", "1");
        params.put("latitude", "0");
        params.put("longitude", "0");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, -mockInt(1, 100));
        params.put("monitorTime", DateUtil.format(cal.getTime(), "yyyy-MM-dd HH:mm:ss"));
        params.put("operator", "edward3");
        params.put("polKey", "drs_test");
        String post = HttpUtils.postJson(
            base_url  + "/sydc-bs/thirdParty/warn-event/create-warn-event",null,
            params);
        JSONObject data = JSONObject.parseObject(post);
        log.info("event : {}", data);
        return data.getString("data");
    }

    private static void createAct(String pkid) {
        JSONObject params = new JSONObject();
        params.put("pkid", pkid);
        params.put("personIds", "admin;");
        params.put("personNames", "管理员;");
        params.put("describe", "污水 test, Our solar system orbits the center of the Milky Way galaxy at about 515,000 mph (828,000 kph). We’re in one of the galaxy’s four spiral arms.");
        params.put("operator", "管理员");
        String post = HttpUtils.postJson(
            base_url + "/sydc-bs/thirdParty/warn-event/add-common-zf-event",null,
            params);
        log.info("act : {}", post);
    }

    private static int mockInt(int start, int end) {
        return (int) (Math.random() * (end - start)) + start;
    }
}
