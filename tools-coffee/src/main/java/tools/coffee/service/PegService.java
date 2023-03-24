package tools.coffee.service;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteResultHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * PegService
 *
 * @author: hyr
 * @date: 2022-07-28
 **/
@Service
@Slf4j
public class PegService {

    @Value("${ffmpeg.binPath}")
    private String ffmpegPath;

    @Value("${ffmpeg.videoPath}")
    private String videoPath;

    @Value("${ffmpeg.gifPath}")
    private String gifPath;

    @Value("${ffmpeg.m3u8Path}")
    private String m3u8Path;

    public void cutVideo(String videoName, String exportName) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 68上的 D:/BesovideoData 挂载到 69上的Y盘 所以数据库上的文件路径要做一下转换
        //视频码率压缩、切割视频、产生预览gif
        String command = String.format(
//            "%sffmpeg -i %s  -vsync 1 -r 4  -vframes 20 -y -f gif  -s 640x360 %s.gif  -vcodec h264 -vbsf h264_mp4toannexb -r 25 -crf 30 -preset veryslow -f hls -hls_list_size 0 -hls_time 5 -hls_wrap 0 %s.m3u8",
            "%sffmpeg -i %s  -vsync 1 -r 4  -vframes 20 -y -f gif  -s 640x360 %s.gif  -vcodec h264 -vbsf h264_mp4toannexb -r 25 -crf 30 -f hls -hls_list_size 0 -hls_time 5 -hls_wrap 0 %s.m3u8",
            ffmpegPath, videoPath + videoName, gifPath + exportName, m3u8Path + exportName);
        log.info("command line : {}", command);
        CommandLine cmdLine = CommandLine.parse(command);
        DefaultExecutor executor = new DefaultExecutor();
        ExecuteResultHandler handler = new ExecuteResultHandler() {
            @Override
            public void onProcessFailed(ExecuteException e) { // 执行失败回调
                countDownLatch.countDown();
                log.error("error!", e);
            }

            @Override
            public void onProcessComplete(int exitValue) { // 执行成功回调
                countDownLatch.countDown();
                log.info("process success!");
            }
        };
        try {
            executor.execute(cmdLine, handler);
            countDownLatch.await(); // 阻塞的位置
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
