package com.example.aulaprojeto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aulaprojeto.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    /*Adicionando o Binding*/

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Inflando o layout*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tratarLogin()

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
}