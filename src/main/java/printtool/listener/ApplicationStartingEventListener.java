package printtool.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import printtool.dllinter.NativeLoader;
import printtool.panel.ControlPanel;
import printtool.utils.INIFileUtil;

import java.awt.*;
import java.io.IOException;

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
        try {
            INIFileUtil.readIniFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
