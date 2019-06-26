import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;

public class Base {


    public String s6Testnet = "s6-testnet";
    public String s7Testnet = "s7-testnet";
    public String s6Mainnet = "s6-mainnet";
    public String s7Mainnet = "s7-mainnet";
    public String platformSandbox = "platformSandbox";
    public String alpeshEconomy = "alpeshEconomy";
    public String s4Testnet = "setupS4Testnet";




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

        switch (platformSandbox)
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
                System.out.println("s7 mainnet");
                setupS7Mainnet();
                break;

            case "platformSandbox" :
                System.out.println("platform sandbox");
                setupPlatformSandbox();
                break;

            case "alpeshEconomy" :
                System.out.println("alpesh economy");
                alpeshEconomy();
                break;

            case "setupS4Testnet" :
                System.out.println("s4 testnet");
                setupS4Testnet();
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
        nestedparam.put("timeout", (long) 15);
        sdkConfig.put("config", nestedparam);

        OSTSDK ostObj = new OSTSDK(sdkConfig);
        services = (com.ost.services.Manifest) ostObj.services;
    }

    private void setupS7Mainnet() {
    }

    private void setups6Mainnet() {
    }

    private void setupS6Testnet() {

        apiKey="534bd4f314eaa014bc97ba6fd146d7ca";
        secretKey="80cb2038439b0b9cba28a66f7789d3f7bf15acf9e457d3416279c3e98bfbff76";
        endPoint="https://s6-api.stagingost.com/testnet/v2/";
        companyId="cbb33e18-e0bd-4e3c-a9f4-b7d8d51ac70f";
        companyTH="0xfae609af29acc68a291b8e63a442c93c2adc05d8";
        user_bhavik_id="0cffb642-cbc6-4743-b82c-853a9e6fbfcb";
        transaction_bhavik_id = "18718072-9f60-4f5c-ae08-a6f73c1ea603";
        user_bhavik_TH="0xd498efdb81c325e6ffb73099f4411bf38711313d";
        pricer_TR="0x4a9d65044535984fe06a368c1575d3b4a877c726";
        directTransfer_TR="0xcafe440aa5bdd62f65d13160e50c6da2de7182f8";
        device_bhavik_address="0x33dBD073dD9b01d5722f4345767a0681a402a487";
        session_bhavik_address="0xd2887e0d1c9610f604d588d598c7c38da9f1267d";
        aux_chain_id="199";
        origin_chain_id="3";
        recivery_owner_address="0x15c6dff793dcf3fdac5cf2423838f43cfd4e5f8d";
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


    private void setupPlatformSandbox() {

        apiKey="77641a73261c5d9db503bb8f2bae26e4";
        secretKey="70bb23e90b06ad016658fecb0e76af922e1978ec1224592689e44eff265a83f6";
        endPoint="https://api.ost.com/testnet/v2/";
        companyId="8fa7588d-f07b-41ba-8f80-e1554daa4d1a";
        companyTH="0xc036a1cb25c9b9598a450b3a64c96a132e889ce8";
        user_bhavik_id="199f41f8-80aa-44bd-b5e5-fe91120f385d";
        transaction_bhavik_id = "dabe417c-785e-4a00-8db1-2c5cc5fe32ee";
        user_bhavik_TH="0xF4fF8Fb5a131C55961fcCaB2688A3f0625477636";
        pricer_TR="0x8f38b329950949c51e556b6d566c59079c7ee865";
        directTransfer_TR="0x41e20b66ec28355efe4ca59636fe4d6ca0e1f23e";
        device_bhavik_address="0xED4362fD8E0e42fD3Ba11A594f13b01b5498FD18";
        session_bhavik_address="0xcc0fd7e92d104cf41a1e0786ad7e7ebb7e243e6c";
        aux_chain_id="1407";
        origin_chain_id="3";
        recivery_owner_address="0x8866333ef820657799953338212c96a2d649b9f4";
    }

    public boolean getSuccessMessage(JsonObject response)
    {
        return response.get("success").getAsBoolean();
    }

    private void alpeshEconomy() {

        apiKey="d1deba53f99875b38ca941c04f819e86";
        secretKey="97401032bf2f074bf569fef439349d4715ceada701ab3ec81f6fc5bc8329aefa";
        endPoint="https://api.ost.com/testnet/v2/";
        companyId="d0c4ddbd-94d2-49e8-b8f4-5d585fae3985";
        companyTH="0x1398dc1f37a7b044e975fd8e0f930b4d5a741085";
        user_bhavik_id="6b58ce64-f679-4132-8053-db3b41f53b60";
        transaction_bhavik_id = "4f8688df-a8ab-4993-9722-72d34453b773";
        user_bhavik_TH="0x70b3e9494d5af4cccbb7a9b32b7f080a5f0f5c84";
        pricer_TR="0x455cbc5949f0faae750038ce0068a9bbbae2ff15";
        directTransfer_TR="0x03c48121ae6670cbaae59e8701e6073eba79cbac";
        device_bhavik_address="0x6D554409fD49291689C4ED4572A294E2D3617317";
        session_bhavik_address="0x85299ec838698c069dbaadd4b5ccdb607962b49d";
        aux_chain_id="1407";
        origin_chain_id="3";
        recivery_owner_address="0x77277cb31463396c46f9cb5d497df0716df48d9d";
    }

    private void setupS4Testnet() {

        apiKey="b528ddd4486a4445a1210945cc384715";
        secretKey="9897931d8e94b527dbb9c10aa166344ec9a4c4a056df767ec35dc837e3725588";
        endPoint="https://api.stagingost.com/testnet/v2/";
        companyId="74616237-cb46-4255-a61b-cee2dd7d575f";
        companyTH="0x48948955445a0973e56b6ba9f7d8b6c2b2de9c65";
        user_bhavik_id="6db0f998-8cd9-42c8-92e1-f8a9c2f50f65";
        transaction_bhavik_id = "4f8688df-a8ab-4993-9722-72d34453b773";
        user_bhavik_TH="0x7d1719b5d850ce88b2469fdbc2ed6ca712bd0b0e";
        pricer_TR="0xfe2f4f2a62c5c4541d48a861504be781aaf82efd";
        directTransfer_TR="0x90dd893384d9d4c01f06d150db9a76bf1f7ffca1";
        device_bhavik_address="0x6D554409fD49291689C4ED4572A294E2D3617317";
        session_bhavik_address="0x85299ec838698c069dbaadd4b5ccdb607962b49d";
        aux_chain_id="1407";
        origin_chain_id="3";
        recivery_owner_address="0xfaed86460dbc32741f9db51fddd9aeaad5b804f1";
    }

}
