package tools.dd.domain;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * DeptResponse:组织详情对象
 * 参考连接：https://openplatform-portal.dg-work.cn/backendManage/#/docs?apiType=serverapi&docKey=2674856
 *
 * @author: hyr
 * @date: 2022-05-10
 **/
public class DeptResponse implements Comparable<DeptResponse> {

    /**
     * 组织code
     */
    private String organizationCode;
    /**
     * 显示名称
     */
    private String organizationName;

    /**
     * 排序码
     */
    private String displayOrder;

    /**
     * 父组织Code
     */
    private String parentCode;

    /**
     * 父组织名称
     */
    private String parentName;

    /**
     * 组织状态：A - 有效的数据, F - 无效的数据
     */
    private String status;

    /**
     * 组织类型Code
     */
    private String typeCode;

    /**
     * 组织类型名称
     */
    private String typeName;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /**
     * 是否为末端节点，true为末端节点，false则说明还有子节点。
     */
    private Boolean leaf;

    /**
     * 行政区划code
     */
    private String divisionCode;

    /**
     * 组织全称
     */
    private String shortName;

    /**
     * 组织机构代码
     */
    private String institutionCode;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 父节点对象
     */
    private DeptResponse parent;

    /**
     * 子节点对象
     */
    private List<DeptResponse> children;

    /**
     * 系统设置排序
     */
    private Long orderNum;

    @Override
    public String toString() {
        return "DeptResponse{" +
            "organizationCode='" + organizationCode + '\'' +
            ", organizationName='" + organizationName + '\'' +
            ", displayOrder=" + displayOrder +
            ", parentCode='" + parentCode + '\'' +
            ", parentName='" + parentName + '\'' +
            ", status='" + status + '\'' +
            ", typeCode='" + typeCode + '\'' +
            ", typeName='" + typeName + '\'' +
            ", gmtCreate=" + gmtCreate +
            ", leaf=" + leaf +
            ", divisionCode='" + divisionCode + '\'' +
            ", shortName='" + shortName + '\'' +
            ", institutionCode='" + institutionCode + '\'' +
            ", remarks='" + remarks + '\'' +
            '}';
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public DeptResponse getParent() {
        return parent;
    }

    public void setParent(DeptResponse parent) {
        this.parent = parent;
    }

    public List<DeptResponse> getChildren() {
        return children;
    }

    public void setChildren(List<DeptResponse> children) {
        this.children = children;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public int compareTo(DeptResponse o) {
        if (StringUtils.isBlank(o.getDisplayOrder())) {
            return -1;
        }
        if (StringUtils.isBlank(this.getDisplayOrder())) {
            return 1;
        }
        return this.displayOrder.compareTo(o.displayOrder);
    }
}
