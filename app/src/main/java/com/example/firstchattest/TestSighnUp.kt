package com.example.firstchattest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TestSighnUp : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtEmail:EditText
    private lateinit var edtpasswd: EditText
    private lateinit var BtnSign1: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_sighn_up)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.editname)
        edtEmail =findViewById(R.id.editEmail)
        edtpasswd = findViewById(R.id.editPasswd)
        BtnSign1 = findViewById(R.id.btnSignup1)
        BtnSign1.setOnClickListener {
            val name=edtName.text.toString()
            val Email= edtEmail.text.toString()
            val password=edtpasswd.text.toString()
            signUp(name,Email,password)
        }
    }
    private fun signUp(name:String,Email:String,password:String){
        //how the user will be created
        mAuth.createUserWithEmailAndPassword(Email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, jump to home activity
                    addUserToDataBase(name, Email,password, mAuth.currentUser?.uid!!)
                    val intent=Intent(this@TestSighnUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText( this@TestSighnUp, task.exception.toString(),Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDataBase(name: String, Email: String, password: String, uid:String) {
        mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,Email,uid))
    }
}