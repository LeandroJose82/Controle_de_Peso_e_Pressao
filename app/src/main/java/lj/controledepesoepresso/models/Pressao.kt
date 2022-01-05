package lj.controledepesoepresso.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tabela_pressao")
data class Pressao(
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    var pressaoSistolica : Int = 120,
    var pressaoDiastolica: Int =80,
    val date: Long = System.currentTimeMillis()
) {
    override fun toString(): String {
        return "$pressaoSistolica/$pressaoDiastolica"
    }
}
