package printtool.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import printtool.panel.ControlPanel;
import printtool.utils.InfoUtil;

import java.awt.*;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

        InfoUtil i = new InfoUtil();
        i.show("提示消息","本地Web打印服务启动成功");
    }
}
