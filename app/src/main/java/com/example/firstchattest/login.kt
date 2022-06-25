package com.example.firstchattest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class login<button> : AppCompatActivity() {
    private lateinit var edtlogin:EditText
    private lateinit var edtpasswd:EditText
    private lateinit var Btnlog: Button
    private lateinit var BtnSign: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        edtlogin = findViewById(R.id.editingLogin)
        edtpasswd = findViewById(R.id.editingPassword)
        Btnlog = findViewById(R.id.btnLogin)
        BtnSign = findViewById(R.id.btnSignup)

        BtnSign.setOnClickListener {
            val intent = Intent(this,TestSighnUp::class.java)
            startActivity(intent)
        }

        Btnlog.setOnClickListener {
            val log=edtlogin.text.toString()
          val password=edtpasswd.text.toString()
           loggin(log,password)

        }

    }
    private fun loggin(log: String,password: String){
        //login for logging user
        mAuth.signInWithEmailAndPassword(log, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, loggin a user
                    val intent=Intent(this@login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else
                    Toast.makeText(this@login,"this user do not exist", Toast.LENGTH_SHORT).show()
                // If sign in fails, erreur mssg
            }

    }




}