package www.m01.tools.kafka.sw;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ParseProtocolData
 *
 * @author: hyr
 * @date: 2022-07-14
 **/
public class ParseProtocolData {

    /* --------------- 必要参数 start --------------- */

    /**
     * 物模字段
     */
    private String fieldCode;

    /**
     * 当前监测数值
     */
    private BigDecimal value;

    /**
     * 监测时间
     */
    private Date time;

    /**
     * 来源编码（0：自带；1：传感）
     */
    private Integer originCode;

    /**
     * 单位
     */
    private String itemUnit;

    /* --------------- 必要参数 end --------------- */

    /**
     * 监测要素名称
     */
    private String itemName;

    /**
     * 下限
     */
    private BigDecimal lowerLimit;

    /**
     * 上限
     */
    private BigDecimal upperLimit;

    public ParseProtocolData() {
    }

    public ParseProtocolData(String fieldCode, BigDecimal value, Date time,
        Integer originCode, String itemUnit, String itemName) {
        this.fieldCode = fieldCode;
        this.value = value;
        this.time = time;
        this.originCode = originCode;
        this.itemUnit = itemUnit;
        this.itemName = itemName;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getOriginCode() {
        return originCode;
    }

    public void setOriginCode(Integer originCode) {
        this.originCode = originCode;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(BigDecimal lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public BigDecimal getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(BigDecimal upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
