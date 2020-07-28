package br.com.guipython.convertetemperaturas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_operacao.*

class OperacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operacao)

        // Spinner Configurado
        val escalas = arrayListOf<String>(
            "Selecione uma Escala:",
            "Celsius",
            "Kelvin",
            "Fahrenheit",
            "Rankine"
        )

        val spnEscala_adapter = ArrayAdapter(
            this@OperacaoActivity,
            android.R.layout.simple_spinner_dropdown_item,
            escalas
        )



        spnEscala.adapter = spnEscala_adapter
        spnEscalaRef.adapter = spnEscala_adapter

        btnConverter.setOnClickListener {
            val temperatura = edtTemperatura.text.toString().trim()
            val escala_ref_trans = spnEscalaRef.selectedItem.toString() + " " + spnEscala.selectedItem.toString()


            if (temperatura.isEmpty() || spnEscala.selectedItem == "Selecione uma Escala:" || spnEscalaRef.selectedItem == "Selecione uma Escala:") {
                AlertDialog.Builder(this@OperacaoActivity).setTitle("Erro")
                    .setMessage("Preencha todos os campos.")
                    .setPositiveButton("Ok") {_, _-> }.setCancelable(false).create().show() }

            else if ( spnEscala.selectedItem == spnEscalaRef.selectedItem ){
                AlertDialog.Builder(this@OperacaoActivity).setTitle("Erro de Cálculo").
                setMessage("Selecione escalas diferentes.").
                setPositiveButton("OK"){_, _-> }.setCancelable(false).create().show() }


            else{
                val temperatura_convert = temperatura.toDouble()
                val temp_f = when (escala_ref_trans) {
                "Celsius Kelvin" -> temperatura_convert + 273
                "Celsius Fahrenheit" -> (temperatura_convert * 1.8) + 32
                "Celsius Rankine" -> temperatura_convert * 1.8 + 491.67
                "Kelvin Celsius" -> temperatura_convert - 273.15
                "Kelvin Fahrenheit" -> (temperatura_convert - 273.15) * 1.8 + 32
                "Kelvin Rankine" -> temperatura_convert * 1.8
                "Rankine Celsius" -> (temperatura_convert  - 491.67) / 1.8
                "Rankine Fahrenheit" -> temperatura_convert - 459.67
                "Rankine Kelvin" -> temperatura_convert  / 1.8
                "Fahrenheit Celsius" -> (temperatura_convert - 32) /1.8
                "Fahrenheit Rankine" -> temperatura_convert + 459.67
                "Fahrenheit Kelvin" -> (temperatura_convert - 32) / 1.8 + 273.15
                else -> "Sem Solucao"
            }

                startActivity(Intent(this@OperacaoActivity, ResultadoActivity::class.java).apply {
                    putExtra("temp_f", temp_f.toString())
                    putExtra("Escala", spnEscala.selectedItem.toString())})
                edtTemperatura.text.clear()
                spnEscala.setSelection(0)
                spnEscalaRef.setSelection(0)
            }
        }
    }
}
