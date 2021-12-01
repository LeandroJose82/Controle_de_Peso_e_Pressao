package lj.controledepesoepresso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FormularioNovoPeso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_novo_peso)

        val btnHome : Button = findViewById(R.id.retornarHome)

        btnHome.setOnClickListener{
            val intent = Intent (this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}