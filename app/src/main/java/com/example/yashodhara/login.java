package com.example.yashodhara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class login extends AppCompatActivity {

    private OkHttpClient client;
    private ProgressDialog progressDialog;
    private static final String baseUrl = "https://www.erhmis.fhb.health.gov.lk/erhmis2356/api/";
    private static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new Database(this);
        progressDialog = new ProgressDialog(this);
        client = new OkHttpClient();
    }

    public void onLoginClick(View view) {
//        progressDialog.setMessage("Checking Credentials...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                boolean loginSuccess = loginToApp1("mobile-dev1", "AbcD#4321");
//            }
//        }).start();
//
//        //remove loader
//        progressDialog.dismiss();
////        if (loginSuccess){
////            goToHome();
////        }else{
////            Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show();
////        }

        loginToApp("mobile-dev1", "AbcD#4321");
    }

    private void goToHome() {
        Intent intent = new Intent(login.this, home.class);
        startActivity(intent);

    }

    private boolean loginToApp1(String username, String password) {

        // login
        String loginDetails = username + ":" + password;
        String encodedString = Base64.getEncoder().encodeToString(loginDetails.getBytes());
        Request request = new Request.Builder()
                .url(baseUrl + "me")
                .header("Authorization", "Basic " + encodedString)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // area details
            String res = Objects.requireNonNull(response.body()).string();
            Log.i("YYY", response.body().string());
            JSONObject obj = new JSONObject(res);
            JSONObject area = (JSONObject) obj.getJSONArray("organisationUnits").get(0);
            String areaId = area.getString("id");
            getAreaDetails1(areaId, encodedString);
            return true;
        } catch (Exception e) {
            Log.e("YYY",e.getMessage());
            return false;
        }
    }


    private void getAreaDetails1(String areaId, String authKey) throws IOException, JSONException {
        if (!areaId.isEmpty()) {
            Request request = new Request.Builder()
                    .url(baseUrl + "trackedEntityInstances.json?ou=" + areaId)
                    .header("Authorization", "Basic " + authKey)
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                        String areaDetails = Objects.requireNonNull(response.body()).string();
                        Log.i("YYY", areaDetails);
                        ArrayList<String> childList = new ArrayList<>();
                        JSONArray area_arr = new JSONObject(areaDetails).getJSONArray("trackedEntityInstances");
                        for (int i = 0; i < area_arr.length(); i++) {
                            JSONObject object = area_arr.getJSONObject(i);
                            childList.add(object.getString("trackedEntityInstance"));
                        }
                        Log.i("YYY", childList.toString());
                        for (String child: childList) {
                            getChildDetails1(child,authKey);
                        }
                        getProgramDetails1(areaId,authKey);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private void getChildDetails1(String childId, String authKey) throws IOException, JSONException {
        if (!childId.isEmpty()) {
            Request request = new Request.Builder()
                    .url(baseUrl + "trackedEntityInstances/" + childId)
                    .header("Authorization", "Basic " + authKey)
                    .get()
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                String childDetails = Objects.requireNonNull(response.body()).string();
                Log.i("YYY", childDetails);
                HashMap<String, String> childAttributes = new HashMap<>();
                JSONObject child_obj = new JSONObject(childDetails);
                String doe = child_obj.getString("created");

                JSONArray child_attr = child_obj.getJSONArray("attributes");
                for (int i = 0; i < child_attr.length(); i++) {
                    JSONObject object = child_attr.getJSONObject(i);
                    childAttributes.put(object.getString("attribute"), object.getString("value"));
                }
                Log.i("YYY", child_attr.toString());

                // save child details
                database.enterChildToChild(childAttributes, childId, true, doe);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private void getProgramDetails1(String areaId, String authKey) throws IOException, JSONException {

        HashMap<String, String> prog_keys = new HashMap<>();
        prog_keys.put("anthropometry", "hM6Yt9FQL0n");
//        prog_keys.put("other","iUgzznPsePB");
//        prog_keys.put("overweight","JsfNVX0hdq9");
//        prog_keys.put("stunt","lSSNwBMiwrK");
//        prog_keys.put("supplementary","tc6RsYbgGzm");
//        prog_keys.put("therapeutic","CoGsKgEG4O0");

        if (!areaId.isEmpty()) {
            Iterator entryIterator = prog_keys.entrySet().iterator();
            for (Map.Entry id : prog_keys.entrySet()) {
                String programId = (String) id.getValue();
                String programKey = (String) id.getKey();
                Request request = new Request.Builder()
                        .url(baseUrl + "trackedEntityInstances.json?ou=" + areaId + "&program=" + programId)
                        .header("Authorization", "Basic " + authKey)
                        .get()
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String progDetails = Objects.requireNonNull(response.body()).string();
                    Log.i("YYY", progDetails);
                    ArrayList<String> childList = new ArrayList<>();
                    JSONArray area_arr = new JSONObject(progDetails).getJSONArray("trackedEntityInstances");
                    for (int i = 0; i < area_arr.length(); i++) {
                        JSONObject object = area_arr.getJSONObject(i);
                        childList.add(object.getString("trackedEntityInstance"));
                    }
                    Log.i("YYY", childList.toString());
                    for (String child : childList) {
                        getChildProgramDetails1(child, areaId, programKey, authKey);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    private void getChildProgramDetails1(String childId, String areaId, String programId, String authKey) throws IOException, JSONException {
        if (!childId.isEmpty()) {
            Request request = new Request.Builder()
                    .url(baseUrl + "events.json?tei="+childId+"&program="+programId+"&ou="+areaId)
                    .header("Authorization", "Basic " + authKey)
                    .get()
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                String childProgramDetails = Objects.requireNonNull(response.body()).string();
                Log.i("YYY", childProgramDetails);
                JSONObject child_obj = new JSONObject(childProgramDetails);
                JSONArray prog_children = child_obj.getJSONArray("events");

                for (int i = 0; i < prog_children.length(); i++) {
                    HashMap<String, String> childProgAttributes = new HashMap<>();

                    JSONObject object = prog_children.getJSONObject(i);
                    String doe = object.getString("created");
                    JSONArray prog_child_updates = object.getJSONArray("dataValues");

                    String updatedDate = object.getString("lastUpdated");

                    for (int j = 0; j < prog_child_updates.length(); j++) {
                        JSONObject update = prog_child_updates.getJSONObject(0);
                        if (update != null) {
                            childProgAttributes.put(update.getString("dataElement"), update.getString("value"));
                        }
                    }
                    // save child details
                    boolean isSuccess = database.enterChildToProgram(childProgAttributes, programId, childId, true, doe, updatedDate);
                    if (isSuccess) {
                        runOnUiThread(() -> {
                            progressDialog.show();
                            progressDialog.setMessage("Successfully saved a child program data");
                        });
                    }

                }
                Log.i("YYY", childProgramDetails.toString());
                // all sync done
                // go home
            } catch (Exception e) {
                throw e;
            }
        }
    }

    private void loginToApp(String username, String password) {
        progressDialog.setMessage("Checking Credentials...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            String loginDetails = username + ":" + password;
            String encodedString = Base64.getEncoder().encodeToString(loginDetails.getBytes());


            Request request = new Request.Builder()
                    .url(baseUrl + "me")
                    .header("Authorization", "Basic " + encodedString)
                    .get()
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                public void onResponse(Call call, Response response) {
                    try {
                        String res = Objects.requireNonNull(response.body()).string();
                        Log.i("YYY", res);
                        JSONObject obj = new JSONObject(res);
                        JSONObject area = (JSONObject) obj.getJSONArray("organisationUnits").get(0);
                        String areaId = area.getString("id");
                        getAreaDetails(areaId, encodedString);
//                        runOnUiThread(() -> {
//                            progressDialog.dismiss();
//                        });
                    } catch (Exception e) {
                        e.printStackTrace();

                        runOnUiThread(() -> {
                            progressDialog.dismiss();
                        });
                    }
                }

                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> {
                        progressDialog.dismiss();
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
        }
    }

    private void getAreaDetails(String areaId, String authKey) {
        if (!areaId.isEmpty()) {
            Request areaDetailsRequest = new Request.Builder()
                    .url(baseUrl + "trackedEntityInstances.json?ou=" + areaId)
                    .header("Authorization", "Basic " + authKey)
                    .get()
                    .build();
            Call areaDetailsCall = client.newCall(areaDetailsRequest);
            areaDetailsCall.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response areaDetailsResponse) throws IOException {
                    try {
                        String areaDetails = Objects.requireNonNull(areaDetailsResponse.body()).string();
                        Log.i("YYY", areaDetails);
                        ArrayList<String> childList = new ArrayList<>();
                        JSONArray area_arr = new JSONObject(areaDetails).getJSONArray("trackedEntityInstances");
                        for (int i = 0; i < area_arr.length(); i++) {
                            JSONObject object = area_arr.getJSONObject(i);
                            childList.add(object.getString("trackedEntityInstance"));
                        }
                        Log.i("YYY", childList.toString());
                        for (String child:
                             childList) {
                            getChildDetails(child,authKey);
                        }
                        getProgramDetails(areaId,authKey);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(() -> {
                        progressDialog.dismiss();
                    });
                }

            });
        }
    }

    private void getChildDetails(String childId, String authKey){
        if (!childId.isEmpty()) {
            Request areaDetailsRequest = new Request.Builder()
                    .url(baseUrl + "trackedEntityInstances/" + childId)
                    .header("Authorization", "Basic " + authKey)
                    .get()
                    .build();
            Call areaDetailsCall = client.newCall(areaDetailsRequest);
            areaDetailsCall.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response areaDetailsResponse) throws IOException {
                    try {
                        String childDetails = Objects.requireNonNull(areaDetailsResponse.body()).string();
                        Log.i("YYY", childDetails);
                        HashMap<String,String> childAttributes = new HashMap<>();
                        JSONObject child_obj = new JSONObject(childDetails);
                        String doe=child_obj.getString("created");

                        JSONArray child_attr = child_obj.getJSONArray("attributes");
                        for (int i = 0; i < child_attr.length(); i++) {
                            JSONObject object = child_attr.getJSONObject(i);
                            childAttributes.put(object.getString("attribute"),object.getString("value"));
                        }
                        Log.i("YYY", childId+" : childAttr :"+childAttributes.toString());

                        // save child details
                        database.enterChildToChild(childAttributes, childId, true, doe);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(() -> {
                        progressDialog.dismiss();
                    });
                }

            });
        }
    }

    private void getProgramDetails(String areaId, String authKey){

        HashMap<String, String> prog_keys= new HashMap<>();
        prog_keys.put("anthropometry","hM6Yt9FQL0n");
//        prog_keys.put("other","iUgzznPsePB");
//        prog_keys.put("overweight","JsfNVX0hdq9");
//        prog_keys.put("stunt","lSSNwBMiwrK");
//        prog_keys.put("supplementary","tc6RsYbgGzm");
//        prog_keys.put("therapeutic","CoGsKgEG4O0");

        if (!areaId.isEmpty()) {
            Iterator entryIterator = prog_keys.entrySet().iterator();
            for (Map.Entry id : prog_keys.entrySet()){
                String programId = (String) id.getValue();
                String programKey =(String) id.getKey();
                Request progDetailsRequest = new Request.Builder()
                        .url(baseUrl + "trackedEntityInstances.json?ou=" + areaId+"&program="+programId)
                        .header("Authorization", "Basic " + authKey)
                        .get()
                        .build();
                Call programDetailsCall = client.newCall(progDetailsRequest);
                programDetailsCall.enqueue(new Callback() {
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response areaDetailsResponse) throws IOException {
                        try {
                            String progDetails = Objects.requireNonNull(areaDetailsResponse.body()).string();
                            Log.i("YYY", progDetails);
                            ArrayList<String> childList = new ArrayList<>();
                            JSONArray area_arr = new JSONObject(progDetails).getJSONArray("trackedEntityInstances");
                            for (int i = 0; i < area_arr.length(); i++) {
                                JSONObject object = area_arr.getJSONObject(i);
                                childList.add(object.getString("trackedEntityInstance"));
                            }
                            Log.i("YYY", childList.toString());
                            for (String child: childList) {
                                getChildProgramDetails(child,areaId,programId, programKey,authKey);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        runOnUiThread(() -> {
                            progressDialog.dismiss();
                        });
                    }

                });
            }

        }else {

        }
    }

    private void getChildProgramDetails(String childId, String areaId, String programId, String programKey,String authKey){
        if (!childId.isEmpty()) {
            Request childProgramDetails = new Request.Builder()
                    .url(baseUrl + "events.json?tei="+childId+"&program="+programId+"&ou="+areaId)
                    .header("Authorization", "Basic " + authKey)
                    .get()
                    .build();
            Call childProgramDetailsCall = client.newCall(childProgramDetails);
            childProgramDetailsCall.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response areaDetailsResponse) throws IOException {
                    try {
                        String childProgramDetails = Objects.requireNonNull(areaDetailsResponse.body()).string();
                        Log.i("YYY", childProgramDetails);
                        JSONObject child_obj = new JSONObject(childProgramDetails);
                        JSONArray prog_children = child_obj.getJSONArray("events");
                        if (prog_children!=null) {
                            for (int i = 0; i < prog_children.length(); i++) {
                                HashMap<String, String> childProgAttributes = new HashMap<>();

                                JSONObject object = prog_children.getJSONObject(i);
                                String doe = object.getString("created");
                                JSONArray prog_child_updates = object.getJSONArray("dataValues");

                                String updatedDate = object.getString("lastUpdated");

                                if (prog_child_updates!=null) {

                                    for (int j = 0; j < prog_child_updates.length(); j++) {
                                        JSONObject update = prog_child_updates.getJSONObject(0);
                                        if (update != null) {
                                            childProgAttributes.put(update.getString("dataElement"), update.getString("value"));
                                        }
                                    }

                                    // save child details
                                    boolean isSuccess = database.enterChildToProgram(childProgAttributes, programKey, childId,true,doe,updatedDate);

                                    if (isSuccess) {
                                        runOnUiThread(() -> {
                                            progressDialog.show();
                                            progressDialog.setMessage("Successfully saved a child program data");
                                        });
                                    }
                                }
                            }
                            Log.i("YYY", childProgramDetails.toString());
                        }
                        runOnUiThread(() -> {
                            progressDialog.show();
                            progressDialog.setMessage("Synced data successfully!");
                        });
                        database.readSingleChildData(childId, Constants.T_CHILD, Constants.CHILD_ATTR);
                        goToHome();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    runOnUiThread(() -> {
                        progressDialog.dismiss();
                    });
                }

            });
        }
    }

}