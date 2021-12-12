package lj.controledepesoepresso.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tabela_peso")
data class Peso(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    var peso: Double=0.0,
    val date: Long = System.currentTimeMillis()
        )