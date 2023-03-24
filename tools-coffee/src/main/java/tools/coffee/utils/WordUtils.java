package tools.coffee.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;
import cn.hutool.poi.word.WordUtil;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import tools.coffee.domain.ExamItem;
import tools.coffee.domain.SelectOption;

/**
 * WordUtils
 *
 * @author: hyr
 * @date: 2022-08-10
 **/
public class WordUtils {

    public static void main(String[] args) {
        extracted();
    }

    public static void coffee() {

    }


    private static Map<Integer, String> letterMap;

    static {
        letterMap = new HashMap<>();
        letterMap.put(0, "A");
        letterMap.put(1, "B");
        letterMap.put(2, "C");
        letterMap.put(3, "D");
        letterMap.put(4, "E");
    }

    public static void generate(String fileName, String title, List<ExamItem> selects, List<ExamItem> judges) {
        /*
         * 行间距没有找到怎么设置的，用的换行？？？？
         * */
        Word07Writer writer = WordUtil.getWriter();

        // 添加段落（标题）居中
        writer.addText(ParagraphAlignment.CENTER, new Font("方正小标宋简体", Font.PLAIN, 20), title);
        writer.addText(ParagraphAlignment.RIGHT, new Font("方正小标宋简体", Font.PLAIN, 12), "模拟");
        writer.addText(ParagraphAlignment.CENTER, new Font("方正小标宋简体", Font.PLAIN, 10), String.format("选择题：%s道，判断题：%s道", selects.size(), judges.size()));
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 15),
            String.format("一、单选题（共%s题，每题1.00分）", selects.size()));
        writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        ExamItem item;
        for (int i = 0; i < selects.size(); i++) { //单选
            item = selects.get(i);
            writer.addText(new Font("宋体", Font.PLAIN, 12),
                i + 1 + ". " + getContent(item.getContent()));
            SelectOption selectOption;
            for (int j = 0; j < item.getOption().size(); j++) {
                selectOption = item.getOption().get(j);
                String selectOptionId = selectOption.getId();
                if (item.getCorrectAnswer().stream().anyMatch(a -> a.indexOf(selectOptionId) > 0)) {
                    writer.addText(new Font("宋体", Font.BOLD, 12),
                        "    " + letterMap.get(j) + ". " + selectOption.getContent()
                            .replaceAll("<p>", "").replaceAll("</p>", ""));
                } else {
                    writer.addText(new Font("宋体", Font.PLAIN, 12),
                        "    " + letterMap.get(j) + ". " + getContent(selectOption.getContent()));
                }
            }
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        writer.addText(new Font("宋体", Font.PLAIN, 15),
            String.format("二、判断题（共%s题，每题1.00分）", judges.size()));
        writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        for (int i = 0; i < judges.size(); i++) {
            item = judges.get(i);
            String answer = "";
            if (item.getCorrectAnswer().get(0).indexOf("0") > 0) {
                answer = "错误";
            } else if (item.getCorrectAnswer().get(0).indexOf("1") > 0) {
                answer = "正确";
            }
            writer.addText(new Font("宋体", Font.PLAIN, 12),
                i + 1 + ". " + getContent(item.getContent())
                    + "（" + answer + "）");

            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        // 写出到文件
        writer.flush(FileUtil.file(String.format("D:/%s.docx", fileName)));
        // 关闭
        writer.close();
    }

    public static void generateNoAnswer(String fileName, String title, List<ExamItem> selects,
        List<ExamItem> judges) {
        /*
         * 行间距没有找到怎么设置的，用的换行？？？？
         * */
        Word07Writer writer = WordUtil.getWriter();

        // 添加段落（标题）居中
        writer.addText(ParagraphAlignment.CENTER, new Font("方正小标宋简体", Font.PLAIN, 20), title);
        writer.addText(ParagraphAlignment.RIGHT, new Font("方正小标宋简体", Font.PLAIN, 12), "模拟");
        writer.addText(ParagraphAlignment.CENTER, new Font("方正小标宋简体", Font.PLAIN, 10), String.format("选择题：%s道，判断题：%s道", selects.size(), judges.size()));
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 15),
            String.format("一、单选题（共%s题，每题1.00分）", selects.size()));
        writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        ExamItem item;
        for (int i = 0; i < selects.size(); i++) { //单选
            item = selects.get(i);
            writer.addText(new Font("宋体", Font.PLAIN, 12),
                i + 1 + ". " + getContent(item.getContent()));
            SelectOption selectOption;
            for (int j = 0; j < item.getOption().size(); j++) {
                selectOption = item.getOption().get(j);
                writer.addText(new Font("宋体", Font.PLAIN, 12),
                    "    " + letterMap.get(j) + ". " + getContent(selectOption.getContent()));

            }
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        writer.addText(new Font("宋体", Font.PLAIN, 15),
            String.format("二、判断题（共%s题，每题1.00分）", judges.size()));
        writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        for (int i = 0; i < judges.size(); i++) {
            item = judges.get(i);
            String answer = "";
            if (item.getCorrectAnswer().get(0).indexOf("0") > 0) {
                answer = "错误";
            } else if (item.getCorrectAnswer().get(0).indexOf("1") > 0) {
                answer = "正确";
            }
            writer.addText(new Font("宋体", Font.PLAIN, 12),
                i + 1 + ". " + getContent(item.getContent())
                    + "（" + "    " + "）");

            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        // 写出到文件
        writer.flush(FileUtil.file(String.format("D:/%s.docx", fileName)));
        // 关闭
        writer.close();
    }

    private static String getContent(String content) {
        return content.replaceAll("<p>", "").replaceAll("</p>", "")
            .replaceAll("&nbsp;", "")
            .replaceAll("&quot;", "\"")
            .replaceAll("<br/>", "\n").replaceAll("<br>", "\n");
    }

    public static void extracted() {
        /*
         * 行间距没有找到怎么设置的，用的换行？？？？
         * */
        Word07Writer writer = WordUtil.getWriter();

        // 添加段落（标题）居中
        writer.addText(ParagraphAlignment.CENTER, new Font("方正小标宋简体", Font.PLAIN, 15), "18旅游政策法规");
        writer.addText(ParagraphAlignment.RIGHT, new Font("方正小标宋简体", Font.PLAIN, 10), "模拟");
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 10), "一、单选题（共50题，每题1.00分）");
        for (int i = 0; i < 50; i++) { //单选
            writer.addText(new Font("宋体", Font.PLAIN, 10), i + 1 + ".中国特色社会主义法律体系已经形成是在（ ）上宣布的。");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "A:十一届全国人大三次会议");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "B:十一届全国人大四次会议");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "C:十二届全国人大三次会议");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "D:十二届全国人大四次会议");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        writer.addText(new Font("宋体", Font.PLAIN, 15), "二、多选题（共30题，每题1.00分）");

        for (int i = 0; i < 30; i++) { //多选
            writer.addText(new Font("宋体", Font.PLAIN, 12), i + 1 + ".加强重点领域立法主要包括（ ）");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "A:完善宪法监督制度");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "B:推进社会主义民主政治法治化");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "C:建立健全文化法律制度");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "D:加强社会建设领域法制制度建设");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 10), "E:用严格的法律制度保护生态环境");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        writer.addText(new Font("宋体", Font.PLAIN, 15), "三、判断题（共20题，每题1.00分）");
        for (int i = 0; i < 20; i++) { //判断
            writer.addText(new Font("宋体", Font.PLAIN, 12),
                i + 1 + ".《“十三五”旅游业发展规划》是由国家旅游局独立编制和发布的“十三五”时期旅游业发展的行动纲领和基本遵循。（ ）");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        // 写出到文件
        writer.flush(FileUtil.file("D:/wordWrite.docx"));
        // 关闭
        writer.close();
    }
}
