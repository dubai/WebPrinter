package printtool.dllinter;

import com.sun.jna.Native;

public class MTICCInter {

    static {
//        Native.register("MTDLL/ICCInter_ZS_SB");
        // 项目打包时用的路径
        Native.register("..\\webprinter\\jre8\\bin\\mt\\ICCInter_ZS_SB");
    }

    //接触
    public static native int IC_ReadCardInfo(byte[] OutData);

    // 非接触读卡
    public static native int IC_RF_ReadCardInfo(byte[] OutData);

    // 社保卡通标志
    public static native int IC_ReadFlag(byte[] OutData);

    //身份证信息
    public static native int IC_ReadIdCard(byte[] OutData);

}
