package lj.controledepesoepresso.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNovoPeso:Button = findViewById(R.id.btn_AdicionarNovoPeso)
        val textoPesoAtual : TextView = findViewById((R.id.pesoTelaPrincipal))

        val db =   Room.databaseBuilder(
            this,
            ControleDatabase::class.java,
            "controle-database"
        ).allowMainThreadQueries().build()


            val pesoRecente : String = db.controleDAO().pesoMaisRecente().toString()
            textoPesoAtual.setText("$pesoRecente KG")


        btnNovoPeso.setOnClickListener {
            val intent =Intent (this, FormularioNovoPeso::class.java)
            startActivity(intent)
        }

    }

}

