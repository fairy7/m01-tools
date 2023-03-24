package www.m01.tools.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * JsonTest
 *
 * @author: hyr
 * @date: 2022-07-14
 **/
@Slf4j
public class JsonTest {

    public static void main(String[] args) {
        List<User> list = Arrays.asList(new User("a", 1), new User("b", 2));
        String s = JSON.toJSONString(list);
        log.info("str : {}", s);
        List<User> list1 = JSON.parseArray(s, User.class);

        list1.forEach(u -> {
            log.info("name : {}, age : {}", u.getName(), u.getAge());
        });
        log.info("list : {}", list1);

    }
}
