package tools.coffee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * T2
 *
 * @author: hyr
 * @date: 2022-12-23
 **/
@Slf4j
public class T2 {


    public static void main(String[] args) {
        test01();
    }


    private static void t01() {
        String s= "{\"data\":\"{center=00, deviceId=0027039027, password=04d2, funcCode=33, 报文长度=51, 报文起始符=02, serial=fda8, sendTime=221228173532, 遥测站地址标识符=f1f1, 遥测站地址=0027039027, 遥测站类型=01, 观测时间标识符=f0f0, time=20221221132500, waterLevelP=9999.999, dtemp=10.4, signal=50.0, 遥测站状态及报警信息=0.0, vt=12.65, charge=100.0, cn=2011}\",\"dataId\":\"9099eaa2-ed6b-45de-b35b-946bb90d442e\",\"deviceId\":\"0027039027\",\"serviceId\":\"SL651_property\",\"time\":1672219787156}";
        JSONObject json = JSONObject.parseObject(s);

    }

    private static void t02() {
        Map<String,Object> data = new LinkedHashMap<>();
        data.put("center", "00");
        data.put("deviceId", "deviceId");
        log.info("toString [{}]", data.toString());
        log.info("toJsonString [{}]", JSON.toJSONString(data));
    }

    private static void test01() {
        File dest = new File("D:/download" + "/a/b" + "/tt.zip");
        if (!dest.exists()) {
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
        }

        File source = new File("D:\\download\\v2rayN-Core.zip");
        if (source.exists()) {
            try (InputStream inputStream = Files.newInputStream(source.toPath())){
                MockMultipartFile mockMultipartFile = new MockMultipartFile(source.getName(),
                    inputStream);
                mockMultipartFile.transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
