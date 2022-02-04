package lj.controledepesoepresso.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import lj.controledepesoepresso.R
import lj.controledepesoepresso.fragments.PesoFragment
import lj.controledepesoepresso.fragments.PressaoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar: BottomNavigationView = findViewById(R.id.bottom_Bar)
        val pesoFragment = PesoFragment()
        val pressaoFragment = PressaoFragment()

        bottomBar.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()

            when (it.itemId) {
                R.id.pesoMenu -> {
                    transaction.replace(R.id.mainActivityContainer, pesoFragment).commit()
                }

                else -> {
                    transaction.replace(R.id.mainActivityContainer, pressaoFragment, "bundle").commit()
                }
            }
            true
        }

    }
}






