package printtool.panel;

import net.sf.image4j.codec.ico.ICODecoder;
import printtool.editor.PosEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ControlPanel extends JFrame {

    public ControlPanel(){
    }

    public void drawTray() throws AWTException {
        //判断当前系统支不支持系统托盘功能
        if(SystemTray.isSupported()){
            // 系统托盘图标
//            URL imageUrl = this.getClass().getResource("/images/printer.png");
//            ImageIcon icon = new ImageIcon(imageUrl);
            List<BufferedImage> images = null;
            ImageIcon icon = null;
            try {
                InputStream is = this.getClass().getResource("/images/GOVERM.ico").openStream();
                images = ICODecoder.read(is);
                if (images != null){
                    icon = new ImageIcon(images.get(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 创建弹出式菜单
            PopupMenu pop = new PopupMenu();

            MenuItem menuItem = new MenuItem("退出");
            menuItem.addActionListener(new ActionListener() {
                // 单击时退出系统
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            MenuItem menuItem1 = new MenuItem("模板编辑");
            menuItem1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new PosEditor();
                }
            });

            pop.add(menuItem1);
            pop.add(menuItem);
            TrayIcon tray = new TrayIcon(icon.getImage(),"打印调用服务", pop);
            //自动缩放
            tray.setImageAutoSize(true);
            SystemTray systemTray = SystemTray.getSystemTray();
            systemTray.add(tray);
        }
    }
}
