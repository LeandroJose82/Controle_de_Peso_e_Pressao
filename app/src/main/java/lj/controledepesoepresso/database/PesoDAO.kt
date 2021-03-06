package lj.controledepesoepresso.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import lj.controledepesoepresso.models.Peso

@Dao
interface PesoDAO {

        @Insert
        suspend fun inserirPeso (peso: Peso)

        @Delete
        suspend fun deletarPeso (peso: Peso)

        //Selecionar o registro mais recente de peso
        @Query("SELECT peso FROM tabela_peso WHERE date= (select MAX(date) from tabela_peso ) ")
        suspend fun pesoMaisRecente():Double
}