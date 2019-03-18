import com.ost.OSTSDK;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;

public class Base {


    public String s6Testnet = "s6-testnet";
    public String s7Testnet = "s7-testnet";
    public String s6Mainnet = "s6-mainnet";
    public String s7Mainnet = "s7-mainnet";




    public String apiKey;
    public String secretKey;
    public String endPoint;
    public String companyId;
    public String companyTH;
    public String user_bhavik_id;
    public String transaction_bhavik_id;
    public String user_bhavik_TH;
    public String pricer_TR;
    public String directTransfer_TR;
    public String device_bhavik_address;
    public String session_bhavik_address;
    public String aux_chain_id;
    public String origin_chain_id;
    public String recivery_owner_address;


    public  com.ost.services.Manifest services;
    @BeforeTest
    public void createBasicObject()
    {

        switch (s7Testnet)
        {
            case "s6-testnet" :
                System.out.println("s6-testnet");
                setupS6Testnet();
                break;

            case "s7-testnet" :
                System.out.println("s7-testnet");
                setupS7Testnet();
                break;

            case "s6-mainnet" :
                System.out.println("s6-mainnet");
                setups6Mainnet();
                break;

            case "s7-mainnet" :
                System.out.println("test");
                setupS7Mainnet();
                break;

                default:
                    System.out.println("Environment is not properly set");
                    break;
        }


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

    private void setupS7Mainnet() {
    }

    private void setups6Mainnet() {
    }

    private void setupS6Testnet() {

        apiKey="65e20fcfce72f4c34546338a70518478";
        secretKey="f07f94340ab66045634d7505385a53e4ed12f7d9792a40798f60fa9a95adb3e0";
        endPoint="https://s6-api.stagingost.com/testnet/v2/";
        companyId="724ed66c-8a0a-477e-b303-b0486e2a3797";
        companyTH="0xa9632350057c2226c5a10418b1c3bc9acdf7e2ee";
        user_bhavik_id="8caa6412-ab11-41d6-8177-2928e948a9a8";
        transaction_bhavik_id = "c03dde62-5baa-417d-8e64-8cd94dc950e9";
        user_bhavik_TH="0xa7261667e6ec6768f6309c7d907dcad7acf0bafa";
        pricer_TR="0x1a83bc05cc3ae1b19f2359d847e2589d9d91fb90";
        directTransfer_TR="0x64315ba1018307d6bc0380fa8eb8af210991ccbc";
        device_bhavik_address="0xd93c3d44fb52af44879cb54eb54d539f386b59c3";
        session_bhavik_address="0xc906b56782e26d9b848bb5a3c64ded3be9ca57ec";
        aux_chain_id="199";
        origin_chain_id="3";
        recivery_owner_address="0x0f57cd47a64b42b444bc841f299715ae5881d7bd";
    }


    private void setupS7Testnet() {

        apiKey="ff316ee09ebed1e9d148092ac7a0b111";
        secretKey="483eb279b621d96a265f24c1223c0c8b6885f21480cfc3b085f2d99c48d9a371";
        endPoint="https://s7-api.stagingost.com/testnet/v2/";
        companyId="081cca44-b32a-4423-84f8-4d4590514da3";
        companyTH="0x415c36485f7e1f90f28ab72451c14e633b29415f";
        user_bhavik_id="43694077-4c10-498d-ac36-e2f1321ac2a1";
        transaction_bhavik_id = "d2600880-8113-4157-a9fe-6ab4b703f71a";
        user_bhavik_TH="0x8ba427184203cf56ede13eafbe2b42d6aa322d0a";
        pricer_TR="0x4e3d351a8f36b7b0b6ce94c1ee56762a9193330a";
        directTransfer_TR="0x96d05d37df79b90b44e8d1f2092e350120d61489";
        device_bhavik_address="0xd93c3d44fb52af44879cb54eb54d539f386b59c3";
        session_bhavik_address="0xf4441dd87eb4fec2aa11dac46941db7e1fdc389d";
        aux_chain_id="198";
        origin_chain_id="3";
        recivery_owner_address="0x640bd9cf656f2b90ed1b80dc714e420201b1761a";
    }

}
