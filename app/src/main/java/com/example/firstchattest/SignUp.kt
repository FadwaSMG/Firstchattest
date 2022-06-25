package com.example.firstchattest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtlogin:EditText
    private lateinit var edtpasswd: EditText
    private lateinit var BtnSign: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edtName = findViewById(R.id.editname)
        edtlogin =findViewById(R.id.editingLogin)
        edtpasswd = findViewById(R.id.editingPassword)
        BtnSign = findViewById(R.id.btnSignup)

        BtnSign.setOnClickListener {
            val login= edtlogin.text.toString()
          val password=edtpasswd.text.toString()
           signUp(login,password)
        }
    }
    private fun signUp(login:String,password:String){
        //how the user will be created
        mAuth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, jump to home activity
                    val intent=Intent(this@SignUp,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText( this@SignUp, "Erreur!!!!!",Toast.LENGTH_SHORT).show()
                }
            }
    }
}