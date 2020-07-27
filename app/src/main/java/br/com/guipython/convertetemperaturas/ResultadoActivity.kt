package br.com.guipython.convertetemperaturas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado.*

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        val res = intent.getStringExtra("temp_f").toFloat()
        val escala =  intent.getStringExtra("Escala")

        txvResultado.text = "%.2f $escala".format(res)
    }

}
