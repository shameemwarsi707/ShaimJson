package com.shaim.shaimjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerAdapter recyclerAdapter;
    ArrayList<ContactModel> contactModels;
    ProgressDialog progressDialog;

    private static final String JSON_URL =
            "https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.reView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadHeroList();
    }
    private void loadHeroList(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Essaa"+response, Toast.LENGTH_LONG).show();

                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            Log.d("Shameem","Data>>"+response);
                            // JSONObject jsonObject=new JSONObject(response);
                            contactModels=new ArrayList<>();
                            for (int i=0;i<=jsonArray.length();i++){
                                ContactModel dataModel=new ContactModel();
                                JSONObject dataObj=jsonArray.getJSONObject(i);
                                dataModel.setName(dataObj.getString("name"));
                                dataModel.setEmail(dataObj.getString("email"));
                                JSONObject addresObj=dataObj.getJSONObject("address");
                                dataModel.setStreet(addresObj.getString("street"));
                                contactModels.add(dataModel);

                                recyclerAdapter=new MyRecyclerAdapter(MainActivity.this,contactModels);
                                LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(recyclerAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Shameem"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}