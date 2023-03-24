package tools.coffee.jczh;

import java.util.ArrayList;
import java.util.List;
import tools.coffee.jczh.domain.WarnEvent;

/**
 * MockTest
 *
 * @author: hyr
 * @date: 2022-11-24
 **/
public class GuanWangMock {

    public static void main(String[] args) {
        for (WarnEvent event : getWarns()) {
//            RemoteUtils.mockProcess(event);
        }
    }

    public static List<WarnEvent> getWarns() {
        List<WarnEvent> list = new ArrayList<>();
        list.add(getWarnEvent1());
        list.add(getWarnEvent2());
        list.add(getWarnEvent3());
        list.add(getWarnEvent4());
        list.add(getWarnEvent5());
        list.add(getWarnEvent6());
        list.add(getWarnEvent7());
        list.add(getWarnEvent8());
        list.add(getWarnEvent9());
        list.add(getWarnEvent10());
        list.add(getWarnEvent11());
        list.add(getWarnEvent12());
        return list;
    }

    private static WarnEvent getWarnEvent1() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\1-1.jpg");
        paths.add("D:\\picture\\mock\\1-2.jpg");
        WarnEvent warnEvent = new WarnEvent(1, "9b6d8a70-2b28-46fd-8e93-653a118e4d8e", "74;76", "赵卿;冯军;",
            "纬七东路南侧-忠浩摄影北侧管网【2022-10-04 08:00:00~2022-10-04 13:10:00】期间，液位上升0.0m，电导率上升72.0us/cm，疑似污水流入。"
            ,"在建","有问题","忠浩摄影器材有限公司的雨水出口流经的窨井发现有水体流动现象，且上游的窨井水面存在油污，现场对雨水出口流经的窨井进行采样分析。", "赵卿", paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为上游窨井污染且封堵措施不合格，在降雨条件下，因为污染水体通过缝隙渗透到监测点，引起数据平台报警。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent2() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\2-1.jpg");
        paths.add("D:\\picture\\mock\\2-2.jpg");
        String content = "纬五东路北侧-康隆达南侧管网【2022-10-03 02:20:00~2022-10-03 08:10:00】期间，液位上升0.01m，电导率上升224.06us/cm，疑似污水流入。";
        String jcyj = "现场发现监测点位有水体流动现象，往金昊方向也有水体流动，再往上无流动。现场对康隆达门口管网取水样进行分析。";
        String operator = "冯军";
        WarnEvent warnEvent = new WarnEvent(2, "0b58b5b8-a017-4580-95a7-9b2e86cb4653", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为上游窨井污染且封堵措施不合格，在降雨条件下，因为污染水体通过缝隙渗透到监测点，引起数据平台报警。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent3() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\3-1.jpg");
        paths.add("D:\\picture\\mock\\3-2.jpg");
        String content = "纬七东路南侧-鑫盛瑞东北侧管网【2022-10-13 08:10:00~2022-10-13 13:30:00】期间，液位上升0.0m，电导率上升264.0us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往鑫盛瑞纺织雨水出口和流经的窨井进行确认，发现上游两个窨井有水流动，但第三个窨井水无水流动，污水的来源不明确，待进一步核查。";
        String operator = "赵卿";
        WarnEvent warnEvent = new WarnEvent(3, "6acb4909-e126-4947-8af9-ce06d29393d1", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为上游窨井污染并且有渗漏，渗透到监测点，引起数据平台报警。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent4() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\4-1.jpg");
        paths.add("D:\\picture\\mock\\4-2.jpg");
        String content = "纬九东路北侧-经二河西侧管网【2022-10-12 07:40:00~2022-10-12 08:10:00】期间，液位上升0.0m，电导率上升1021.0us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往华鸿纺织雨水出口和流经的窨井进行确认，发现雨水排口有渗漏现象。";
        String operator = "冯军";
        WarnEvent warnEvent = new WarnEvent(4, "bde2eced-2757-4678-a2a9-23d36ac9bfc0", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为华鸿纺织企业污水渗漏导致。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent5() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\5-1.jpg");
        paths.add("D:\\picture\\mock\\5-2.jpg");
        String content = "纬九东路北侧-经二河西侧管网在2022-10-12 15:10:00时,电导率值为：1888.008,高于上限1300";
        String jcyj = "该点位巡查人员已于今日上午前往华鸿纺织雨水出口和流经的窨井进行确认，发现雨水排口有渗漏现象。该处报警是由于渗漏还未得到有效解决导致。";
        String operator = "赵卿";
        WarnEvent warnEvent = new WarnEvent(5, "28cb6473-b63e-4978-bbc9-ead08c7e9e2a", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为华鸿纺织企业污水渗漏导致。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent6() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\6-1.jpg");
        paths.add("D:\\picture\\mock\\6-2.jpg");
        String content = "纬三东路北侧-经一河东侧管网【2022-10-27 04:40:00~2022-10-27 10:00:00】期间，液位上升0.02m，电导率上升2992.02us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往奥龙电源雨水出口和流经的窨井进行确认，奥龙电源现场有少量流水，上游窨井也有水流动，疑似排污。现场对监测点位及上游窨井进行了采样分析。";
        String operator = "冯军";
        WarnEvent warnEvent = new WarnEvent(6, "217f2983-9d52-45ac-b9a4-d81ab8c64aa3", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，发现上游节点的水质明显劣于下游节点，且该段管网污水的来源理论上只有奥龙电源和路面雨水，污水有较大可能来自上游企业。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent7() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\7-1.jpg");
        paths.add("D:\\picture\\mock\\7-2.jpg");
        String content = "纬五东路北侧-康隆达南侧管网【2022-10-28 08:00:00~2022-10-28 13:00:00】期间，液位上升0.02m，电导率上升64.02us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往康隆达监测点位和上游流经窨井进行确认，发现康隆达监测点有明显水流流动，上游排查窨井，有水流流动，疑似排污。现场对监测点位和最上游段进行了取样分析。";
        String operator = "赵卿";
        WarnEvent warnEvent = new WarnEvent(7, "5826dad4-3098-4701-9b38-9ced753c3db3", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，康隆达监测点、上游窨井有排水，YSZB00041为康隆达企业直排口，大概率为企业排放，需待进一步核查。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent8() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\8-1.jpg");
        paths.add("D:\\picture\\mock\\8-2.jpg");
        String content = "纬七东路南侧-忠浩摄影北侧管网【2022-11-03 08:30:00~2022-11-03 13:50:00期间，液位上升0.02m，电导率上升368.0us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往忠浩摄影雨水出口和流经的窨井进行确认，忠浩摄影监测点无明显水流流动，上游第二个窨井有水流流出，下游无明显水流流动，现场对监测点位和上游窨井进行了采样。";
        String operator = "冯军";
        WarnEvent warnEvent = new WarnEvent(8, "c502ec12-f5bf-43bc-8e88-4b15bc09a42d", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，初步判断异常数据超标是由上游来水导致，需进一步排查污水来源。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent9() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\9-1.jpg");
        paths.add("D:\\picture\\mock\\9-2.jpg");
        String content = "纬三东路北侧-经一河东侧管网【2022-11-09 07:10:00~2022-11-09 10:00:00】期间，液位上升0.01m，电导率上升1040.0us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往奥龙电源雨水出口和流经的窨井进行确认，发现监测点明显水流流动，上游窨井到奥龙门口前一个窨井都有水流流出，奥龙电源大门无明显水流流动。现场对监测点位和上游窨井进行了取样分析。";
        String operator = "赵卿";
        WarnEvent warnEvent = new WarnEvent(9, "c0fed5be-c735-4875-a847-ef5473436cd8", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，初步判定监测点报警由于上游来水污染严重，但由于无法判定是否为企业直接排水，需加强相关管网测量手段。");
        return warnEvent;
    }

    private static WarnEvent getWarnEvent10() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\10-1.jpg");
        paths.add("D:\\picture\\mock\\10-2.jpg");
        paths.add("D:\\picture\\mock\\10-3.jpg");
        String content = "经七东路西侧-中能循环东侧管网【2022-11-11 11:10:00~2022-11-11 11:20:00】期间，液位上升0.01m，电导率上升3450.0us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往中能循环雨水出口和流经的窨井进行确认，发现监测点有明显水流流动，向上排查至中能循环雨水排口，也有水流动，进入企业雨水房查看后，发现厂区雨水闸阀无泄漏，但闸阀后端与市政管网连接处，有漏点，疑似厂内消防用水泄漏。";
        String operator = "冯军";
        WarnEvent warnEvent = new WarnEvent(10, "c005460d-730e-4cfc-867b-6e79d1a408fc", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，发现厂区雨水闸阀后端与市政管网连接处，有漏点，疑似厂内消防用水泄漏，已要求厂区整改，后期将持续跟踪排查。");
        return warnEvent;
    }
    private static WarnEvent getWarnEvent11() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\11-1.jpg");
        paths.add("D:\\picture\\mock\\11-2.jpg");
        String content = "东经三路北段-三丰化工西侧管网【2022-11-17 10:50:00~2022-11-17 13:30:00】期间，液位上升0.04m，电导率上升6136.06us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往曼浦汉克、中欣氟材雨水出口和流经的窨井进行确认，排查发现是由于监测点位上游施工挖断污水管网，导致污水流入监测点位导致异常报警，现场对监测点进行了采样分析。";
        String operator = "赵卿";
        WarnEvent warnEvent = new WarnEvent(11, "1c159f89-ba77-4d58-ac20-5a0fdb54fbfc", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，发现是由于监测点位上游施工挖断污水管网，导致污水流入监测点位导致异常报警。");
        return warnEvent;
    }
    private static WarnEvent getWarnEvent12() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\12-1.jpg");
        paths.add("D:\\picture\\mock\\12-2.jpg");
        String content = "纬五东路北侧-康隆达南侧管网【2022-11-18 07:50:00~2022-11-18 13:30:00】期间，液位上升0.0m，电导率上升1520.02us/cm，疑似污水流入。";
        String jcyj = "巡查人员前往康隆达监测点位和上游流经窨井进行确认，发现康隆达点位东西方向都有流水流入，其中东方向流入较多，西方向流入较少。上游排查（西）发现有渗漏，少量漏水，并已通知企业进行封堵。东方向流水来源暂未确定。";
        String operator = "冯军";
        WarnEvent warnEvent = new WarnEvent(12, "4b21b723-6f22-4d87-90cf-49393d466af6", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，康隆达上游（西）发现有渗漏，并已通知企业进行封堵，东方向流水来源不明确，需配合工具进一步勘察明确。");
        return warnEvent;
    }
    private static WarnEvent getWarnEvent() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\mock\\1-1.png");
        paths.add("D:\\picture\\mock\\1-2.jpg");
        String content = "";
        String jcyj = "";
        String operator = "";
        WarnEvent warnEvent = new WarnEvent(2, "", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, operator, paths);
        return warnEvent;
    }

}
