package br.com.guipython.convertetemperaturas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            // Funcao Lambda
            startActivity(Intent(this@MainActivity,OperacaoActivity::class.java))
            // Fecha a Pagina de splash
            finish()
        },2000)
    }
}
