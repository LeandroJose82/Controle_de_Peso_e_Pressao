package lj.controledepesoepresso.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import lj.controledepesoepresso.R
import lj.controledepesoepresso.fragments.PesoFragment
import lj.controledepesoepresso.fragments.PressaoFragment

class MainActivity : AppCompatActivity() {


    private val TELA_INICIAL = "Tela_inicial"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar: BottomNavigationView = findViewById(R.id.bottom_Bar)
        val pesoFragment = PesoFragment()
        val pressaoFragment = PressaoFragment()
        val sharedPreferences = getSharedPreferences(TELA_INICIAL, Context.MODE_PRIVATE)

        selecionarTelaInicial(sharedPreferences, pesoFragment, pressaoFragment)


        bottomBar.setOnItemSelectedListener {

            val transaction = supportFragmentManager.beginTransaction()

            when (it.itemId) {
                R.id.pesoMenu -> {
                    sharedPreferences.edit().apply {
                        putInt(TELA_INICIAL, R.string.peso).apply()
                    }
                    transaction.replace(R.id.mainActivityContainer, pesoFragment).commit()
                }
                else -> {
                    sharedPreferences.edit().apply {
                        putInt(TELA_INICIAL, R.string.pressao).apply()
                    }
                    transaction.replace(R.id.mainActivityContainer, pressaoFragment).commit()
                }
            }
            true
        }

    }

    private fun selecionarTelaInicial(
        sharedPreferences: SharedPreferences,
        pesoFragment: PesoFragment,
        pressaoFragment: PressaoFragment
    ) {
        val transaction = supportFragmentManager.beginTransaction()

        if (sharedPreferences.getInt(TELA_INICIAL, R.string.peso).equals(R.string.peso)) {
            transaction.replace(R.id.mainActivityContainer, pesoFragment).commit()
        } else {
            transaction.replace(R.id.mainActivityContainer, pressaoFragment).commit()
        }
    }

}







