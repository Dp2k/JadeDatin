package com.example.jade

import android.content.Intent
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
    private var userUID: String = ""
    private var userGender: String = ""
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

                    if(response.has("message")){
                        Toast.makeText( this,"Your username or password is not correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText( this,"Wellcome", Toast.LENGTH_SHORT).show();
                        userUID = response.get("uid").toString()
                        userGender = response.get("gender").toString()
                    }

                },{ error ->
                    error.printStackTrace()
            })
            VolleySingleton.getInstance(applicationContext).addToRequestQueue(jsonObjectRequest)
        }

    }

    private fun gotoLogin(){
        val i = Intent(this, LoggedActivity::class.java)
        i.putExtra("personaje", 2)
        startActivity(i)
        finish()
    }
}