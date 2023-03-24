package tools.ffmpeg.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.ffmpeg.service.CoffeeService;

/**
 * PegCpntroller
 *
 * @author: hyr
 * @date: 2022-07-28
 **/
@RestController
@RequestMapping("/peg")
@Slf4j
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

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
