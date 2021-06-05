package com.smartmobileproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;
import com.smartmobileproject.function.JsonParsing;

public class ShareActivity extends AppCompatActivity {

    private EditText et_email;
    private Button btn_email;
    String email, shared_email;
    boolean response = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        et_email = findViewById(R.id.et_email);
        btn_email = findViewById(R.id.btn_email);

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shared_email = et_email.getText().toString();

                if(response = true) {
                    Intent intent = new Intent(ShareActivity.this, MainActivity.class);
                    intent.putExtra("email", email);//파싱한 값을 넘겨줌
                    intent.putExtra("shared_email", shared_email);
                    ShareActivity.this.startActivity(intent);
                    Toast.makeText(ShareActivity.this, "공유대상이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ShareActivity.this, "오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void Upload(String email, String shared_email){

        String serverUrl = "https://phpproject-cparr.run.goorm.io/Kakaouser.php";

        SimpleMultiPartRequest smpr = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("hellosuccess", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hellofail", String.valueOf(error));

            }
        });

        smpr.addStringParam("email",email);
        smpr.addStringParam("shared_email",email);
        Log.d("email",email);
        Log.d("shared_email", shared_email);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.d("smpr", String.valueOf(smpr));
        requestQueue.add(smpr);
    }
}