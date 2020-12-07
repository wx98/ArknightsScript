import com.arknights.Arknights;
import com.qqmsg.QQMonitor;

public class Statr {
    public static void main(String[] args) {
        new Statr().process();
    }

    public void process() {
        //启动QQ信息监控,检测指令
        QQMonitor.getSingleton()//得到监听器示例
                .setPostHost("127.0.0.1")
                .setPostPort(40000)//监听地址
                .setSocketPort(40001)//收到消息上报端口
                .core()//加载核心
                .run();//启动
    }
}
