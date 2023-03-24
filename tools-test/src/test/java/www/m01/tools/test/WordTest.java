package www.m01.tools.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.word.Word07Writer;
import cn.hutool.poi.word.WordUtil;
import java.awt.Font;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

/**
 * WordTest
 *
 * @author: hyr
 * @date: 2022-08-02
 **/
@Slf4j
public class WordTest {

    public static void main(String[] args) {
        extracted();
    }

    private static void coffee() {

    }

    private static void generate(String title) {
        /*
         * 行间距没有找到怎么设置的，用的换行？？？？
         * */
        Word07Writer writer = WordUtil.getWriter();

        // 添加段落（标题）居中
        writer.addText(ParagraphAlignment.CENTER,new Font("方正小标宋简体", Font.PLAIN, 15), title);
        writer.addText(ParagraphAlignment.RIGHT,new Font("方正小标宋简体", Font.PLAIN, 10), "模拟");
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 10), "一、单选题（共50题，每题1.00分）");
        for (int i=0;i<50;i++){ //单选
            writer.addText(new Font("宋体", Font.PLAIN, 10), i+1+".中国特色社会主义法律体系已经形成是在（ ）上宣布的。");
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

        for (int i=0;i<30;i++){ //多选
            writer.addText(new Font("宋体", Font.PLAIN, 12), i+1+".加强重点领域立法主要包括（ ）");
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
        for (int i=0;i<20;i++){ //判断
            writer.addText(new Font("宋体", Font.PLAIN, 12), i+1+".《“十三五”旅游业发展规划》是由国家旅游局独立编制和发布的“十三五”时期旅游业发展的行动纲领和基本遵循。（ ）");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        // 写出到文件
        writer.flush(FileUtil.file("D:/wordWrite.docx"));
        // 关闭
        writer.close();
    }

    private static void extracted() {
        /*
         * 行间距没有找到怎么设置的，用的换行？？？？
         * */
        Word07Writer writer = WordUtil.getWriter();

        // 添加段落（标题）居中
        writer.addText(ParagraphAlignment.CENTER,new Font("方正小标宋简体", Font.PLAIN, 15), "18旅游政策法规");
        writer.addText(ParagraphAlignment.RIGHT,new Font("方正小标宋简体", Font.PLAIN, 10), "模拟");
        // 添加段落（正文）
        writer.addText(new Font("宋体", Font.PLAIN, 10), "一、单选题（共50题，每题1.00分）");
        for (int i=0;i<50;i++){ //单选
            writer.addText(new Font("宋体", Font.PLAIN, 10), i+1+".中国特色社会主义法律体系已经形成是在（ ）上宣布的。");
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

        for (int i=0;i<30;i++){ //多选
            writer.addText(new Font("宋体", Font.PLAIN, 12), i+1+".加强重点领域立法主要包括（ ）");
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
        for (int i=0;i<20;i++){ //判断
            writer.addText(new Font("宋体", Font.PLAIN, 12), i+1+".《“十三五”旅游业发展规划》是由国家旅游局独立编制和发布的“十三五”时期旅游业发展的行动纲领和基本遵循。（ ）");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
            writer.addText(new Font("宋体", Font.PLAIN, 8), "");
        }
        // 写出到文件
        writer.flush(FileUtil.file("D:/wordWrite.docx"));
        // 关闭
        writer.close();
    }

    static class SelectEntity {

    }

}
