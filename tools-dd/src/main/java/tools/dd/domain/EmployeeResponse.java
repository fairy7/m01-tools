package tools.dd.domain;

/**
 * EmployeeReponse
 *
 * @author: hyr
 * @date: 2022-04-27
 **/
public class EmployeeResponse {

    private Long accountId;
    private String employeeName;
    private String govEmpAvatar;
    private String account;
    private String employeeCode;
    private String status;
    private String nameSpace;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long accountId, String employeeName, String account,
        String employeeCode, String nameSpace) {
        this.accountId = accountId;
        this.employeeName = employeeName;
        this.account = account;
        this.employeeCode = employeeCode;
        this.nameSpace = nameSpace;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGovEmpAvatar() {
        return govEmpAvatar;
    }

    public void setGovEmpAvatar(String govEmpAvatar) {
        this.govEmpAvatar = govEmpAvatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
            "accountId='" + accountId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", govEmpAvatar='" + govEmpAvatar + '\'' +
            ", account='" + account + '\'' +
            ", employeeCode='" + employeeCode + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
