package com.example.visitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button b1,b2;
    String getFname,getLname,getPurp,getWhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        et1=(EditText) findViewById(R.id.firstname);
        et2=(EditText) findViewById(R.id.lastname);
        et3=(EditText) findViewById(R.id.purp);
        et4=(EditText) findViewById(R.id.whomeet);
        b1=(Button) findViewById(R.id.subbut);
        b2=(Button) findViewById(R.id.back1but);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFname=et1.getText().toString();
                getLname=et2.getText().toString();
                getPurp=et3.getText().toString();
                getWhom=et4.getText().toString();
                if(getFname.isEmpty()||getLname.isEmpty()||getPurp.isEmpty()||getWhom.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"All the fields are mandatory!",Toast.LENGTH_LONG).show();
                }
                else {
                    callApi();
                }
                
            }

            private void callApi() {
                String apiUrl="https://log-app-demo-api.onrender.com/addvisitor";
                JSONObject data=new JSONObject();
                try {
                    data.put("firstname",getFname);
                    data.put("lastname",getLname);
                    data.put("purpose",getPurp);
                    data.put("whomToMeet",getWhom);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest request=new JsonObjectRequest(
                        Request.Method.POST,
                        apiUrl,
                        data,
                        response -> Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_LONG).show(),
                        error -> Toast.makeText(getApplicationContext(),"Error Occured!",Toast.LENGTH_LONG).show()
                );
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                queue.add(request);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}