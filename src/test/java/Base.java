import com.ost.OSTSDK;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;

public class Base {

    public String apiKey="65e20fcfce72f4c34546338a70518478";
    public String secretKey="f07f94340ab66045634d7505385a53e4ed12f7d9792a40798f60fa9a95adb3e0";
    public String endPoint="https://s6-api.stagingost.com/testnet/v2/";
    public String companyId="724ed66c-8a0a-477e-b303-b0486e2a3797";
    public String companyTH="0xa9632350057c2226c5a10418b1c3bc9acdf7e2ee";
    public String user_bhavik_id="8caa6412-ab11-41d6-8177-2928e948a9a8";
    public String transaction_bhavik_id = "c03dde62-5baa-417d-8e64-8cd94dc950e9";
    public String user_bhavik_TH="0xa7261667e6ec6768f6309c7d907dcad7acf0bafa";
    public String pricer_TR="0x1a83bc05cc3ae1b19f2359d847e2589d9d91fb90";
    public String directTransfer_TR="0x64315ba1018307d6bc0380fa8eb8af210991ccbc";
    public String device_bhavik_address="0xd93c3d44fb52af44879cb54eb54d539f386b59c3";
    public String session_bhavik_address="0xc906b56782e26d9b848bb5a3c64ded3be9ca57ec";
    public String aux_chain_id="199";
    public String origin_chain_id="3";
    public String recivery_owner_address="0x0f57cd47a64b42b444bc841f299715ae5881d7bd";


    public  com.ost.services.Manifest services;
    @BeforeTest
    public void createBasicObject()
    {


        HashMap<String,Object> sdkConfig = new HashMap<String,Object>();
        sdkConfig.put("apiEndpoint",endPoint);
        sdkConfig.put("apiKey",apiKey);
        sdkConfig.put("apiSecret",secretKey);


        HashMap <String,Object> nestedparam = new HashMap<String,Object>();
        nestedparam.put("timeout", (long) 5);
        sdkConfig.put("config", nestedparam);

        OSTSDK ostObj = new OSTSDK(sdkConfig);
        services = (com.ost.services.Manifest) ostObj.services;
    }

}
