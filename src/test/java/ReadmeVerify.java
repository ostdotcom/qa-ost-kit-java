import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadmeVerify {

    public static void main(String args[]) throws IOException, OSTAPIService.InvalidParameter, OSTAPIService.MissingParameter {

        HashMap<String,Object> sdkConfig = new HashMap<String,Object>();
        sdkConfig.put("apiEndpoint","https://api.ost.com/testnet/v2/");
        sdkConfig.put("apiKey","77641a73261c5d9db503bb8f2bae26e4");
        sdkConfig.put("apiSecret","70bb23e90b06ad016658fecb0e76af922e1978ec1224592689e44eff265a83f6");

// The config field is optional for sdkConfig Object
        HashMap <String,Object> nestedparam = new HashMap<String,Object>();
// This is the timeout in seconds for which the socket connection will remain open
// The value of timeout will always be of type long
        nestedparam.put("timeout", (long) 15);
        sdkConfig.put("config", nestedparam);

        OSTSDK ostObj = new OSTSDK(sdkConfig);
        com.ost.services.Manifest services = (com.ost.services.Manifest) ostObj.services;


        com.ost.services.Users usersService = services.users;
        com.ost.services.Devices devicesService = services.devices;
        com.ost.services.Sessions sessionsService = services.sessions;
        com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
        com.ost.services.Rules rulesService = services.rules;
        com.ost.services.PricePoints pricePointsService = services.pricePoints;
        com.ost.services.Transactions transactionsService = services.transactions;
        com.ost.services.Balance balancesService = services.balance;
        com.ost.services.RecoveryOwners recoveryOwnersService = services.recoveryOwners;

//        HashMap <String,Object> params = new HashMap<String,Object>();
//        JsonObject response = usersService.create( params );
//        System.out.println("response: " + response.toString() );
//
//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "6b58ce64-f679-4132-8053-db3b41f53b60");
//        JsonObject response = usersService.get( params );
//        System.out.println("response: " + response.toString() );


//        HashMap <String,Object> params = new HashMap<String,Object>();
//        ArrayList<Object> idsArray = new ArrayList<Object>();
//        idsArray.add("29f57b59-60af-4579-9d6c-2ebcb36a9142");
//        idsArray.add("12f57b59-60af-4579-9d6c-2ebcb36a9123");
//        params.put("ids", idsArray);
//        params.put("limit", 10);
//        JsonObject response = usersService.getList( params );
//        System.out.println("response: " + response.toString() );




//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "6b58ce64-f679-4132-8053-db3b41f53b60");
//        params.put("address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
//        params.put("api_signer_address", "0x5F860598383868e8E8Ee0ffC5ADD92369Db37455");
//        params.put("device_uuid", "593a967f-87bd-49a6-976c-52edf46c4df4");
//        params.put("device_name", "Iphone S");
//        JsonObject response = devicesService.create( params );
//        System.out.println("response: " + response.toString() );

//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//        params.put("device_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
//        JsonObject response = devicesService.get( params );
//        System.out.println("response: " + response.toString() );


//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "6b58ce64-f679-4132-8053-db3b41f53b60");
////params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
//ArrayList<Object> addressesArray = new ArrayList<Object>();
////addressesArray.add("0x7be894952783e191092f917847c238463f38efe0");
////addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
//params.put("addresses", addressesArray);
//params.put("limit", 10);
//        JsonObject response = devicesService.getList( params );
//        System.out.println("response: " + response.toString() );


//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "6b58ce64-f679-4132-8053-db3b41f53b60");
//        JsonObject response = deviceManagersService.get( params );
//        System.out.println("response: " + response.toString() );



//
//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//        params.put("session_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
//        JsonObject response = sessionsService.get( params );
//        System.out.println("response: " + response.toString() );



//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
////params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
//    ArrayList<Object> addressesArray = new ArrayList<Object>();
//    addressesArray.add("0x5906ae461eb6283cf15b0257d3206e74d83a6bd4");
//    addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
//    params.put("addresses", addressesArray);
//    params.put("limit", 10);
//        JsonObject response = sessionsService.getList( params );
//        System.out.println("response: " + response.toString() );



//        HashMap <String,Object> params = new HashMap<String,Object>();
//        JsonObject response = rulesService.getList( params );
//        System.out.println("response: " + response.toString() );


//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("chain_id", "1407");
//        JsonObject response = pricePointsService.get( params );
//        System.out.println("response: " + response.toString() );


//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//        params.put("transaction_id", "e96450b8-f46a-40ee-adf1-9d65a4b53241");
//        JsonObject response = transactionsService.get( params );
//        System.out.println("response: " + response.toString() );

//
//        ArrayList<HashMap<String, Object>> metaPropertyArray = new ArrayList<HashMap<String, Object>>();
//HashMap <String,Object> metaPropertyArrayParams = new HashMap<String,Object>();
//metaPropertyArrayParams.put("name", "transaction_name"); //like, download IMP : Max length 25 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArrayParams.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaPropertyArrayParams.put("details", "test"); // memo field to add additional info about the transaction .  IMP : Max length 120 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArray.add(metaPropertyArrayParams);
//Gson gsonObj = new Gson();
//String metaPropertyArrayJsonStr = gsonObj.toJson(metaPropertyArray);
//
//ArrayList<Object> statusArray = new ArrayList<Object>();
//statusArray.add("CREATED");
//statusArray.add("SUBMITTED");
//statusArray.add("SUCCESS");
//statusArray.add("FAILED");
//
//        HashMap <String,Object> params = new HashMap<String,Object>();
//        params.put("user_id", "6b58ce64-f679-4132-8053-db3b41f53b60");
//params.put("statuses", statusArray);
//params.put("meta_properties", metaPropertyArrayJsonStr);
//params.put("limit", 10);
//        JsonObject response = transactionsService.getList( params );
//        System.out.println("response: " + response.toString() );



//HashMap <String,Object> metaProperty = new HashMap<String,Object>();
//metaProperty.put("name", "transaction_name"); // like, download
//metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaProperty.put("details", ""); // memo field to add additional info about the transaction
/*
        HashMap <String,Object> params = new HashMap<String,Object>();
        HashMap <String,Object> nestedparams = new HashMap<String,Object>();
        String userId = "29f57b59-60af-4579-9d6c-2ebcb36a9142";
        String toAddress = "0xe37906219ad67cc1301b970539c9860f9ce8d991";
        String user2TokenHolderAddress = "0xa31e988eebc89d0bc3e4a9a5463545ea534593e4";
        String amount = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "pay");
        ArrayList<Object> nestedarraylist = new ArrayList<Object>();
        ArrayList<Object> arrayListForUser2TokenHolderAddress = new ArrayList<Object>();
        arrayListForUser2TokenHolderAddress.add(user2TokenHolderAddress);
        ArrayList<Object> arrayListAmount = new ArrayList<Object>();
        arrayListAmount.add(amount);
        Gson gsonObj = new Gson();
        String tokenHolderSender = "0xa9632350057c2226c5a10418b1c3bc9acdf7e2ee";
        String payCurrencyCode = "USD";
        String ostToUsdInWei = "23757000000000000";
        nestedarraylist.add(tokenHolderSender);
        nestedarraylist.add(arrayListForUser2TokenHolderAddress);
        nestedarraylist.add(arrayListAmount);
        nestedarraylist.add(payCurrencyCode);
        nestedarraylist.add(ostToUsdInWei);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
//params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute( params );
        System.out.println("response: " + response.toString() );

*/


/*
        HashMap <String,Object> metaProperty = new HashMap<String,Object>();
metaProperty.put("name", "transaction_name"); // like, download
metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
metaProperty.put("details", "test"); // memo field to add additional info about the transaction

        HashMap <String,Object> params = new HashMap<String,Object>();
        HashMap <String,Object> nestedparams = new HashMap<String,Object>();
        String userId = "736f7175-93c5-4856-b7fe-6aeb9d75c591";
        String toAddress = "0x455cbc5949f0faae750038ce0068a9bbbae2ff15";
        String user2TokenHolderAddress = "0xc2fecBCA9F0CD7C4B6048075DeBf7A6F6870824a";
        String amount = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "pay");
        ArrayList<Object> nestedarraylist = new ArrayList<Object>();
        ArrayList<Object> arrayListForUser2TokenHolderAddress = new ArrayList<Object>();
        arrayListForUser2TokenHolderAddress.add(user2TokenHolderAddress);
        ArrayList<Object> arrayListAmount = new ArrayList<Object>();
        arrayListAmount.add(amount);
        Gson gsonObj = new Gson();
        String tokenHolderSender = "0x1e5c76731fc959ff33ab5efaf56fbba203fad65d";
        String payCurrencyCode = "USD";
        String ostToUsdInWei = "23757000000000000";
        nestedarraylist.add(tokenHolderSender);
        nestedarraylist.add(arrayListForUser2TokenHolderAddress);
        nestedarraylist.add(arrayListAmount);
        nestedarraylist.add(payCurrencyCode);
        nestedarraylist.add(ostToUsdInWei);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute( params );
        System.out.println("response: " + response.toString() );




        HashMap <String,Object> params = new HashMap<String,Object>();
        params.put("user_id", "736f7175-93c5-4856-b7fe-6aeb9d75c591");
        params.put("transaction_id", "d8b25619-720d-4c71-b6f9-7e508f0e794d");
        JsonObject response = transactionsService.get( params );
        System.out.println("response: " + response.toString() );


        ArrayList<HashMap<String, Object>> metaPropertyArray = new ArrayList<HashMap<String, Object>>();
HashMap <String,Object> metaPropertyArrayParams = new HashMap<String,Object>();
metaPropertyArrayParams.put("name", "transaction_name"); //like, download IMP : Max length 25 characters (numbers alphabets spaces _ - allowed)
metaPropertyArrayParams.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
metaPropertyArrayParams.put("details", "test"); // memo field to add additional info about the transaction .  IMP : Max length 120 characters (numbers alphabets spaces _ - allowed)
metaPropertyArray.add(metaPropertyArrayParams);
Gson gsonObj = new Gson();
String metaPropertyArrayJsonStr = gsonObj.toJson(metaPropertyArray);

ArrayList<Object> statusArray = new ArrayList<Object>();
statusArray.add("CREATED");
statusArray.add("SUBMITTED");
statusArray.add("SUCCESS");
statusArray.add("FAILED");

        HashMap <String,Object> params = new HashMap<String,Object>();
        params.put("user_id", "736f7175-93c5-4856-b7fe-6aeb9d75c591");
params.put("status", statusArray);
params.put("meta_property", metaPropertyArrayJsonStr);
params.put("limit", 10);
        JsonObject response = transactionsService.getList( params );
        System.out.println("response: " + response.toString() );



        HashMap <String,Object> params = new HashMap<String,Object>();
        params.put("user_id", "736f7175-93c5-4856-b7fe-6aeb9d75c591");
        JsonObject response = balancesService.get( params );
        System.out.println("response: " + response.toString() );
*/

        HashMap <String,Object> params = new HashMap<String,Object>();
        params.put("user_id", "736f7175-93c5-4856-b7fe-6aeb9d75c591");
        params.put("recovery_owner_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
        JsonObject response = recoveryOwnersService.get( params );
        System.out.println("response: " + response.toString() );
    }
}
