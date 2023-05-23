package com.example.aulaprojeto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.Toast
import com.example.aulaprojeto.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    /*Adicionando o Binding*/

    lateinit var binding: ActivityMainBinding
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Inflando o layout*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tratarLogin()

        binding.fab.setOnClickListener{
            novoItem()
        }

    /* Finalizndo o binding*/
    }

    /*Criando função para tratar o login*/

    fun tratarLogin(){

        /*Verificando usuario autenticado*/

        if(FirebaseAuth.getInstance().currentUser == null){
            val providerStatus = arrayListOf(AuthUI.IdConfig.EmailBuilder().build())
            val intent = AuthUI
                .getInstance()
                .createSingInIntentBuilder()
                .setAvailableProviders(providers)
                .build
            startActivityForResult(intent, 1)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == RESULT_OK){

            /*fazendo texto aparecer na tela*/
            Toast.makeText(this, "Autenticado", Toast.LENGTH_LONG).show()
        }
        else{
            finishAffinity()
        }
    }

    fun configurarBase(){
        FirebaseAuth.getInstance().currentUser?.let{
            database = FirebaseDatabase.getInstance().reference.child(it.uid)
        }
    }

    fun novoItem(){

        val editText = EditText(this)
        editText.hint = "Nome do item"

        AlertDialog.Builder(this)
            .setTitle("Novo Item")
            .setView(editText)
            .setPositiveButton("Inserir", null)
            .create()
            .show()
    }


}