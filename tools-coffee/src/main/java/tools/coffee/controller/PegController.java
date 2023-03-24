package tools.coffee.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.coffee.service.PegService;

/**
 * PegCpntroller
 *
 * @author: hyr
 * @date: 2022-07-28
 **/
@RestController
@RequestMapping("/peg")
@Slf4j
public class PegController {

    @Autowired
    private PegService pegService;

    @ApiOperation("视频切片测试")
    @PostMapping("/cut-video")
    public String cutVideo(String videoName, String exportName) {
        long time0 = System.currentTimeMillis();
        pegService.cutVideo(videoName, exportName);
        log.info("success, cost : {} ms", System.currentTimeMillis() - time0);
        return String.format("success, cost : %s ms", System.currentTimeMillis() - time0);
    }

    @ApiOperation("post测试")
    @PostMapping("/post-test")
    public Object postTest(String time0, String time1) {
        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("msg", "sad");
        json.put("data", Arrays.asList(time0, time1));
        return json;
    }


}
