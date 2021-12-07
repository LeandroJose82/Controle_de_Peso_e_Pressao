package lj.controledepesoepresso.database

import androidx.room.Database
import androidx.room.RoomDatabase
import lj.controledepesoepresso.models.Peso

@Database (entities = arrayOf(Peso::class), version = 1, exportSchema = false)
abstract class ControleDatabase : RoomDatabase() {
    abstract fun controleDAO(): ControleDAO

//        companion object {
//            val db = fun(context: Context) {
//                Room.databaseBuilder(
//                    context,
//                    ControleDatabase::class.java,
//                    "controle-database"
//                ).allowMainThreadQueries().build()
//            }
//        }

    }




