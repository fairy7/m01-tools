package tools.coffee.jczh;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tools.coffee.jczh.domain.WarnEvent;
import www.m01.tools.utils.HttpUtils;

/**
 * RemoteUtils
 *
 * @author: hyr
 * @date: 2022-11-24
 **/
@Slf4j
public class RemoteUtils {


        private static final String url = "https://syzhhb.csdn.shangyu.gov.cn/";
//    private static final String url = "http://172.26.25.4:9528/";


    public static void mockProcess(WarnEvent event) {
        // 创建执法单
        String taskId = createAct(event);
        // 执法
        dealTask(event, taskId);
        // 文件上传
        fileTask(event, taskId);
        // 回复确认
        confirm(event);
    }


    public static String createAct(WarnEvent event) {
        JSONObject params = new JSONObject();
        params.put("pkid", event.getPkid());
        params.put("personIds", event.getCheckPersonIds());
        params.put("personNames", event.getCheckPersonNames());
        params.put("describe", event.getContent());
        params.put("operator", "管理员");
        String post = HttpUtils.postJson(
            url + "/sydc-bs/thirdParty/warn-event/add-common-zf-event", null,
            params);
        JSONObject data = JSONObject.parseObject(post);
        log.info("createAct : {}", data);
        return data.getString("data");
    }


    public static String dealTask(WarnEvent event, String taskId) {
        JSONObject params = new JSONObject();
        params.put("id", taskId);
        params.put("qyscqk", event.getQyscqk());
        params.put("jcjg", event.getJcjg());
        params.put("jcyj", event.getJcyj());
        params.put("operator", event.getOperator());
        String post = HttpUtils.postJson(
            url + "/sydc-bs/thirdParty/warn-event/feedback-event", null,
            params);
        JSONObject data = JSONObject.parseObject(post);
        log.info("dealTask : {}", data);
        return data.getString("data");
    }

    public static String fileTask(WarnEvent event, String taskId) {
        JSONObject params = new JSONObject();
        params.put("businessId", taskId);
        if (CollectionUtils.isEmpty(event.getTaskFilePath())) {
            return null;
        }
        for (String filePath : event.getTaskFilePath()) {
            MultipartFile multipartFile = convertMultipartFile(filePath);
            params.put("file", new File(filePath));
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "multipart/form-data");
            String post = HttpUtils.postForm(
                url + "/sydc-bs/thirdParty/warn-event/feedback-event-file-upload", headers,
                params);
            JSONObject data = JSONObject.parseObject(post);
            log.info("dealTask : {}", data);
        }

        return "";
    }

    private static MultipartFile convertMultipartFile(String filePath) {
        File file = new File(filePath);
        try {
            InputStream inputStream = new FileInputStream(file);
            return new MockMultipartFile(file.getName(), inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String confirm(WarnEvent event) {
        JSONObject params = new JSONObject();
        params.put("pkid", event.getPkid());
        params.put("content", event.getConfirm());
        params.put("operator", "管理员");
        Map<String, String> headers = new HashMap<>();
        String post = HttpUtils.postJson(
            url + "/sydc-bs/thirdParty/warn-event/confirm-event", headers,
            params);
        JSONObject data = JSONObject.parseObject(post);
        log.info("confirm : {}", data);
        return post;
    }


}
