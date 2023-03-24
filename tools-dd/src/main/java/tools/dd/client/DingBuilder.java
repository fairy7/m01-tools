package tools.dd.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.xxpt.gateway.shared.api.request.OapiAuthScopesV2Request;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMessageWorkNotificationRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMoziEmployeeListEmployeeAccountIdsRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMoziFusionPageSearchEmployeeRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMoziOrganizationGetOrganizationByCodeRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMoziOrganizationListOrganizationEmployeeCountRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMoziOrganizationPageOrganizationEmployeePositionsRequest;
import com.alibaba.xxpt.gateway.shared.api.request.OapiMoziOrganizationPageSubOrganizationCodesRequest;
import com.alibaba.xxpt.gateway.shared.api.response.OapiAuthScopesV2Response;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMessageWorkNotificationResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMoziEmployeeListEmployeeAccountIdsResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMoziFusionPageSearchEmployeeResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMoziOrganizationGetOrganizationByCodeResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMoziOrganizationListOrganizationEmployeeCountResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMoziOrganizationPageOrganizationEmployeePositionsResponse;
import com.alibaba.xxpt.gateway.shared.api.response.OapiMoziOrganizationPageSubOrganizationCodesResponse;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.GetClient;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentGetClient;
import com.alibaba.xxpt.gateway.shared.client.http.IntelligentPostClient;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;
import com.alibaba.xxpt.gateway.shared.client.http.api.OapiSpResultContent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.dd.domain.EmployeeResponse;
import tools.dd.domain.WorkNotice;
import tools.dd.ex.ServiceException;

/**
 * DingConnect
 *
 * @author: hyr
 * @date: 2022-03-30
 **/
public class DingBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DingBuilder.class);


    public static String getUserInfo(String authCode) {
        DingConnect dingConnect = new DingConnect();
        return dingConnect.getUserInfo(authCode);
    }

    public static String sendWorkNotice(WorkNotice workNotice) {
        DingConnect dingConnect = new DingConnect();
        return dingConnect.sendWorkNotice(workNotice);
    }

    /**
     * 获取组织信息
     *
     * @return
     */
    public static JSONObject getDeptDetails() {
        DingConnect dingConnect = new DingConnect();
        return dingConnect.getDeptDetails();
    }

    /**
     * 获取所有员工对象
     *
     * @return 员工对象
     */
    public static Map<String, List<EmployeeResponse>> getAllEmployeeResponse() {
        DingConnect dingConnect = new DingConnect();
        Set<String> deptCodes = dingConnect.getDeptVisibleScopes();
        if (CollectionUtils.isEmpty(deptCodes)) {
            logger.warn("钉钉获取组织编码为空");
            return new HashMap<>();
        }

        return getEmployeeResponseByDeptCode(deptCodes);
    }

    /**
     * 根据组织编码获取员工信息
     *
     * @param deptCodes 组织编码，多个用逗号隔开
     * @return EmployeeResponses
     */
    public static Map<String, List<EmployeeResponse>> getEmployeeResponseByDeptCode(
        Set<String> deptCodes) {
        logger.info("钉钉组织编码：{}", deptCodes);
        Map<String, List<EmployeeResponse>> map = new HashMap<>();

        for (String deptCode : deptCodes) {
            List<EmployeeResponse> deptEmployeeResponseList = getDeptEmployeeResponseList(
                deptCode);
            map.put(deptCode, deptEmployeeResponseList);
        }
        return map;
    }

    public static List<EmployeeResponse> getDeptEmployeeResponseList(String deptCode) {
        List<EmployeeResponse> employees = new ArrayList<>();
        DingConnect dingConnect = new DingConnect();
        Map<String, String> employeeNames = dingConnect.getEmployeeNames(deptCode);
        if (employeeNames.size() == 0) {
            return employees;
        }
        logger.info("钉钉组织下人员姓名, deptCode: {}, count: {}", deptCode, employeeNames.size());

        Map<String, String> tmp = new HashMap<>();
        AtomicLong index = new AtomicLong();
        employeeNames.forEach((code, name) -> {
            index.getAndIncrement();
            tmp.put(code, name);
            if (tmp.size() >= 99 || index.get() == employeeNames.size()) {
                List<EmployeeResponse> employeeResponses = dingConnect
                    .listEmployeeAccountIds(tmp);
                employees.addAll(employeeResponses);
                tmp.clear();
            }
        });
        return employees;
    }

    /**
     * 查询组织下人数
     *
     * @return
     */
    public static JSONArray getDeptEmployeeCount(String[] depts) {
        DingConnect dingConnect = new DingConnect();
        return dingConnect.getDeptEmployeeCount(new HashSet<>(Arrays.asList(depts)));
    }

    /**
     * 根据组织Code查询详情
     *
     * @param dept
     * @return
     */
    public static JSONObject getDeptDetails(String dept) {
        DingConnect dingConnect = new DingConnect();
        return dingConnect.getDeptDetails(dept);
    }

    /**
     * 分页获取下⼀级组织 Code 列表
     *
     * @param dept     组织编码
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static JSONArray getSubDepts(String dept, Integer pageNo, Integer pageSize) {
        DingConnect dingConnect = new DingConnect();
        return dingConnect.getSubDepts(dept, pageNo, pageSize);
    }

    static class DingConnect extends BaseConnect {

        private static final String TOKEN_KEY = "_access_token_code_key_";

        /**
         * 获取token
         *
         * @return {"success":true,"content":{"data":{"expiresIn":7200,"accessToken":"app_9412a87440e84173a4230dd3fecbb311"},"success":true,"requestId":"2760828516486227608618421e7d17","responseMessage":"OK","responseCode":"0","bizErrorCode":"0"},"bizErrorCode":"0"}
         */
        private TokenResponse accessToken() {
            try {
                String apiResult;
                String api = "/gettoken.json";
                GetClient getClient = getClient().newGetClient(api);
                getClient.addParameter("appkey", dingTalkConfig.getAppKey());
                getClient.addParameter("appsecret", dingTalkConfig.getAppSecret());
                apiResult = getClient.get();
                JSONObject jsonObject = JSONObject.parseObject(apiResult);
                boolean success = jsonObject.getBooleanValue("success");
                if (success) {
                    JSONObject content = jsonObject.getJSONObject("content");
                    String responseCode = content.getString("responseCode");
                    String responseMessage = content.getString("responseMessage");
                    if ("0".equals(responseCode)) {
                        JSONObject data = content.getJSONObject("data");
                        return new TokenResponse(data.getString("accessToken"),
                            data.getInteger("expiresIn"));
                    } else {
                        logger.warn("获取token失败, responseCode : {}, responseMessage : {}",
                            responseCode,
                            responseMessage);
                        throw new ServiceException(String.format("获取token失败, %s", responseMessage));
                    }
                } else {
                    logger.warn("获取token失败, {}", apiResult);
                    throw new ServiceException(String.format("获取token失败, %s", apiResult));
                }
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }

        private static class TokenResponse {

            private String token;
            private Integer timeOut;

            public TokenResponse(String token, Integer timeOut) {
                this.token = token;
                this.timeOut = timeOut;
            }

            public String getToken() {
                return token;
            }

            public Integer getTimeOut() {
                return timeOut;
            }
        }

        /**
         * 获取token并存入redis
         *
         * @return token
         */
        private String getToken() {
            TokenResponse tokenResponse = accessToken();
            return tokenResponse.getToken();
        }

        /**
         * 获取用户详情
         *
         * @param authCode 临时授权码
         * @return 结果
         */
        private String getUserInfo(String authCode) {
            return getUserInfo(getToken(), authCode);
        }

        private String getUserInfo(String accessToken, String authCode) {
            try {
                String api = "/rpc/oauth2/dingtalk_app_user.json";
                PostClient postClient = getClient().newPostClient(api);
                postClient.addParameter("access_token", accessToken);
                postClient.addParameter("auth_code", authCode);
                logger.info("user info accessToken : {}, authCode : {}", accessToken, authCode);
                String post = postClient.post();

                JSONObject postJson = JSONObject.parseObject(post);
                if (postJson.getBooleanValue("success")) {
                    JSONObject content = postJson.getJSONObject("content");
                    if ("0".equals(content.getString("responseCode"))) {
                        logger.info("success get user info : {}", content);
                        return content.getString("data");
                    }
                    logger.warn("获取钉钉用户详情失败, {}", content);
                }
            } catch (Exception e) {
                logger.error("获取钉钉用户详情失败", e);
                throw new ServiceException(String.format("获取钉钉用户详情失败, %s", e));
            }
            throw new ServiceException("获取钉钉用户详情失败");
        }

        /**
         * 发送工作消息
         *
         * @param workNotice 工作消息对象
         * @return { "success": true, "content":
         * "{\"success\":true,\"content\":{\"msgId\":\"aeb07a615a5e4294a55d65925f84bb3a\"}}" }
         */
        private String sendWorkNotice(WorkNotice workNotice) {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/message/workNotification");
            OapiMessageWorkNotificationRequest oapiMessageWorkNotificationRequest = new OapiMessageWorkNotificationRequest();
            //接收者的部门id列表
            oapiMessageWorkNotificationRequest
                .setOrganizationCodes(workNotice.getOrganizationCodes());
            //接收人用户ID
            oapiMessageWorkNotificationRequest.setReceiverIds(workNotice.getReceiverIds());
            //租户ID
            oapiMessageWorkNotificationRequest.setTenantId(dingTalkConfig.getTenantId());
            //业务消息id
            oapiMessageWorkNotificationRequest.setBizMsgId(workNotice.getBizMsgId());
            //消息对象
            oapiMessageWorkNotificationRequest.setMsg(workNotice.getMsg().getContent());
            //获取结果
            OapiMessageWorkNotificationResponse apiResult = intelligentGetClient
                .get(oapiMessageWorkNotificationRequest);
            if (apiResult.getSuccess()) {
                String con = apiResult.getContent();
                JSONObject jsonObject = JSONObject.parseObject(con);
                return jsonObject.getString("msgId");
            } else {
                throw new ServiceException("发送工作消息失败");
            }
        }

        /**
         * 获取组织编码（获取通讯录权限范围）
         *
         * @return 结果
         */
        private Set<String> getDeptVisibleScopes() {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/auth/scopesV2");
            OapiAuthScopesV2Request oapiAuthScopesV2Request = new OapiAuthScopesV2Request();
            //租户ID
            oapiAuthScopesV2Request.setTenantId(Long.valueOf(dingTalkConfig.getTenantId()));
            //获取结果
            OapiAuthScopesV2Response apiResult = intelligentGetClient.get(oapiAuthScopesV2Request);

            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                JSONObject content = JSONObject.parseObject(apiResult.getContent());
                JSONArray deptVisibleScopes = content.getJSONArray("deptVisibleScopes");
                if (deptVisibleScopes != null && deptVisibleScopes.size() > 0) {
                    return new HashSet<>(deptVisibleScopes.toJavaList(String.class));
                }
            } else {
                logger.warn("获取组织编码失败，errorMsg: {}", apiResult.getMessage());
            }
            return new HashSet<>(0);
        }

        private JSONObject getDeptDetails() {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/auth/scopesV2");
            OapiAuthScopesV2Request oapiAuthScopesV2Request = new OapiAuthScopesV2Request();
            //租户ID
            oapiAuthScopesV2Request.setTenantId(Long.valueOf(dingTalkConfig.getTenantId()));
            //获取结果
            OapiAuthScopesV2Response apiResult = intelligentGetClient.get(oapiAuthScopesV2Request);

            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                return JSONObject.parseObject(apiResult.getContent());
            } else {
                logger.warn("获取组织编码失败，errorMsg: {}", apiResult.getMessage());
            }
            return new JSONObject();
        }

        /**
         * 根据组织编码获取组织下所有员工
         *
         * @param deptCode 组织编码
         * @return 结果
         */
        public Map<String, String> getEmployeeNames(String deptCode) {
            Map<String, String> names = new HashMap<>();
            int pageNum = 1;
            int pageSize = 100;

            OapiSpResultContent content = getEmployeeContent(deptCode, 1, pageSize);
            addName(names, content);
            long totalCount = content.getTotalSize();
            logger.info("组织：{}， 总人数：{}", deptCode, totalCount);

            while (totalCount > pageNum * pageSize) {
                pageNum++;
                logger.info("pageNum: {}, pageSize: {}", pageNum, pageSize);
                content = getEmployeeContent(deptCode, pageNum, pageSize);
                addName(names, content);
            }
            return names;
        }

        private void addName(Map<String, String> names, OapiSpResultContent content) {
            JSONArray array = JSON.parseArray(content.getData());
            array.forEach(a -> {
                JSONObject emp = JSONObject.parseObject(a.toString());
                names.put(emp.getString("employeeCode"), emp.getString("employeeName"));
            });
        }

        /**
         * 批量根据员工Code获取员⼯账号ID
         *
         * @param employeeMap <员工编码, 员工姓名>，最多100个
         * @return 结果 成功返回案例 { "success": true, "content": { "data": [{ "accountId": 132,
         * "accountCode": "ceshirenyuan", "accountNamespace": "local", "employeeCode":
         * "GE_78e5f5015d7a44c5b4b53c20e166aac6" }], "success": true, "requestId":
         * "225283bc-25f5-4d84-a75e-b15dd01e5092", "responseMessage": "OK", "responseCode": "0" } }
         */
        public List<EmployeeResponse> listEmployeeAccountIds(Map<String, String> employeeMap) {
            //executableClient保证单例
            IntelligentPostClient intelligentPostClient = getClient()
                .newIntelligentPostClient("/mozi/employee/listEmployeeAccountIds");
            OapiMoziEmployeeListEmployeeAccountIdsRequest oapiMoziEmployeeListEmployeeAccountIdsRequest = new OapiMoziEmployeeListEmployeeAccountIdsRequest();
            oapiMoziEmployeeListEmployeeAccountIdsRequest
                .setEmployeeCodes(new ArrayList<>(employeeMap.keySet()));
            oapiMoziEmployeeListEmployeeAccountIdsRequest
                .setTenantId(Long.parseLong(dingTalkConfig.getTenantId()));

            //获取结果
            OapiMoziEmployeeListEmployeeAccountIdsResponse apiResult = intelligentPostClient
                .post(oapiMoziEmployeeListEmployeeAccountIdsRequest);
            List<EmployeeResponse> list = new ArrayList<>();
            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                if (apiResult.getContent() == null || StringUtils
                    .isBlank(apiResult.getContent().getData())) {
                    return list;
                }
                JSONArray data = JSONObject.parseArray(apiResult.getContent().getData());
                if (data == null) {
                    logger.warn("批量根据员工Code获取员⼯账号ID失败，errorCode:{}, errorMsg:{}",
                        apiResult.getContent().getResponseCode(),
                        apiResult.getContent().getResponseMessage());
                    return list;
                }
                for (int i = 0; i < data.size(); i++) {
                    JSONObject item = data.getJSONObject(i);
                    if (item == null) {
                        continue;
                    }
                    Long accountId = item.getLong("accountId");
                    String empCode = item.getString("employeeCode");
                    String account = item.getString("accountCode");
                    String nameSpace = item.getString("accountNamespace");
                    if (accountId == null || StringUtils.isBlank(empCode) || StringUtils
                        .isBlank(account)) {
                        logger.warn("批量根据员工Code获取员⼯账号ID emp invalidate, item : {}",
                            item.toJSONString());
                    }
                    list.add(
                        new EmployeeResponse(accountId, employeeMap.get(empCode), account, empCode,
                            nameSpace));
                }
            }
            return list;
        }

        /**
         * 根据组织编码获取组织下员工，分页1，20
         *
         * @param deptCode 组织编码
         * @return 结果
         */
        public OapiSpResultContent getEmployeeContent(String deptCode, Integer pageNum,
            Integer pageSize) {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/mozi/organization/pageOrganizationEmployeePositions");
            OapiMoziOrganizationPageOrganizationEmployeePositionsRequest oapiMoziOrganizationPageOrganizationEmployeePositionsRequest = new OapiMoziOrganizationPageOrganizationEmployeePositionsRequest();
            //是否请求总数，默认是false
            oapiMoziOrganizationPageOrganizationEmployeePositionsRequest.setReturnTotalSize(true);
            //分页大小，默认是20，最大100
            oapiMoziOrganizationPageOrganizationEmployeePositionsRequest.setPageSize(pageSize);
            //员工状态，A为有效，F为无效，默认是所有
            oapiMoziOrganizationPageOrganizationEmployeePositionsRequest.setEmployeeStatus("A");
            //组织code
            oapiMoziOrganizationPageOrganizationEmployeePositionsRequest
                .setOrganizationCode(deptCode);
            //请求起始页，默认是1
            oapiMoziOrganizationPageOrganizationEmployeePositionsRequest.setPageNo(pageNum);
            //租户id
            oapiMoziOrganizationPageOrganizationEmployeePositionsRequest
                .setTenantId(Long.valueOf(dingTalkConfig.getTenantId()));
            //获取结果
            OapiMoziOrganizationPageOrganizationEmployeePositionsResponse apiResult = intelligentGetClient
                .get(oapiMoziOrganizationPageOrganizationEmployeePositionsRequest);

            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                if (apiResult.getContent().getSuccess() != null && apiResult.getContent()
                    .getSuccess()) {
                    return apiResult.getContent();
                } else {
                    logger.warn("根据组织编码获取组织下员工失败，responseCode: {}, responseMsg:{}",
                        apiResult.getContent().getResponseCode(),
                        apiResult.getContent().getResponseMessage());
                }
            } else {
                logger.warn("根据组织编码获取组织下员工失败，code: {}, msg:{}", apiResult.getCode(),
                    apiResult.getMessage());
            }
            throw new ServiceException("根据组织编码获取组织下员工失败");
        }


        /**
         * 根据姓名查询人员信息
         *
         * @param deptCode 组织编码
         * @param name     姓名
         * @return 人员信息
         */
        private EmployeeResponse getEmployeeResponse(String deptCode, String name) {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/mozi/fusion/pageSearchEmployee");
            OapiMoziFusionPageSearchEmployeeRequest oapiMoziFusionPageSearchEmployeeRequest = new OapiMoziFusionPageSearchEmployeeRequest();
            //是否返回查询结果总数  默认不需要
            oapiMoziFusionPageSearchEmployeeRequest.setReturnTotalSize(true);
            //组织code,在当前组织下查询 优先级高于cascadeOrganizationCode 两个参数至少有一个
            oapiMoziFusionPageSearchEmployeeRequest.setInOrganizationCode(deptCode);
            //每页条数, 默认20, 最大只能100
            oapiMoziFusionPageSearchEmployeeRequest.setPageSize(1);
            //当前页码, 开始页码为1, 小于1认为为1
            oapiMoziFusionPageSearchEmployeeRequest.setPageNo(1);
            //租户id
            oapiMoziFusionPageSearchEmployeeRequest
                .setTenantId(Long.parseLong(dingTalkConfig.getTenantId()));
            //组织code,在组织级联下级中查询
//            oapiMoziFusionPageSearchEmployeeRequest.setCascadeOrganizationCode("字符串");
            //A/F （在职/离职）默认返回所有
//            oapiMoziFusionPageSearchEmployeeRequest.setStatus("A");
            //人员姓名关键字
            oapiMoziFusionPageSearchEmployeeRequest.setNameKeywords(name);
            //获取结果
            OapiMoziFusionPageSearchEmployeeResponse apiResult = intelligentGetClient
                .get(oapiMoziFusionPageSearchEmployeeRequest);
            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                if (apiResult.getContent().getSuccess() != null && apiResult.getContent()
                    .getSuccess()) {
                    if (StringUtils.isBlank(apiResult.getContent().getData())) {
                        return null;
                    }
                    JSONArray array = JSON.parseArray(apiResult.getContent().getData());
                    if (array.size() == 0) {
                        logger.warn("根据姓名查询未找到人员信息，组织编码：{}, 姓名: {}", deptCode, name);
                    } else {
                        return JSONObject.parseObject(array.getString(0), EmployeeResponse.class);
                    }
                    apiResult.getContent().getData();
                } else {
                    logger.warn("根据姓名查询人员信息失败，responseCode: {}, responseMsg:{}",
                        apiResult.getContent().getResponseCode(),
                        apiResult.getContent().getResponseMessage());
                }
            } else {
                logger.warn("根据姓名查询人员信息失败，code: {}, msg:{}", apiResult.getCode(),
                    apiResult.getMessage());
            }
            return null;
        }

        /**
         * 查询组织下人数
         *
         * @return
         */
        private JSONArray getDeptEmployeeCount(Set<String> depts) {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/mozi/organization/listOrganizationEmployeeCount");
            OapiMoziOrganizationListOrganizationEmployeeCountRequest oapiMoziOrganizationListOrganizationEmployeeCountRequest = new OapiMoziOrganizationListOrganizationEmployeeCountRequest();
            //组织 Code 列表 最多100
            oapiMoziOrganizationListOrganizationEmployeeCountRequest
                .setOrganizationCodes(new ArrayList<>(depts));
            //是否级联查询 true/false  默认 及联查询 true
            oapiMoziOrganizationListOrganizationEmployeeCountRequest.setNeedCascadingQuery(true);
            //租户Id
            oapiMoziOrganizationListOrganizationEmployeeCountRequest
                .setTenantId(Long.parseLong(dingTalkConfig.getTenantId()));
            //员工状态，A 在职； F 离职；  默认是查询所有数据
//            oapiMoziOrganizationListOrganizationEmployeeCountRequest.setEmployeeStatus("字符串");
            //获取结果
            OapiMoziOrganizationListOrganizationEmployeeCountResponse apiResult = intelligentGetClient
                .get(oapiMoziOrganizationListOrganizationEmployeeCountRequest);
            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                if (apiResult.getContent().getData() == null) {
                    return new JSONArray();
                }
                return JSONArray.parseArray(apiResult.getContent().getData());
            } else {
                throw new ServiceException(
                    String.format("获取组织员工数失败， depts:%s, msg: %s", depts, apiResult.getMessage()));
            }
        }

        /**
         * 根据组织Code查询详情
         *
         * @param dept
         * @return
         */
        private JSONObject getDeptDetails(String dept) {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/mozi/organization/getOrganizationByCode");
            OapiMoziOrganizationGetOrganizationByCodeRequest oapiMoziOrganizationGetOrganizationByCodeRequest = new OapiMoziOrganizationGetOrganizationByCodeRequest();
            oapiMoziOrganizationGetOrganizationByCodeRequest.setOrganizationCode(dept);
            oapiMoziOrganizationGetOrganizationByCodeRequest
                .setTenantId(Long.parseLong(dingTalkConfig.getTenantId()));
            //获取结果
            OapiMoziOrganizationGetOrganizationByCodeResponse apiResult = intelligentGetClient
                .get(oapiMoziOrganizationGetOrganizationByCodeRequest);
            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                if (apiResult.getContent().getData() == null) {
                    return new JSONObject();
                }
                return JSONObject.parseObject(apiResult.getContent().getData());
            } else {
                throw new ServiceException(
                    String.format("获取组织详情失败， dept:%s, msg: %s", dept, apiResult.getMessage()));
            }
        }

        /**
         * 分页获取下⼀级组织 Code 列表
         *
         * @param dept     组织编码
         * @param pageNo
         * @param pageSize
         * @return
         */
        private JSONArray getSubDepts(String dept, Integer pageNo, Integer pageSize) {
            //executableClient保证单例
            IntelligentGetClient intelligentGetClient = getClient()
                .newIntelligentGetClient("/mozi/organization/pageSubOrganizationCodes");
            OapiMoziOrganizationPageSubOrganizationCodesRequest oapiMoziOrganizationPageSubOrganizationCodesRequest = new OapiMoziOrganizationPageSubOrganizationCodesRequest();
            oapiMoziOrganizationPageSubOrganizationCodesRequest.setReturnTotalSize(true);
            oapiMoziOrganizationPageSubOrganizationCodesRequest.setPageSize(pageSize);
            oapiMoziOrganizationPageSubOrganizationCodesRequest.setOrganizationCode(dept);
            oapiMoziOrganizationPageSubOrganizationCodesRequest.setPageNo(pageNo);
            // 只查询有效部门
            oapiMoziOrganizationPageSubOrganizationCodesRequest.setStatus("A");
            oapiMoziOrganizationPageSubOrganizationCodesRequest
                .setTenantId(Long.parseLong(dingTalkConfig.getTenantId()));
            //获取结果
            OapiMoziOrganizationPageSubOrganizationCodesResponse apiResult = intelligentGetClient
                .get(oapiMoziOrganizationPageSubOrganizationCodesRequest);
            JSONArray result = new JSONArray();
            if (apiResult.getSuccess() != null && apiResult.getSuccess()) {
                if (apiResult.getContent().getData() == null) {
                    return result;
                }
                JSONArray array = JSONArray.parseArray(apiResult.getContent().getData());
                array.stream().forEach(a -> {
                    result.add(getDeptDetails(String.valueOf(a)));
                });
                return result;
            } else {
                throw new ServiceException(
                    String.format("分页获取下⼀级组织 Code 列表失败， dept:%s, msg: %s", dept,
                        apiResult.getMessage()));
            }
        }

        /************************************************************************钉钉二维码免登陆处理************************************************************************/


        public ExecutableClient getQrClient() {
            ExecutableClient executableClient = ExecutableClient.getInstance();
//            executableClient.setAccessKey(dingQrCodeConfig.getAppKey());
//            executableClient.setSecretKey(dingQrCodeConfig.getAppSecret());
//            executableClient.setDomainName(dingTalkConfig.getDomainName());
//            executableClient.setProtocal("https");
//            executableClient.init();
            return executableClient;
        }


        private String getQrCodeUserInfo(String accessToken, String authCode) {
            JSONObject content = null;
            try {
                String api = "/rpc/oauth2/getuserinfo_bycode.json";
                PostClient postClient = getQrClient().newPostClient(api);
                postClient.addParameter("access_token", accessToken);
                postClient.addParameter("code", authCode);
                logger.info("user info accessToken : {}, code : {}", accessToken, authCode);
                String post = postClient.post();

                JSONObject postJson = JSONObject.parseObject(post);
                if (postJson.getBooleanValue("success")) {
                    content = postJson.getJSONObject("content");
                    if ("0".equals(content.getString("responseCode"))) {
                        logger.info("success get user info : {}", content);
                        return content.getString("data");
                    }
                    logger.warn("获取钉钉用户详情失败, {}", content);
                }
            } catch (Exception e) {
                logger.error("获取钉钉用户详情失败", e);
                throw new ServiceException(String.format("获取钉钉用户详情失败, %s", e));
            }
            throw new ServiceException("获取钉钉用户详情失败------>" + content);
        }

    }
    /************************************************************************钉钉二维码免登陆处理************************************************************************/


}
