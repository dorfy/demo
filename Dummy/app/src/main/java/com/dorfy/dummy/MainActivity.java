package com.dorfy.dummy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutMain;
    private ListView listView;
    private Button mButton,btn;
    String[] str = {"accept", "yes"};
    ArrayList<String> list = new ArrayList<String>();
    ListView mListView;
    ArrayAdapter<String> adapter;
    private LeaseClassification leaseClassification;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init(){
        linearLayoutMain = (LinearLayout) findViewById(R.id.linearLayout);
        listView = (ListView) findViewById(R.id.listView);
//        list.add("List 1");

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


        for (int i = 0; i < str.length; i++) {

            mButton = new Button(this);
            mButton.setId(i);
            mButton.setText(str[i]);
            mButton.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    handleButtonClicks(0);

                }
            });
            linearLayoutMain.addView(mButton);
//            sendRequest();


            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("lease.txt"), "UTF-8"));

                // do reading, usually loop until end of file reading
                String mLine;
                while ((mLine = reader.readLine()) != null) {
                    //process line
                    Log.v("MyLog",mLine);
                }
            } catch (IOException e) {
                //log the exception
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        //log the exception
                    }
                }
            }
        }
    }

    private void handleButtonClicks(int questionId){
        addQuestionToList("");
    }

    private void addQuestionToList(String question){
        list.add("List");
        adapter.notifyDataSetChanged();
    }

    private void sendRequest(){


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, "/Users/dorao/AndroidStudioProjects/Dummy/app/src/main/java/com/dorfy/dummy/SurveyMock.json", null,

                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("&&&&&",""+response);
                        try {
                            JSONObject jsonArray;
                            jsonArray = response;
                            ArrayList<LeaseClassification> arr = new ArrayList<LeaseClassification>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                LeaseClassification jsonModelObject = new LeaseClassification();
                                JSONObject student = jsonArray.getJSONObject(String.valueOf(i));
                            }

                        } catch (JSONException e) {
                            Log.e("ErrorMessage","welsh");

                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        mRequestQueue.add(jsonObjectRequest);

    }
}





