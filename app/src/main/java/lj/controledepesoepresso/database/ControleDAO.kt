package lj.controledepesoepresso.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Room
import lj.controledepesoepresso.models.Peso

@Dao
interface ControleDAO {

        @Insert
        fun inserirPeso (peso: Peso)

        @Delete
        fun deletarPeso (peso: Peso)

        //Query para selecionar o registro mais recente de peso
//        @Query("SELECT peso FROM controle-database WHERE MAX(date) ")
//        fun pesoMaisRecente ()
}