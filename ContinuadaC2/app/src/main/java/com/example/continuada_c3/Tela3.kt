package com.example.continuada_c3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tela3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela3)


        val tvLista:LinearLayout = findViewById(R.id.layout_lista)

        val apiCachorro = ConexaoApiCachorros.criar()

        apiCachorro.get().enqueue(object : Callback<List<Cachorro>> { // retrofit2.Callback
            // onResponse é executando se chamada for feita com sucesso
            override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {
                // response.body() -> obtém o corpo da resposta
                response.body()?.forEach {
                    // criando uma nova TextView
                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - Preço: ${it.valprecoMedio}"
                    tvCachorro.setTextColor(Color.parseColor("#9911AA"))
                    // adicionando a nova TextView no LinearLayout
                    tvLista.addView(tvCachorro)
                }
            }

            // onFailure é executado se houver erro na chamada
            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
                Log.e("Erro", t.message!!)
            }

        })
    }
}