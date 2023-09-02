package com.example.tela.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.tela.R
import com.example.tela.data.Mock
import com.example.tela.infra.SecurityPreferences
import com.example.tela.`object`.Constantes
import com.example.tela.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Sempre lebrar de mexer no GRANDLE

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Esconde a barra de navegação
        supportActionBar?.hide()
        //Eventos
        binding.buttomFrase.setOnClickListener(this)
        binding.buttomReturn.setOnClickListener(this)
        binding.infinity.setOnClickListener(this)
        binding.sentimento.setOnClickListener(this)
        binding.sol.setOnClickListener(this)
        val name:String = SecurityPreferences(this).getString(Constantes.USER_NAME)
        handleUserName(name)

    }
    var categoryId:Int? = null
    private var mock: Mock = Mock()
    private fun handleUserName(name: String) {
      binding.textNameUser.text = "Olá, $name!"
    }

    override fun onClick(view: View?) {
        if (view!!.id == R.id.buttom_frase) {
            binding.tagStateDescription.text = mock.number()
        } else if(view!!.id == R.id.buttom_return) {
            handleReturn()
        } else if (view!!.id in listOf(R.id.infinity,R.id.sentimento,R.id.sol)) {
            handleFilter(view!!.id )
        }
    }

    private fun handleFilter(id: Int) {

        when {
            id.equals(R.id.infinity) -> {
                binding.infinity.setColorFilter(R.color.white)
                Constantes.FILTER.INFINITY
                binding.sol.clearColorFilter()
                binding.sentimento.clearColorFilter()

            }
            id.equals(R.id.sol) -> {
                binding.sol.setColorFilter(R.color.white)
                Constantes.FILTER.SUNNY
                binding.sentimento.clearColorFilter()
                binding.infinity.clearColorFilter()
            }
            id.equals(R.id.sentimento) -> {
                binding.sentimento.setColorFilter(R.color.white)
                Constantes.FILTER.SENTIMENT
                binding.infinity.clearColorFilter()
                binding.sol.clearColorFilter()
            }
        }
    }


    private fun handleReturn() {
        //Limpa o store
        SecurityPreferences(this).storeString(Constantes.USER_NAME, "")
        //Redireciona para para a primeira tela
        startActivity(Intent(this, UserActivity::class.java))
    }

}