package tools.dd.utils;

import org.apache.commons.lang3.StringUtils;
import tools.dd.client.DingBuilder;
import tools.dd.domain.MsgTemplate;
import tools.dd.domain.WorkNotice;
import tools.dd.ex.ServiceException;

/**
 * DingTalkUtil
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public class WorkNoticeUtil {

    /**
     * 根据不同的业务id，可以发送相同的消息内容
     *
     * @param receiverIds 消息接收人
     * @param bizMsgId    业务id
     * @param msg         消息内容
     * @return
     */
    public static String send(String receiverIds, String bizMsgId, MsgTemplate msg) {
        if (StringUtils.isBlank(receiverIds)) {
            throw new ServiceException("接收人为空");
        }
        if (msg == null || StringUtils.isBlank(msg.getContent())) {
            throw new ServiceException("消息内容为空");
        }

        WorkNotice workNotice = new WorkNotice(receiverIds, msg);
        if (!StringUtils.isBlank(bizMsgId)) {
            workNotice.setBizMsgId(bizMsgId);
        }
        return DingBuilder.sendWorkNotice(workNotice);
    }

}
