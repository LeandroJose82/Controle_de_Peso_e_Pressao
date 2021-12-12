package lj.controledepesoepresso.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import lj.controledepesoepresso.models.Pressao

@Dao
interface PressaoDAO {

    @Insert
    suspend fun inserirPressao (pressao: Pressao)

    @Delete
    suspend fun deletarPeso (pressao: Pressao)

    @Query("SELECT pressaoSistolica FROM tabela_pressao WHERE date= (select MAX(date) from tabela_pressao ) ")
    suspend fun pressaoSistolicaMaisRecente():Int

    @Query("SELECT pressaoDiastolica FROM tabela_pressao WHERE date= (select MAX(date) from tabela_pressao ) ")
    suspend fun pressaoDiastolicaMaisRecente():Int

}