package com.example.tela.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    // Isso faz com que os dados seja privado somente do app.
   private val security: SharedPreferences = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key:String, valor:String) {
        security.edit().putString(key,valor).apply()
    }

    fun getString(key:String) : String {
      return security.getString(key, "") ?: ""
    }
}