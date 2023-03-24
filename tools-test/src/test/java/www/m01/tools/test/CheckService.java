package www.m01.tools.test;

/**
 * CheckService
 *
 * @author: hyr
 * @date: 2022-08-03
 **/
public class CheckService {

    private final ThreadLocal<Integer> threadNum = new ThreadLocal<>();

    private Integer commonNum = null;


    public void setThreadNum(Integer num) {
        threadNum.set(num);
    }

    public Integer getThreadNum() {
        return threadNum.get();
    }

    public void setCommonNum(Integer num) {
        this.commonNum = num;
    }

    public Integer getCommonNum() {
        return commonNum;
    }
}
