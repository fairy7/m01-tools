package tools.coffee.domain;

import lombok.Data;

/**
 * MsgSubmit
 *
 * @author: hyr
 * @date: 2023-01-04
 **/
@Data
public class MsgSubmit {

    private String ecName;

    private String apId;

    private String secretKey;

    private String mobiles;

    private String content;

    private String sign;

    private String addSerial;

    private String mac;

}
