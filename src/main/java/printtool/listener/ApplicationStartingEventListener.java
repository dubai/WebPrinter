package printtool.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import printtool.panel.ControlPanel;

import java.awt.*;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        // 在程序开始启动时，加载面板
        ControlPanel controlPanel = new ControlPanel();
        try {
            controlPanel.drawTray();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
