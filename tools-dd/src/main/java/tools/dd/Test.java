package tools.dd;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import tools.dd.domain.MsgLink;
import tools.dd.domain.MsgTemplate;
import tools.dd.utils.WorkNoticeUtil;

/**
 * tt
 *
 * @author: hyr
 * @date: 2022-12-07
 **/
@Slf4j
public class Test {

    public static void main(String[] args) {
        time();
    }

    private static void time() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date end = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date start = calendar.getTime();

        log.info("{}", start);
        log.info("{}", end);
    }

    private static void dingding() {
        // {data={lastName=张领, realmId=50415305, clientId=szhb2, openid=42825cc772ef60deb56404f0c74140f0, realmName=欧梯恩智能科技（苏州）有限公司, nickNameCn=张领, tenantUserId=50415305$848091, employeeCode=GE_8a774e29afdc4b4f8a2903b9660a86df, accountId=848091, tenantName=欧梯恩智能科技（苏州）有限公司, referId=848091, namespace=local, tenantId=50415305, account=zl-rjyfb}, success=true, responseMessage=成功, responseCode=0}
//        String ddId = "848091";
        // {data={lastName=黄雨润, realmId=50415305, clientId=szhb2, openid=9d752bb3abe6b5dc88b0050bff9769bb, realmName=欧梯恩智能科技（苏州）有限公司, nickNameCn=黄雨润, tenantUserId=50415305$822581, employeeCode=GE_1efa11f4d984414980388a4a792e9873, accountId=822581, tenantName=欧梯恩智能科技（苏州）有限公司, namespace=local, tenantId=50415305, account=hyr-rjyfb}, success=true, responseMessage=成功, responseCode=0}
        String ddId = "822581";
//        String ddId = "848091,822581";
        // shuzi 1
//        String msgUrl = "taurus://taurusclient/action/open_tiny_app?appId=0221129103606469&page=pages%2Findex%2Findex";
        // xinchang
//        String msgUrl = "taurus://taurusclient/action/open_tiny_app?appId=0220321163247589&page=pages%2Findex%2Findex";
        String msgUrl = "taurus://taurusclient/action/open_tiny_app?appId=0220321163247589&page=page%2Fcomponent%2Findex";
//        String msgUrl = "taurus://taurusclient/page/link?url=https%3A%2F%2Fwww.baidu.com&container_type=browser";
        String content = "日本文化委员会在2022年11月18日向文部科学省提交一份报告，指定将日本国立天文台NAOJ保存的一份江户幕府时代天文文献《星学手简 Seigaku Shukan》列为重要文化财产。NAOJ馆藏了许多与天文学、日历和日本数学相关的古籍，包含曾是幕府天文学家高桥至时的藏书《星学手简》。";
        MsgTemplate msgLink = new MsgLink(msgUrl, "http://58.210.34.82:2007/test-gateway-3.2.0/file/statics/test/syhb.png", "title " + System.currentTimeMillis(), content);
        System.out.println(msgLink.getContent());
//        String send = WorkNoticeUtil.send(ddId, UUID.randomUUID().toString().replace("-", ""),
//            msgLink);
//        log.info("{}", send);
        System.out.println(String.format("taurus://taurusclient/action/open_tiny_app?appId=%s&page=page%2Fcomponent%2Findex", "xxx"));
    }
}
