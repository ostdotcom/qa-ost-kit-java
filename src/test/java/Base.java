import com.google.gson.JsonObject;
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

        switch (s6Testnet)
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

        apiKey="5c3876e64b2286154b47310d90ac7931";
        secretKey="6434306c15725b3285858f4c536ef60a58389bc14a1fbe4fe4c4603741a7bbba";
        endPoint="https://s6-api.stagingost.com/testnet/v2/";
        companyId="d47af60e-c29e-484f-b7c1-32c637028f33";
        companyTH="0x557e631a3d556f7ad62382fe079ed76397f02133";
        user_bhavik_id="761e825f-8ce6-4149-b249-e094477a0c89";
        transaction_bhavik_id = "18718072-9f60-4f5c-ae08-a6f73c1ea603";
        user_bhavik_TH="0x4cD9e7bCB1643685E8aF10951767AEefc0690C94";
        pricer_TR="0x3b30cb28e355c14f5b571ad8b6b6b8aacac57fac";
        directTransfer_TR="0x10ae3ed8628c166b73185e8bd8348ecd7ac487d6";
        device_bhavik_address="0x33dBD073dD9b01d5722f4345767a0681a402a487";
        session_bhavik_address="0xd2887e0d1c9610f604d588d598c7c38da9f1267d";
        aux_chain_id="199";
        origin_chain_id="3";
        recivery_owner_address="0xb06a267c9c13295ca81b94394f3328cae46d60dd";
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

    public boolean getSuccessMessage(JsonObject response)
    {
        return response.get("success").getAsBoolean();
    }

}
