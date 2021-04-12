package printtool.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import printtool.dllinter.DKICCInter;
import printtool.utils.RCFormatUtil;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/DK")
public class DKReadCardController {

    Logger log = LoggerFactory.getLogger(DKReadCardController.class);

    @Autowired
    public DKReadCardController(){
        System.loadLibrary("dk/dcrf32");
        System.loadLibrary("dk/ssse32");
        System.loadLibrary("dk/fct_des");
        System.loadLibrary("dk/GetInfo");
        System.loadLibrary("dk/libghttp");
        System.loadLibrary("dk/mt_32");

        System.loadLibrary("dk/DK_HttpInter_ZJZS_SB");
        System.loadLibrary("dk/DK_ICCDevInter_ZS_SB");

        System.loadLibrary("dk/DK_SSCardDriver");
    }

    @GetMapping(value = "/readCard", produces = {"application/json;charset=UTF-8"})
    public String readCardInfo(){
        Map<String, String> resMap = new LinkedHashMap<>();
        byte[] outData = new byte[1024];
        int code = DKICCInter.IC_ReadCardInfo(outData);
        resMap.put("code", String.valueOf(code));
        if (code == 0){
            resMap.putAll(RCFormatUtil.formatData(outData, "ksbm","klx","kahao","fakrq","kyxq","sfz","xm","xb","zdbh"));
//            String result = new String(outData).trim();
//            log.info("Person Info:"+result);
//            String[] sp_res = result.split("~");
//
//            resMap.put("KSBM", sp_res[0]);
//            resMap.put("KLX", sp_res[1]);
//            resMap.put("KAHAO", sp_res[2]);
//            resMap.put("FAKRQ", sp_res[3]);
//            resMap.put("KYXQ", sp_res[4]);
//            resMap.put("SFZ", sp_res[5]);
//            resMap.put("XM", sp_res[6]);
//            resMap.put("XB", sp_res[7]);
        }else{
            resMap.put("errMsg", new String(outData).trim());
        }
        return JSON.toJSONString(resMap);
    }

    @GetMapping(value = "/rfReadCard", produces = {"application/json;charset=UTF-8"})
    public String ICRFReadCard(){
        System.out.println("31313313132");
        Map<String, String> resMap = new LinkedHashMap<>();
        byte[] outData = new byte[1024];
        int code = DKICCInter.IC_RF_ReadCardInfo(outData);
        resMap.put("code", String.valueOf(code));
        if (code == 0){
            resMap.putAll(RCFormatUtil.formatData(outData, "rfksbm","xm","zjh","zjlx","xb","jcskh","ylkh"));
//            String result = new String(outData).trim();
//            log.info("Person Info:"+result);
//            String[] sp_res = result.split("~");
//
//            resMap.put("RFKSBM", sp_res[0]);
//            resMap.put("XM", sp_res[1]);
//            resMap.put("ZJH", sp_res[2]);
//            resMap.put("ZJLX", sp_res[3]);
//            resMap.put("XB", sp_res[4]);
//            resMap.put("JCSKH", sp_res[5]);
//            resMap.put("YLKH", sp_res[6]);
        }else{
            resMap.put("errMsg", new String(outData).trim());
        }
        return JSON.toJSONString(resMap);
    }

    @GetMapping(value = "/readFlag", produces = {"application/json;charset=UTF-8"})
    public String ICReadFlag(){
        Map<String, String> resMap = new LinkedHashMap<>();
        byte[] outData = new byte[1024];
        int code = DKICCInter.IC_ReadFlag(outData);
        resMap.put("code", String.valueOf(code));
        if (code == 0){
            resMap.putAll(RCFormatUtil.formatData(outData, "ktbz"));
//            String result = new String(outData).trim();
//            log.info("Person Info:"+result);
//            String[] sp_res = result.split("~");
//
//            resMap.put("KTBZ", sp_res[0]);
        }else{
            resMap.put("errMsg", new String(outData).trim());
        }
        return JSON.toJSONString(resMap);
    }

    @GetMapping(value = "/readIDCard", produces = {"application/json;charset=UTF-8"})
    public String ICReadIDCard(){
        Map<String, String> resMap = new LinkedHashMap<>();
        byte[] outData = new byte[1024];
        int code = DKICCInter.IC_ReadIdCard(outData);
        resMap.put("code", String.valueOf(code));
        if (code == 0){
            resMap.putAll(RCFormatUtil.formatData(outData, "xm","xb","mz","csrq","dz","zjh","fkbm","fkrq","yxq"));
//            String result = new String(outData).trim();
//            log.info("Person Info:"+result);
//            String[] sp_res = result.split("~");
//
//            resMap.put("XM", sp_res[0]);
//            resMap.put("XB", sp_res[1]);
//            resMap.put("MZ", sp_res[2]);
//            resMap.put("CSRQ", sp_res[3]);
//            resMap.put("DZ", sp_res[4]);
//            resMap.put("ZJH", sp_res[5]);
//            resMap.put("FKBM", sp_res[6]);
//            resMap.put("FKRQ", sp_res[7]);
//            resMap.put("YXQ", sp_res[8]);
        }else{
            resMap.put("errMsg", new String(outData).trim());
        }
        return JSON.toJSONString(resMap);
    }
}
