package printtool.dllinter;

import com.sun.jna.Native;

public class DKICCInter {

    static {
//        Native.register("DKDLL/ICCInter_ZS_SB");
        Native.register("..\\webprinter\\jre8\\bin\\dk\\ICCInter_ZS_SB");
//        DKSSCardDriver cardDriver = DKSSCardDriver.INSTANCE;
//        Native.register("C:\\Java\\JDK1.8\\jre\\bin\\dk\\ICCInter_ZS_SB.dll");
    }

    //读接触式卡信息
    public static native int IC_ReadCardInfo(byte[] OutData);

    //读非接触式卡信息
    public static native int IC_RF_ReadCardInfo(byte[] OutData);

    //读接触式卡社保卡通标志
    public static native int IC_ReadFlag(byte[] OutData);

    //读身份证信息
    public static native int IC_ReadIdCard(byte[] OutData);
}
