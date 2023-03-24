package tools.coffee.jczh;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import tools.coffee.jczh.domain.WarnEvent;

/**
 * KoNo
 *
 * @author: hyr
 * @date: 2022-11-30
 **/
@Slf4j
public class KoNo {

    public static void main(String[] args) {
        List<WarnEvent> list = new ArrayList<>();
        list.add(new WarnEvent(1));
        list.add(new WarnEvent(2));
        list.add(new WarnEvent(3));
        list.add(new WarnEvent(4));
        list.sort((o1,o2) -> {return o1.getOrder() - o2.getOrder();});
        list.forEach(e -> {
            log.info("{}", e.getOrder());
        });
    }
}
