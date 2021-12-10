package lj.controledepesoepresso.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import lj.controledepesoepresso.models.Peso

@Database(entities = [Peso::class], version = 1, exportSchema = false)
abstract class ControleDatabase : RoomDatabase() {
    abstract fun controleDAO(): ControleDAO


    companion object {

        @Volatile
        private var INSTANCE: ControleDatabase? = null

        fun getDatabase(context: Context): ControleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ControleDatabase::class.java,
                    "controle-database"
                ).build()
                INSTANCE = instance
                instance
            }

        }


    }
}
        






