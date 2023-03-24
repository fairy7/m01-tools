package tools.coffee.jczh;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import tools.coffee.jczh.domain.WarnEvent;

/**
 * YuanWangMock
 *
 * @author: hyr
 * @date: 2022-11-28
 **/
@Slf4j
public class YuanWangMock {

    /**
     * 8e5db285-a60c-410f-a562-1370aed9ea18
     * d4ca7214-f87a-4c6a-89a2-67eb78d1508c
     * @param args
     */
    public static void main(String[] args) {
        List<WarnEvent> rs = new ArrayList<>();
        rs.addAll(getWarnEvents());
        rs.addAll(GuanWangMock.getWarns());

        Set<String> collect = rs.stream().map(r -> r.getPkid()).collect(Collectors.toSet());
        collect.add("8e5db285-a60c-410f-a562-1370aed9ea18");
        collect.add("d4ca7214-f87a-4c6a-89a2-67eb78d1508c");
        log.info("event size : {}", collect.size());
        StringBuilder sb = new StringBuilder();
        collect.forEach(e -> {
            System.out.println(String.format("('%s'),", e));
            sb.append("\"").append(e).append("\",");
        });
        System.out.println(sb);
    }

    private static void generate() {
        List<WarnEvent> warnEvents = getWarnEvents();
        warnEvents.forEach(e -> {
            System.out.println(sql(e.getPkid(), e.getEnterpriseName(), e.getContent()));
        });
    }

    private static String sql(String pkid, String enterpriseName, String content) {
//        String sql = "update wry_feature_event set enterprise_name = '经七东路西侧-中能循环东侧管网', relate_data = json_set(relate_data,'$.COMPLAINT_SUBJECT','经七东路西侧-中能循环东侧管网【2022-11-07 21:40:00~2022-11-08 02:40:00】期间，液位上升0.02m，电导率上升528.0us/cm，疑似污水流入。'), relate_data = json_set(relate_data,'$.ENTERPRISENAME','经七东路西侧-中能循环东侧管网') where pkid = '8fc22d81-aeb8-4cc0-84f2-ce255b1cc1f4';";
        String sqlFormat = "update wry_feature_event set enterprise_name = '%s', relate_data = json_set(relate_data,'$.COMPLAINT_SUBJECT','%s'), relate_data = json_set(relate_data,'$.ENTERPRISENAME','%s') where pkid = '%s';";
        return String.format(sqlFormat, enterpriseName, content, enterpriseName, pkid);
    }

    private static void process() {
        List<WarnEvent> warnEvents = getWarnEvents();
        StringBuilder sb = new StringBuilder();
        warnEvents.forEach(e -> {
            System.out.println(String.format("%s,%s", e.getPkid(), e.getContent()));
        });
        log.info("{}", sb);
    }

    public static List<WarnEvent> getWarnEvents() {
        List<WarnEvent> warnEvents = new ArrayList<>();
        warnEvents.add(getHeEvent1());
        warnEvents.add(getHeEvent2());
        warnEvents.add(getHeEvent3());
        warnEvents.add(getHeEvent4());
        warnEvents.add(getHeEvent5());
        warnEvents.add(getHeEvent6());
        warnEvents.add(getYuanEvent1());
        return warnEvents;
    }


    private static WarnEvent getHeEvent1() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\hemock\\1-1.jpg");
        paths.add("D:\\picture\\hemock\\1-2.jpg");
        String content = "东北塘河西段-三丰化工北侧河道在2022-11-17 11:20:00时,电导率值为：1112.000,高于上限1000";
        String jcyj = "巡查人员现场发现，监测点位入河排口有大量污水汇入，沿管网向上排查发现是由于施工挖断污水管网，导致污水流入监测点位导致异常报警。";
        WarnEvent warnEvent = new WarnEvent(1, "9321a85a-9aa4-4c02-a2e9-d78a0a725a74", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，发现是由于监测点位上游施工挖断污水管网，导致污水流入监测点位导致异常报警。");
        warnEvent.setEnterpriseName("东北塘河西段-三丰化工北侧河道");
        return warnEvent;
    }

    private static WarnEvent getHeEvent2() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\hemock\\2-1.jpg");
        String content = "东中心河中段-美都海创西南侧河道在2022-11-07 21:44:30时,浊度值为：210.796,高于上限100";
        String jcyj = "巡查人员现场排查发现，是由于浊度传感器掉落沉入水箱底部，导致监测结果偏高。";
        WarnEvent warnEvent = new WarnEvent(2, "bc38a1a4-2c70-4703-9189-ca20cb4596ed", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为传感器沉入水箱底部导致。");
        warnEvent.setEnterpriseName("东中心河中段-美都海创西南侧河道");
        return warnEvent;
    }

    private static WarnEvent getHeEvent3() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\hemock\\3-1.jpg");
        String content = "东中心河中段-美都海创西南侧河道在2022-11-11 10:35:00时,光谱法高锰酸盐指数值为：85.646,高于上限20";
        String jcyj = "巡查人员现场排查发现，是由于电磁阀损坏漏气导致数据异常";
        WarnEvent warnEvent = new WarnEvent(3, "3e2362f5-b4a7-4c5f-8f10-5b9721469eaa", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为设备故障导致");
        warnEvent.setEnterpriseName("东中心河中段-美都海创西南侧河道");
        return warnEvent;
    }

    private static WarnEvent getHeEvent4() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\hemock\\4-1.jpg");
        String content = "东北塘河中段-东海化工北侧河道在2022-11-15 09:09:41时,光谱法氨氮值为：196.644,高于上限15,光谱法总磷值为：39.798,高于上限1,光谱法总氮值为：289.462,高于上限20,光谱法高锰酸盐指数值为：419.887,高于上限20";
        String jcyj = "巡查人员现场排查发现，是由于光谱仪损坏故障导致异常数据";
        WarnEvent warnEvent = new WarnEvent(4, "027f0ac6-f284-4a10-8fc3-7b8a14747151", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为设备故障导致");
        warnEvent.setEnterpriseName("东北塘河中段-东海化工北侧河道");
        return warnEvent;
    }

    private static WarnEvent getHeEvent5() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\hemock\\5-1.jpg");
        String content = "东北塘河西段-久田伞业北侧河道在2022-11-17 11:21:59时,化学需氧量值为：71.96,高于上限60";
        String jcyj = "巡查人员现场排查发现，是由于水箱中藻类杂质导致水质化学需氧量偏高";
        WarnEvent warnEvent = new WarnEvent(5, "de22ee12-ba78-4e3c-8e9d-3900b7d8cdcc", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为水箱杂质导致。");
        warnEvent.setEnterpriseName("东北塘河西段-久田伞业北侧河道");
        return warnEvent;
    }

    private static WarnEvent getHeEvent6() {
        List<String> paths = new ArrayList<>();
        paths.add("D:\\picture\\hemock\\6-1.jpg");
        String content = "东中心河西段-康隆达南侧河道在2022-11-11 11:47:15时,PH值为：5.831,低于下限6";
        String jcyj = "巡查人员现场排查发现，是由于水箱水排空，采水故障，导致传感器数据异常，需重新校准。";
        WarnEvent warnEvent = new WarnEvent(6, "829e087e-51ac-47ae-8a4c-e83b463f9771", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为水箱采水故障，导致传感器数据异常。");
        warnEvent.setEnterpriseName("东中心河西段-康隆达南侧河道");
        return warnEvent;
    }

    private static WarnEvent getYuanEvent1() {
        List<String> paths = new ArrayList<>();
        String content = "永农生物西南侧雨水口在2022-11-11 13:27:50时,电导率值为：6326,高于上限2000";
        String jcyj = "巡查人员现场排查发现，是由于传感器周边杂质堆积，水质恶化导致数据异常";
        WarnEvent warnEvent = new WarnEvent(1, "c5b87736-03ed-4c30-b6d6-840c6ba99c5f", "74;76", "赵卿;冯军;",
            content
            ,"在建","有问题",
            jcyj, paths);
        warnEvent.setOperator(warnEvent.getOrder()/2 == 0 ? "赵卿" : "冯军");
        warnEvent.setConfirm("管理员确认检查反馈结果，经过本次现场核查，是因为传感器周边杂质导致。");
        warnEvent.setEnterpriseName("永农生物西南侧雨水口");
        return warnEvent;
    }

}
