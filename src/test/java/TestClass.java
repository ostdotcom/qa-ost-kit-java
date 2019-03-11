import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.HashMap;

public class TestClass {

    public static void main(String args[]) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {

        HashMap <String,Object> sdkConfig = new HashMap<String,Object>();
        sdkConfig.put("apiEndpoint","https://s6-api.stagingost.com/testnet/v2/");
        sdkConfig.put("apiKey","65e20fcfce72f4c34546338a70518478");
        sdkConfig.put("apiSecret","f07f94340ab66045634d7505385a53e4ed12f7d9792a40798f60fa9a95adb3e0");

// The config field is optional for sdkConfig Object
        HashMap <String,Object> nestedparam = new HashMap<String,Object>();
// This is the timeout in seconds for which the socket connection will remain open
// The value of timeout will always be of type long
        nestedparam.put("timeout", (long) 15);
        sdkConfig.put("config", nestedparam);

        OSTSDK ostObj = new OSTSDK(sdkConfig);
        com.ost.services.Manifest services = (com.ost.services.Manifest) ostObj.services;

        com.ost.services.Chains chainsService = services.chains;

        HashMap <String,Object> params = new HashMap<String,Object>();
        params.put("chain_id", "199");
        JsonObject response = chainsService.get( params );
        System.out.println("response: " + response.toString() );
    }
}
