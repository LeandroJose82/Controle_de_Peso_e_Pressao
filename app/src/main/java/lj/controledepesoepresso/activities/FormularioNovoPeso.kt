package lj.controledepesoepresso.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Peso

class FormularioNovoPeso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_novo_peso)

        val btnHome: Button = findViewById(R.id.retornarHome)
        val btnSalvarNovoPeso : Button = findViewById(R.id.salvarNovoPeso)
        val txtEntradaNovoPeso : TextView = findViewById(R.id.entradaNovoPeso)

        val db =   Room.databaseBuilder(
            this,
            ControleDatabase::class.java,
            "controle-database"
        ).allowMainThreadQueries().build()


        btnSalvarNovoPeso.setOnClickListener {
           val verificarPeso =  txtEntradaNovoPeso.text
            if (verificarPeso==null || verificarPeso.isEmpty()){
                    Toast.makeText(this,R.string.insiraPeso,Toast.LENGTH_SHORT).show()
            }else{
                val novoPeso = Peso(peso=txtEntradaNovoPeso.text.toString().toDouble())
                db.controleDAO().inserirPeso(novoPeso)
                retornarHome()
            }


        }

        btnHome.setOnClickListener {
            retornarHome()
        }


    }

    private fun retornarHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}