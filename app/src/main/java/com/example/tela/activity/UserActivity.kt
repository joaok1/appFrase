package com.example.tela.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tela.R
import com.example.tela.infra.SecurityPreferences
import com.example.tela.`object`.Constantes
import com.example.tela.databinding.ActivityUserMainBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    //Sempre configurar o binding isto definir qual tela será chamada
    private lateinit var binding: ActivityUserMainBinding

    //Este e o metodo principal desta activity, ela e sempre a primeira a ser iniciada
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)

        //Esconde a barra de navegação
        supportActionBar?.hide()
        //Eventos
        binding.buttomSaveName.setOnClickListener(this)

        setContentView(binding.root)

        //Sempre lebrar de mexer no GRANDLE e no arquivo manifest para definir qual tela irá iniciar primeiro

        verifyUserName()
    }


    private fun verifyUserName() {
        val name:String = SecurityPreferences(this).getString(Constantes.USER_NAME)
        if (!name.isEmpty()) {
            println(name)
            startActivity(Intent(this, MainActivity()::class.java))
        }
    }

    override fun onClick(view: View?) {
        if (view!!.id == R.id.buttom_save_name) {
            handleSave()
        }

    }

    private fun handleSave() {

        // Isso faz com que os dados seja privado somente do app.
//        val a: SharedPreferences = applicationContext.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

        val name = binding.editInputName.text.toString()
        //Instancia da classe criada, o construtor e passado direto
        SecurityPreferences(this).storeString(Constantes.USER_NAME, name)

        if (!name.isEmpty()) {
            //FAz com seja direcionado a nova pagina a activity se refere a tela
            //Os :: significa metodo referencia neste caso fala que a MainActivity() e uma classe java
            startActivity(Intent(this, MainActivity()::class.java))
            //existe o metodo finish() ele mata a aplicação caso tente voltar/ encerra a aplicação
        } else {
            //Faz com que exiba uma mensagem
            Toast.makeText(this, R.string.app_you_name_empty, Toast.LENGTH_SHORT).show()
        }
    }

}