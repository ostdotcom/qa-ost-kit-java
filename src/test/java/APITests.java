import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import com.ost.services.OSTAPIService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class APITests extends Base {

// Chain Module

    @DataProvider(name = "chain_id")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {aux_chain_id, true},
                        {origin_chain_id, true},
                        {"201", false},  //Different economy's chain id
                        {"avfbdf", false},
                        {"", false},
                        {null, false},
                        {"-199", false}, //Negative
                        {"199asdf", false}, // alphanumeric
                        {"1234567899999234567890987656789", false},// Max Number
                        {"1", false}, // Min Number
                        {"199-9 2", false}, //Hypen and space
                        {"199.92", false}, //Decimal Value
                        {Integer.parseInt(aux_chain_id), true}, //Integer
                        {"!@#$%^&^*(", false}, //SPECIAL CHARACTER
                        {"199_262", false} // UNDERSCORE
                };
    }

    @Test(dataProvider = "chain_id")
    public void check_chain_id(Object chainId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {

        com.ost.services.Chains chainsService = services.chains;

        try {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("chain_id", chainId);
            JsonObject response = chainsService.get(params);
            System.out.println("response: " + response.toString());
            Assert.assertEquals(successStatus, getSuccessMessage(response));
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+chainId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+chainId);
            }
        }
    }

//Price Point Module

    @Test(dataProvider = "chain_id")
    public void check_price_point(Object chainId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.PricePoints pricePointsService = services.pricePoints;
        try{


        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("chain_id", chainId);
        JsonObject response = pricePointsService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+chainId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+chainId);
            }
        }
    }

    @Test()
    public void check_token() throws IOException, OSTAPIService.MissingParameter {
        com.ost.services.Tokens tokensService = services.tokens;
        HashMap<String, Object> params = new HashMap<String, Object>();
        JsonObject response = tokensService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(true,getSuccessMessage(response));
    }


    @Test
    public void check_token_rule() throws IOException {
        com.ost.services.Rules rulesService = services.rules;
        HashMap<String, Object> params = new HashMap<String, Object>();
        JsonObject response = rulesService.getList(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(true,getSuccessMessage(response));
    }


// User Module

    @Test(threadPoolSize = 10,invocationCount = 100 )
    public void create_user() throws IOException {
        com.ost.services.Users userService = services.users;
        HashMap<String, Object> params= new HashMap<String, Object>();
        //params.put("random", RandomUtils.nextFloat(1,100));
        JsonObject response = userService.create(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(true,getSuccessMessage(response));
    }

    @DataProvider(name = "user_id")
    public Object[][] getUserId() {
        return new Object[][]
                {
//                        {user_bhavik_id, true},
                        {companyId, true},
//                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622", false}, //different economy
//                        {"avfbdf", false},//alphabetic
//                        {"^!@$$@$#%&*^&(*", false}, //Special Char
//                        {" ", false},
//                        {null, false},
//                        {"-2d971b59-1cda-4fb4-a022-8b2fa65c7622", false},//Negative
//                        {"   2d971b59-1cda-4fb4-a022-8b2fa65c7622", false}, // Forward Space
//                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622  ", false}, // Backward Space
//                        {"1234_133", false}, // Underscore
//                        {"1234-12344", false}, // Hypen
//                        {"12345.12345", false}// Decimal Value
                };
    }

    @Test(dataProvider = "user_id")
    public void get_user(String userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Users userService = services.users;
        try{


        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = userService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    }


    @DataProvider(name = "limit")
    public Object[][] getLimit() {
        return new Object[][]
                {
                        {20, true},
                        {0, false},
                        {1, true},
                        {26, false},
                        {"abc", false}, //alphabetic
                        {"#%$^", false}, //Special Character
                        {" ",false}, //Blank
                        {null, false},
                        {"-25", false}, //Negative
                        {"122_123", false},// Underscore
                        {"122-1233", false}, //hypen
                        {"1234adff", false},// /alphanumeric
                        {"25.425", false} //Decimal Limit
                };
    }

    @Test(dataProvider = "limit")
    public void get_users_list(Object limit, boolean successStatus) throws IOException {
        com.ost.services.Users userService = services.users;
        try
        {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("limit", limit);
        JsonObject response = userService.getList(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+limit);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+limit);
            }
        }
    }

    @Test(enabled = false)
    public void get_users_list_filter_userId() throws IOException {
        com.ost.services.Users userService = services.users;
        HashMap<String, Object> params = new HashMap<String, Object>();
        ArrayList<Object> userIdsArray = new ArrayList<Object>();
        for (int i = 0; i < 26; i++) {
            userIdsArray.add("fd836794-96fe-4841-a486-f3d2966d3ac8");
            // userIdsArray.add("fbb7990a-c396-46d2-a179-b69e783bf231");
        }
        params.put("ids", userIdsArray);
        JsonObject response = userService.getList(params);
        System.out.println("response: " + response.toString());
    }


    @Test(threadPoolSize = 1,invocationCount = 1)
    public void create_device() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("address", user_bhavik_TH);
        params.put("api_signer_address", "0x5F860598383868e8E8Ee0ffC5ADD92369Db37454");
        params.put("device_uuid", "593a967f-87bd-49a6-976c-52edf46c4df4");
        params.put("device_name", "Iphone S");
        JsonObject response = devicesService.create(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(true,getSuccessMessage(response));
    }


    @Test(dataProvider = "user_id")
    public void get_device_user_id(Object userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        params.put("device_address", device_bhavik_address);
        JsonObject response = devicesService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
    }
        catch (Exception e)
        {
        if(successStatus)
        {
            Assert.fail("Given input is expected to pass. Input = "+userId);
        }
        else
        {
            System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
        }
        }
    }

// Device Module

    @Test(dataProvider = "device_address")
    public void get_device_deviceAdd(String deviceAddress, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("device_address", deviceAddress);
        JsonObject response = devicesService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
    }
        catch (Exception e)
    {
        if(successStatus)
        {
            Assert.fail("Given input is expected to pass. Input = "+deviceAddress);
        }
        else
        {
            System.out.println("Does not accept by SDK, which is expected for given input : "+deviceAddress);
        }
    }
    }

    @DataProvider(name = "device_address")
    public Object[][] getDeviceId() {
        return new Object[][]
                {
                    {device_bhavik_address, true},
                    {companyTH, false},
                    {user_bhavik_TH, false},
                    {"2d971b59-1cda-4fb4-a022-8b2fa65c7622", false}, // id
                    {"0x46336b3895b08af6fc3226f332f96e05cfd3f637", false}, //different user's device address
                    {"avfbdf", false},
                    {" ", false},
                    {null, false},
                    {"-2d971b59-1cda-4fb4-a022-8b2fa65c7622", false}, //Negative
                    {"  2d971b59-1cda-4fb4-a022-8b2fa65c7622", false}, //Forward Space
                    {"2d971b59-1cda-4fb4-a022-8b2fa65c7622  ", false}, //Backward Space
                    {"@#$%^&*(", false}, // Special Character
                    {"fyfshsa134uuru", false} //alphanumeric
                };
    }


    @Test(dataProvider = "user_id")
    public void get_devices_list_userId(String userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = devicesService.getList(params);
        System.out.println("response: " + response.toString());
            Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    }


    // To do - pagination identifier need to add
    @Test(dataProvider = "limit")
    public void get_devices_list_limit(Object limit, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("limit", limit);
        JsonObject response = devicesService.getList(params);
        System.out.println("response: " + response.toString());
            Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+limit);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+limit);
            }
        }
    }


    @Test()
    public void get_devices_list_filter_address() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        //params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
        ArrayList<Object> addressesArray = new ArrayList<Object>();
        for (int i = 0; i < 50; i++) {
            addressesArray.add("0x5906ae461eb6283cf15b0257d3206e74d83a6bd4");
            addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
        }
        params.put("addresses", addressesArray);
        JsonObject response = devicesService.getList(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(false,getSuccessMessage(response));
    }

// Device Manager Module

    @Test(dataProvider = "user_id")
    public void get_device_manager(String userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = deviceManagersService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    }

// Session Module

    @DataProvider(name = "session_address")
    public Object[][] getSessionAdd() {
        return new Object[][]
                {
                        {session_bhavik_address, true},
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, //company's session address (different user's address)
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622", false},
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622", false},
                        {" ", false},
                        {null, false},
                        {"-0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, // Negative
                        {"  0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, //forward Space
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331  ", false}, //Backward Space
                        {"0xea2d2-4141de586be3d-3ab0b425aaa7a-118ba3331", false}, //Hypen
                        {"0xea2d241_41de586be3d3ab_0b425aaa7a118b_a3331", false} //Underscore
                };
    }

    @Test(dataProvider = "session_address")
    public void get_session(String sessionAddress, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Sessions sessionsService = services.sessions;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("session_address", sessionAddress);
        JsonObject response = sessionsService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+sessionAddress);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+sessionAddress);
            }
        }
    }

    @Test(dataProvider = "limit")
    public void get_sessions_list(Object limit, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Sessions sessionsService = services.sessions;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("limit", limit);
        JsonObject response = sessionsService.getList(params);
        System.out.println("response: " + response.toString());
            Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+limit);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+limit);
            }
        }
    }

// Recovery Owner Module

    @Test(enabled = false)
    public void get_recovery_owner() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.RecoveryOwners recoveryOwnersService = services.recoveryOwners;

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
        params.put("recovery_owner_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
        JsonObject response = recoveryOwnersService.get(params);
        System.out.println("response: " + response.toString());
    }

// Balance Module

    @Test(dataProvider = "user_id")
    public void get_balance(String userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Balance balanceService = services.balance;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = balanceService.get(params);
        System.out.println("response: " + response.toString());
            Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    }

    // Get Transaction Module

    @DataProvider(name = "get_transaction_user")
    public Object[][] getTransactionUser() {
        return new Object[][]
                {
                        {user_bhavik_id, transaction_bhavik_id, true},
                        {companyId, transaction_bhavik_id, true},
                        {user_bhavik_id, "920b7f72-becf-42ec-8921-af389e8bde64", false},   //different user's transaction
                        {companyId, "5207d6db-e746-44ab-9cd3-aad0db1bd172", false},  //different economy's tx
                        {" ", " ", false},
                        {null, null, false},
                        {user_bhavik_id, "fgdgdhd", false}, //Invalid
                        {companyId, "!@##$@%@^@^", false}, //Special Character

                };
    }


    @Test(dataProvider = "get_transaction_user")
    public void get_transaction(String userId, String transactionId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        params.put("transaction_id", "fad4c31a-b172-429c-b1f2-5b023841bebb");
        JsonObject response = transactionsService.get(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    }
// Execute Transaction Module By Direct Method

    // threadPoolSize = 300, invocationCount = 2000
    @Test(dataProvider = "user_id", threadPoolSize = 10, invocationCount = 50)
    public void execute_transaction_DT_userId(String userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String toAddress = directTransfer_TR;
        String parameter1 =user_bhavik_TH;
        String parameter2 = "1000000000000000000";
        params.put("user_id", userId);
        params.put("to", toAddress);
        params.put("random", RandomUtils.nextFloat(1,100));
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(parameter1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(parameter2);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    }

// Token  Module


    @DataProvider(name = "token_rule")
    public Object[][] getTokenRule() {
        return new Object[][]
                {
                        {directTransfer_TR, true},
                        {pricer_TR, true},
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331" , false}, //company's session address (different user's address)
                        {"0x7a27e897ab4b6579c5fe9880806b5a0b7c9e6f0d", false}, //Different economy's Token rule
                        {" ", false},
                        {null, false},
                        {"-0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, // Negative
                        {"  0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, //Forward Space
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331   ", false}, //Backward Space
                        {"@#$%^&*(&*(*&", false}, //Special Character
                        {"hdhdhdhd", false},  //alphabetic
                        {"gsfd2_34567", false}, //alphanumeric
                };
    }


    @Test(dataProvider = "token_rule")
    public void execute_transaction_DT_TokenRule(String token_rule_id, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;

        HashMap<String,Object> sdkConfig = new HashMap<String,Object>();
        sdkConfig.put("apiEndpoint",endPoint);
        sdkConfig.put("apiKey",apiKey);
        sdkConfig.put("apiSecret",secretKey);


        HashMap <String,Object> nestedparam = new HashMap<String,Object>();
        nestedparam.put("timeout", (long) 15);
        sdkConfig.put("config", nestedparam);

        OSTSDK ostObj = new OSTSDK(sdkConfig);
        services = (com.ost.services.Manifest) ostObj.services;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = token_rule_id;
        String transferTo1 = user_bhavik_TH;
        String transferAmount1 = "100";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(transferTo1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(transferAmount1);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+token_rule_id);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+token_rule_id);
            }
        }
    }


    @DataProvider(name = "transfer_to")
    public Object[][] getTransferToAdd() {
        return new Object[][]
                {
                    {user_bhavik_TH, true},
                    {companyTH, true},
                    {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, //unkown address
                    {"abcf12342mfk34", false},
                    {" ", false},
                    {null, false},
                    {"-0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, // negative
                    {" 0xea2d24141de586be3d3ab0b425aaa7a118ba3331", false}, //forward space
                    {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331 ", false}, //Backward Space
                    {"gsgd_hgdhf-dh", false}, //alphabetic
                };
    }


    @Test(dataProvider = "transfer_to")
    public void execute_transaction_DT_transferTo(String transfer_to, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String parameter1 = transfer_to;
        String parameter2 = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(parameter1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(parameter2);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+transfer_to);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+transfer_to);
            }
        }
    }


    @DataProvider(name = "transfer_amount")
    public Object[][] getTransferAmount() {
        return new Object[][]
                {
                        {1, true},
                        {0, false},
                        {"10", true}, //amount
                        {"abcf12342mf", false},
                        {" ", false},
                        {null, false},
                        {"-112525", false}, //Negative Value
                        {"fafsdgd", false}, //Alphabetic
                        {"fsafsfd1235", false}, //alphanumeric
                        {"   10000000", false},// Amount with Space
                        {"1009393_515363", false}, //underscore
                        {"126789-2345678", false}, //Hypen
                        {"!@#$%^&*()_)(*&", false}, // Special Character
                        {"123.25252622626262", false}, //Decimal
                        {"?", false}
                };
    }

    @Test(dataProvider = "transfer_amount")
    public void execute_transaction_DT_amount(Object transfer_amount, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String transferTo1 = user_bhavik_TH;
        String transferTo2 = String.valueOf(transfer_amount);
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(transferTo1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(transferTo2);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+transfer_amount);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+transfer_amount);
            }
        }
    }



    @Test
    public void execute_transactions_multiple_transafer() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String transferTo1 = user_bhavik_TH;
        String transferAmount1 = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();

        for (int i = 0; i < 5; i++) {
            arrayList1.add(transferTo1);
            arrayList2.add(transferAmount1);
        }

        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(true, getSuccessMessage(response));
    }

    @DataProvider(name = "meta_name")
    public Object[][] getMetaName() {
        return new Object[][]
                {
                        { "name_test3",true}
//                        {"download_IMP MaxLength-25", true},  //Exact 25 characters
//                        {"download IMP MaxLength 25 ", false}, //More than 25
//                        {"t#$%#$^$%^d", false}, // Special Character
//                        {"https://ost.com/", false},
//                        {" ", true},
//                        {null, false},
//                        {"afsgs123445", true}, //alphanumeric
//                        {"-hshydhyffhbbh  1344", true}, //Negative alphanumeric value with space
                };
    }


    @Test(dataProvider = "meta_name", threadPoolSize = 2,invocationCount = 5000)
    public void execute_transaction_DT_meta_name(String meta_name, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
            System.out.println(meta_name);
        HashMap<String, Object> metaProperty = new HashMap<String, Object>();
        metaProperty.put("name", meta_name); // like, download
        metaProperty.put("type", "company_to_user"); // user_to_user, company_to_user, user_to_company
        metaProperty.put("details", "Test details"); // memo field to add additional info about the transaction

        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String transferTo1 = user_bhavik_TH;
        String transferAmount1 = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        params.put("random", RandomUtils.nextFloat(1,100));
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(transferTo1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(transferAmount1);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+meta_name);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+meta_name);
            }
        }
    }


    @DataProvider(name = "meta_type")
    public Object[][] getmetaType() {
        return new Object[][]
                {

                        {"company_to_user", true},
                        {"user_to_user", true},
                        {"user_to_company", true},
                        {"company to company", false},
                        {"https://ost.com/", false},
                        {" ", false},
                        {null, false},
                        {"@#$%^&*(", false}, // Special Character
                };
    }


    @Test(dataProvider = "meta_type")
    public void execute_transaction_DT_meta_type(String meta_type, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
        HashMap<String, Object> metaProperty = new HashMap<String, Object>();
        metaProperty.put("name", "Test name"); // like, download
        metaProperty.put("type", meta_type); // user_to_user, company_to_user, user_to_company
        metaProperty.put("details", "Test details"); // memo field to add additional info about the transaction

        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String transferTo1 = user_bhavik_TH;
        String transferAmount1 = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(transferTo1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(transferAmount1);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+meta_type);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+meta_type);
            }
        }
    }


    @DataProvider(name = "meta_details")
    public Object[][] getmetaDetails() {
        return new Object[][]
                {
                        /*Exact 125 chars*/  {"memo field to add additional info about the transaction IMP Max length 120 characters numbers alphabets spaces_-allowed__ VKL", true},
/*more thna 125 chars*/ {"memo field to add additional info about the transaction IMP Max length 120 characters numbers alphabets spaces_-allowed__ VKLB", false},
                        {"https://ost.com/", false},
                        {" ", true},
                        {null, false},
                        {"@#$%^&*()(*!@#$%^&*", false}, //Special Character
                        {"-ggdgdgd123456789", true}, // Negative
                        {"123 45678900987_54321", true} // Numeric Value
                };
    }


    @Test(dataProvider = "meta_details")
    public void execute_transaction_DT_meta_details(String meta_details, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
        HashMap<String, Object> metaProperty = new HashMap<String, Object>();
        metaProperty.put("name", "Test name"); // like, download
        metaProperty.put("type", "company_to_user"); // user_to_user, company_to_user, user_to_company
        metaProperty.put("details", meta_details); // memo field to add additional info about the transaction

        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String transferTo1 = user_bhavik_TH;
        String transferAmount1 = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(transferTo1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(transferAmount1);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+meta_details);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+meta_details);
            }
        }
    }


    @Test(dataProvider = "user_id")
    public void get_transactions_list_userId(String user_id, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        JsonObject response = transactionsService.getList(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+user_id);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+user_id);
            }
        }
    }


    @DataProvider(name = "status")
    public static Object[][] getStatus() {
        return new Object[][]{

                {"CREATED"},
                {"SUBMITTED"},
                {"MINED"},
                {"SUCCESS"},
                {"FAILED"},
                {"TEST"},
                {" "},
                {null},
                {"!@#$%^&*"}, // Special Character
                {"-2345678"}, //Negative
                {"aksjdjhdf"}, //alphabetic
                {"sgsgsgs123456"}, //alphanumeric
                {"     "} //Blank Spaces
        };
    }

    @Test(dataProvider = "status")
    public void get_transactions_list_status(Object status) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;

        ArrayList<Object> statusArray = new ArrayList<Object>();
        statusArray.add(status);


        HashMap <String,Object> params = new HashMap<String,Object>();
        params.put("user_id", user_bhavik_id);
        params.put("statuses", statusArray);
        JsonObject response = transactionsService.getList( params );
        System.out.println("response: " + response.toString() );
    }


    @Test(dataProvider = "limit")
    public void get_transactions_list_limit(Object limit, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try
        {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", companyId);
        params.put("limit", limit);
        JsonObject response = transactionsService.getList(params);
        System.out.println("response: " + response.toString());
        Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+limit);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+limit);
            }
        }
    }


    @Test
    public void get_transaction_statuses() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter, InterruptedException {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> metaProperty = new HashMap<String, Object>();
        metaProperty.put("name", "Test name"); // like, download
        metaProperty.put("type", "company_to_user"); // user_to_user, company_to_user, user_to_company
        metaProperty.put("details", "verifying transaction status"); // memo field to add additional info about the transaction

        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = directTransfer_TR;
        String transferTo1 = user_bhavik_TH;
        String transferAmount1 = "1";
        params.put("user_id", userId);
        params.put("to", toAddress);
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(transferTo1);
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(transferAmount1);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);
        params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());

        Thread.sleep(1000);


        for (int i = 0; i <= 100; i++) {
            Thread.sleep(50);
            HashMap<String, Object> getTransactionParams = new HashMap<String, Object>();
            getTransactionParams.put("user_id", companyId);
            String transactionId = response.getAsJsonObject("data").getAsJsonObject("transaction").get("id").getAsString();
            getTransactionParams.put("transaction_id", transactionId);
            JsonObject getTransactionResponse = transactionsService.get(getTransactionParams);
            System.out.println("response: " + getTransactionResponse.toString());
            String transactionStatus = getTransactionResponse.getAsJsonObject("data").getAsJsonObject("transaction").get("status").getAsString();
            System.out.println("transaction status is : " + transactionStatus);

            if (transactionStatus.equals("SUCCESS"))
                break;
        }
    }

    @Test()
    public void pay_transfer() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {

        com.ost.services.Transactions transactionsService = services.transactions;

        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = pricer_TR;
        String user2TokenHolderAddress = user_bhavik_TH ;
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
        String tokenHolderSender = companyTH;
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
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
    }

/*
    @Test(dataProvider="user_id", threadPoolSize = 500, invocationCount = 500)
    public void execute_transaction_DT_alpesh(String userId, boolean successStatus) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        try{
            HashMap<String, Object> params = new HashMap<String, Object>();
            HashMap<String, Object> nestedparams = new HashMap<String, Object>();
            String toAddress = directTransfer_TR;
            String parameter1 = user_bhavik_TH;
            String parameter2 = "1";
            params.put("user_id", companyId);
            params.put("to", toAddress);
            params.put("random", RandomUtils.nextFloat(1,10000));
            nestedparams.put("method", "directTransfers");
            ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
            ArrayList<Object> arrayList1 = new ArrayList<Object>();
            arrayList1.add(parameter1);
            ArrayList<Object> arrayList2 = new ArrayList<Object>();
            Gson gsonObj = new Gson();
            arrayList2.add(parameter2);
            nestedarraylist.add(arrayList1);
            nestedarraylist.add(arrayList2);
            nestedparams.put("parameters", nestedarraylist);
            String jsonStr = gsonObj.toJson(nestedparams);
            params.put("raw_calldata", jsonStr);
            JsonObject response = transactionsService.execute(params);
            System.out.println("response: " + response.toString());
            Assert.assertEquals(getSuccessMessage(response),successStatus);
        }
        catch (Exception e)
        {
            if(successStatus)
            {
                Assert.fail("Given input is expected to pass. Input = "+userId);
            }
            else
            {
                System.out.println("Does not accept by SDK, which is expected for given input : "+userId);
            }
        }
    } */

}
