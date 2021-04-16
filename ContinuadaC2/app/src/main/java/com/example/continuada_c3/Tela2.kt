package com.example.continuada_c3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Tela2 : AppCompatActivity() {

    lateinit var swIndicado:Switch
    lateinit var tvCadastrado:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        swIndicado = findViewById(R.id.sw_Indicado)
        tvCadastrado = findViewById(R.id.tv_cadastrado)
    }

    fun cadastrarCachorro(view: View) {
        val apiCachorro = ConexaoApiCachorros.criar()

        val etRaca:EditText = findViewById(R.id.et_raca)
        val etPreco:EditText = findViewById(R.id.et_preco)

        val raca:String = etRaca.text.toString()
        val preco:Int = "0${etPreco.text}".toInt()

       val novoCachorro = Cachorro(0,raca,preco,false)

        apiCachorro.post(novoCachorro).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if(response.code() == 201){
                    val cachorroCriado = response.body()
                    tvCadastrado.text = "Cão cadastrado com sucesso"
                    val ivFoto: ImageView = findViewById(R.id.iv_cachorroFeliz)
                    ivFoto.setImageResource(R.drawable.cachorro_feliz)
                }else{
                    tvCadastrado.text = "Falha ao criar o Cachorro: ${response.errorBody()!!}"
                }
            }
            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}