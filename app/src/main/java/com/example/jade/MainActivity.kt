package com.example.jade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Request
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var btnSubmit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userEmail = findViewById(R.id.userEmail)
        userPassword = findViewById(R.id.userPassword)
        btnSubmit = findViewById(R.id.btnSubmit)


        val url = "http://guesswho.danielpacheco.com.mx:8080"
        var jsonObjectRequest : JsonObjectRequest

        btnSubmit.setOnClickListener{
            val jsonObject =JSONObject();
            jsonObject.put("email",userEmail.text)
            println("hoal")
            jsonObject.put("password",userPassword.text)

            jsonObjectRequest = JsonObjectRequest(url+"/login",jsonObject,{
                    response ->
                    Toast.makeText( this,response.toString(), Toast.LENGTH_SHORT).show();
                },{ error ->
                    error.printStackTrace()
            })
            VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonObjectRequest)
        }

    }
}