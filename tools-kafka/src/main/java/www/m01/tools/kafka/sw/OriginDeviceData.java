package www.m01.tools.kafka.sw;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备原始数据对象 time_origin_device_data
 *
 * @author ruoyi
 * @date 2022-07-01
 */
public class OriginDeviceData {

    private static final long serialVersionUID = -9166411017977893754L;

    /**
     * $column.columnComment
     */
    private String dataId;

    /**
     * $column.columnComment
     */
    private String deviceId;

    /**
     * $column.columnComment
     */
    private String data;

    /**
     * $column.columnComment
     */
    private Date time;

    public OriginDeviceData(String dataId, String deviceId, String data, Date time) {
        this.dataId = dataId;
        this.deviceId = deviceId;
        this.data = data;
        this.time = time;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("dataId", getDataId())
            .append("deviceId", getDeviceId())
            .append("data", getData())
            .append("time", getTime())
            .toString();
    }
}
