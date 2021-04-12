package printtool.utils;

import org.ini4j.Ini;
import org.ini4j.Profile;

import java.io.File;
import java.util.List;
import java.util.Map;

public class INIFileUtil {

    public static String deviceName;

    public static void readIniFile() throws Exception{
        File iniFile = new File("conf/config.ini");
        Ini ini = new Ini();
        ini.load(iniFile);
        Profile.Section section = ini.get("device");
        deviceName = section.get("name");
    }
}
