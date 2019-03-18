import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import com.ost.services.OSTAPIService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class APITests extends Base {

// Chain Module

    @DataProvider(name = "chain_id")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {aux_chain_id},
                        {origin_chain_id},
                        {"195"},
                        {"avfbdf"},
                        {""},
                        {null},
                        {"-199"}, //Negative
                        {"199asdf"}, // alphanumeric
                        {"1234567899999234567890987656789"},// Max Number
                        {"1"}, // Min Number
                        {"199-9 2"}, //Hypen and space
                        {"199.92"}, //Decimal Value
                        {199}, //Integer
                        {"!@#$%^&^*("}, //SPECIAL CHARACTER
                        {"199_262"} // UNDERSCORE

                };

    }

    @Test(dataProvider = "chain_id")
    public void check_chain_id(String chainId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {

        com.ost.services.Chains chainsService = services.chains;

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("chain_id", chainId);
        JsonObject response = chainsService.get(params);
        System.out.println("response: " + response.toString());
    }

//Price Point Module

    @Test(dataProvider = "chain_id")
    public void check_price_point(String chainId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.PricePoints pricePointsService = services.pricePoints;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("chain_id", chainId);
        JsonObject response = pricePointsService.get(params);
        System.out.println("response: " + response.toString());
    }

    @Test()
    public void check_token() throws IOException, OSTAPIService.MissingParameter {
        com.ost.services.Tokens tokensService = services.tokens;
        HashMap<String, Object> params = new HashMap<String, Object>();
        JsonObject response = tokensService.get(params);
        System.out.println("response: " + response.toString());
    }


    @Test
    public void check_token_rule() throws IOException {
        com.ost.services.Rules rulesService = services.rules;
        HashMap<String, Object> params = new HashMap<String, Object>();
        JsonObject response = rulesService.getList(params);
        System.out.println("response: " + response.toString());
    }
// User Module

    @Test
    public void create_user() throws IOException {
        com.ost.services.Users userService = services.users;
        HashMap<String, Object> params = new HashMap<String, Object>();
        JsonObject response = userService.create(params);
        System.out.println("response: " + response.toString());
    }

    @DataProvider(name = "user_id")
    public Object[][] getUserId() {
        return new Object[][]
                {
                        {user_bhavik_id},
                        {companyId},
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622"}, //different economy
                        {"avfbdf"},//alphabetic
                        {"^!@$$@$#%&*^&(*"}, //Special Char
                        {" "},
                        {null},
                        {"-2d971b59-1cda-4fb4-a022-8b2fa65c7622"},//Negative
                        {"   2d971b59-1cda-4fb4-a022-8b2fa65c7622"}, // Forward Space
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622  "}, // Backward Space
                        {"1234_133"}, // Underscore
                        {"1234-12344"}, // Hypen
                        {"12345.12345"}// Decimal Values


                };
    }

    @Test(dataProvider = "user_id")
    public void get_user(String userId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Users userService = services.users;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = userService.get(params);
        System.out.println("response: " + response.toString());
    }


    @DataProvider(name = "limit")
    public Object[][] getLimit() {
        return new Object[][]
                {
                        {20},
                        {0},
                        {1},
                        {26},
                        {"abc"}, //alphabetic
                        {"#%$^"}, //Special Character
                        {" "}, //Blank
                        {null},
                        {"-25"}, //Negative
                        {"122_123"},// Underscore
                        {"122-1233"}, //hypen
                        {"1234adff"},// /alphanumeric
                        {"25.425"} //Decimal Limit
                };
    }

    @Test(dataProvider = "limit")
    public void get_users_list(Object limit) throws IOException {
        com.ost.services.Users userService = services.users;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("limit", limit);
        JsonObject response = userService.getList(params);
        System.out.println("response: " + response.toString());
    }

    @Test()
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


    @Test
    public void create_device() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
        params.put("api_signer_address", "0x5F860598383868e8E8Ee0ffC5ADD92369Db37455");
        params.put("device_uuid", "593a967f-87bd-49a6-976c-52edf46c4df4");
        params.put("device_name", "Iphone S");
        JsonObject response = devicesService.create(params);
        System.out.println("response: " + response.toString());
    }


    @Test(dataProvider = "user_id")
    public void get_device_user_id(String userId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        params.put("device_address", device_bhavik_address);
        JsonObject response = devicesService.get(params);
        System.out.println("response: " + response.toString());
    }

// Device Module

    @Test(dataProvider = "device_address")
    public void get_device_deviceAdd(String deviceAddress) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("device_address", deviceAddress);
        JsonObject response = devicesService.get(params);
        System.out.println("response: " + response.toString());
    }

    @DataProvider(name = "device_address")
    public Object[][] getDeviceId() {
        return new Object[][]
                {
                        {device_bhavik_address},
                        {companyTH},
                        {user_bhavik_TH},
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622"}, // id
                        {"0x46336b3895b08af6fc3226f332f96e05cfd3f637"}, //different user's device address
                        {"avfbdf"},
                        {" "},
                        {null},
                        {"-2d971b59-1cda-4fb4-a022-8b2fa65c7622"}, //Negative
                        {"  2d971b59-1cda-4fb4-a022-8b2fa65c7622"}, //Forward Space
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622  "}, //Backward Space
                        {"@#$%^&*("}, // Special Character
                        {"fyfshsa134uuru"} //alphanumeric

                };
    }


    @Test(dataProvider = "user_id")
    public void get_devices_list_userId(String userId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        //params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
        //ArrayList<Object> addressesArray = new ArrayList<Object>();
        //addressesArray.add("0x5906ae461eb6283cf15b0257d3206e74d83a6bd4");
        //addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
        //params.put("addresses", addressesArray);
        //params.put("limit", "10");
        JsonObject response = devicesService.getList(params);
        System.out.println("response: " + response.toString());
    }


    @Test(dataProvider = "limit")
    public void get_devices_list_limit(Object limit) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Devices devicesService = services.devices;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        //params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
        //ArrayList<Object> addressesArray = new ArrayList<Object>();
        //addressesArray.add("0x5906ae461eb6283cf15b0257d3206e74d83a6bd4");
        //addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
        //params.put("addresses", addressesArray);
        params.put("limit", limit);
        JsonObject response = devicesService.getList(params);
        System.out.println("response: " + response.toString());
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
    }

// Device Manager Module

    @Test(dataProvider = "user_id")
    public void get_device_manager(String userId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = deviceManagersService.get(params);
        System.out.println("response: " + response.toString());
    }

// Session Module

    @DataProvider(name = "session_address")
    public Object[][] getSessionAdd() {
        return new Object[][]
                {
                        {session_bhavik_address},
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, //company's session address (different user's address)
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622"},
                        {"2d971b59-1cda-4fb4-a022-8b2fa65c7622"},
                        {" "},
                        {null},
                        {"-0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, // Negative
                        {"  0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, //forward Space
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331  "}, //Backward Space
                        {"0xea2d2-4141de586be3d-3ab0b425aaa7a-118ba3331"}, //Hypen
                        {"0xea2d241_41de586be3d3ab_0b425aaa7a118b_a3331"} //Underscore


                };
    }

    @Test(dataProvider = "session_address")
    public void get_session(String sessionAddress) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Sessions sessionsService = services.sessions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_bhavik_id);
        params.put("session_address", sessionAddress);
        JsonObject response = sessionsService.get(params);
        System.out.println("response: " + response.toString());
    }


    @Test
    public void get_sessions_list() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Sessions sessionsService = services.sessions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//params.put("limit", "10");
        JsonObject response = sessionsService.getList(params);
        System.out.println("response: " + response.toString());
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
    public void get_balance(String userId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Balance balanceService = services.balance;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        JsonObject response = balanceService.get(params);
        System.out.println("response: " + response.toString());
    }

    // Get Transaction Module

    @DataProvider(name = "get_transaction_user")
    public Object[][] getTransactionUser() {
        return new Object[][]
                {
                        {user_bhavik_id, transaction_bhavik_id},
                        {companyId, transaction_bhavik_id},
                        {user_bhavik_id, "920b7f72-becf-42ec-8921-af389e8bde64"},   //different user's transaction
                        {companyId, "5207d6db-e746-44ab-9cd3-aad0db1bd172"},  //different economy's tx
                        {" ", " "},
                        {null, null},
                        {"fsfdgd", "fgdgdhd"}, //Invalid
                        {"#@$@%^#^#&", "!@##$@%@^@^"}, //Special Character
                        {"12344858", "2345678"}, // Numeric
                        {"fdgdgd12344", "gdfgd23tyu"}, //alphanumeric
                        {"-23456sdfgh", "-1233hydf"} //Negartive
                };
    }


    @Test(dataProvider = "get_transaction_user")
    public void get_transaction(String userId, String transactionId) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", userId);
        params.put("transaction_id", transactionId);
        JsonObject response = transactionsService.get(params);
        System.out.println("response: " + response.toString());
    }
// Execute Transaction Module By Direct Method

    @Test(dataProvider = "user_id")
    public void execute_transaction_DT_userId(String user_Id) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = user_Id;
        String toAddress = directTransfer_TR;
        String parameter1 = "0xa31e988eebc89d0bc3e4a9a5463545ea534593e4";
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
        //params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
    }

// Token  Module


    @DataProvider(name = "token_rule")
    public Object[][] getTokenRule() {
        return new Object[][]
                {
                        {directTransfer_TR},
                        {pricer_TR},
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, //company's session address (different user's address)
                        {"0x7a27e897ab4b6579c5fe9880806b5a0b7c9e6f0d"}, //Different economy's Token rule
                        {" "},
                        {null},
                        {"-0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, // Negative
                        {"  0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, //Forward Space
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331   "}, //Backward Space
                        {"@#$%^&*(&*(*&"}, //Special Character
                        {"hdhdhdhd"},  //alphabetic
                        {"gsfd234567"}, //alphanumeric
                        {"_"}, //Underscore
                        {"-"} //hypen

                };
    }


    @Test(dataProvider = "token_rule")
    public void execute_transaction_DT_TokenRule(String token_rule_id) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
//        HashMap <String,Object> metaProperty = new HashMap<String,Object>();
//        metaProperty.put("name", "transaction_name"); // like, download
//        metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//        metaProperty.put("details", ""); // memo field to add additional info about the transaction

        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        String userId = companyId;
        String toAddress = token_rule_id;
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
        //params.put("meta_property", metaProperty);
        JsonObject response = transactionsService.execute(params);
        System.out.println("response: " + response.toString());
    }


    @DataProvider(name = "transfer_to")
    public Object[][] getTransferToAdd() {
        return new Object[][]
                {
                        {user_bhavik_TH},
                        {companyTH},
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, //unkown address
                        {"abcf12342mfk34"},
                        {" "},
                        {null},
                        {"-0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, // negative
                        {" 0xea2d24141de586be3d3ab0b425aaa7a118ba3331"}, //forward space
                        {"0xea2d24141de586be3d3ab0b425aaa7a118ba3331 "}, //Backward Space
                        {"gsgdhgdhfdh"}, //alphabetic
                        {"@#$%^&*(*&^"}, //Special Character
                        {"12345678fgsjsjs"}, //alphanumeric
                        {"_"}, //Underscore
                        {"-"} //Hypen


                };
    }


    @Test(dataProvider = "transfer_to")
    public void execute_transaction_DT_transferTo(String transfer_to) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
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
    }


    @DataProvider(name = "transfer_amount")
    public Object[][] getTransferAmount() {
        return new Object[][]
                {
                        {1},
                        {0},
                        {"10"}, //unkown address
                        {"abcf12342mfk34"},
                        {" "},
                        {null},
                        {"-112525"}, //Negative Value
                        {"fafsdgd"}, //Alphabetic
                        {"fsafsfd1235"}, //alphanumeric
                        {"   1000000000000000000"},// Amount with Space
                        {"1009393_515363"}, //underscore
                        {"123456789-2345678"}, //Hypen
                        {"!@#$%^&*()_)(*&"}, // Special Character
                        {"1234567.25252622626262"}, //Decimal
                        {"?"}
                };
    }

    @Test(dataProvider = "transfer_amount")
    public void execute_transaction_DT_amount(Object transfer_amount) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
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

        for (int i = 0; i < 10; i++) {
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
    }

    @DataProvider(name = "meta_name")
    public Object[][] getMetaName() {
        return new Object[][]
                {

                        {"download_IMP MaxLength-25"},  //Exact 25 characters
                        {"download IMP MaxLength 25 "}, //More than 25
                        {"t#$%#$^$%^d"}, // Special Character
                        {"https://ost.com/"},
                        {" "},
                        {null},
                        {"12345678"}, // Numeric Value
                        {"afsgs123445"}, //alphanumeric
                        {"-hshydhyffhbbh  1344"}, //Negative alhanumric value with space
                };

    }


    @Test(dataProvider = "meta_name")
    public void execute_transaction_DT_meta_name(String meta_name) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> metaProperty = new HashMap<String, Object>();
        metaProperty.put("name", meta_name); // like, download
        metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
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
    }


    @DataProvider(name = "meta_type")
    public Object[][] getmetaType() {
        return new Object[][]
                {

                        {"company_to_user"},
                        {"user_to_user"},
                        {"user_to_company"},
                        {"company to company"},
                        {"https://ost.com/"},
                        {" "},
                        {null},
                        {"@#$%^&*("}, // Special Character
                        {"asasdfghjklqwertyuioasdfghjklzxcvbnmasdfghjklqwertyui"} // more than 25 character

                };
    }


    @Test(dataProvider = "meta_type")
    public void execute_transaction_DT_meta_type(String meta_type) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
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
    }


    @DataProvider(name = "meta_details")
    public Object[][] getmetaDetails() {
        return new Object[][]
                {
                        /*Exact 125 chars*/  {"memo field to add additional info about the transaction IMP Max length 120 characters numbers alphabets spaces_-allowed__ VKL"},
/*more thna 125 chars*/ {"memo field to add additional info about the transaction IMP Max length 120 characters numbers alphabets spaces_-allowed__ VKLB"},// more than 120 chars
                        {"https://ost.com/"},
                        {" "},
                        {null},
                        {"@#$%^&*()(*!@#$%^&*"}, //Special Character
                        {"-ggdgdgd123456789"}, // Negative
                        {"12345678900987654321"} // Numeric Value

                };
    }


    @Test(dataProvider = "meta_details")
    public void execute_transaction_DT_meta_details(String meta_details) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
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
    }


    @Test(dataProvider = "user_id")
    public void get_transactions_list_userId(String user_id) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        JsonObject response = transactionsService.getList(params);
        System.out.println("response: " + response.toString());
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
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", companyId);
        params.put("status", statusArray);
        JsonObject response = transactionsService.getList(params);
        System.out.println("response: " + response.toString());
    }


    @Test(dataProvider = "limit")
    public void get_transactions_list_limit(Object limit) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {
        com.ost.services.Transactions transactionsService = services.transactions;
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", companyId);
        params.put("limit", limit);
        JsonObject response = transactionsService.getList(params);
        System.out.println("response: " + response.toString());
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

    @Test
    public void pay_transafer() throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {

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


}
