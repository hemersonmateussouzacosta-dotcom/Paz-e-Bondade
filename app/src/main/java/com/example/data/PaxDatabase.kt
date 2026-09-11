package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.dao.PaxDao
import com.example.data.model.AudioPackage
import com.example.data.model.FranciscanSaint
import com.example.data.model.LiturgicalDay1962
import com.example.data.model.ReminderSetting
import com.example.data.model.SeraphicPrayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        FranciscanSaint::class,
        SeraphicPrayer::class,
        LiturgicalDay1962::class,
        ReminderSetting::class,
        AudioPackage::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PaxDatabase : RoomDatabase() {

    abstract fun paxDao(): PaxDao

    companion object {
        @Volatile
        private var INSTANCE: PaxDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PaxDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PaxDatabase::class.java,
                    "pax_et_bonum.db"
                )
                    .addCallback(PaxDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class PaxDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.paxDao())
                }
            }
        }

        suspend fun populateDatabase(paxDao: PaxDao) {
            paxDao.insertSaints(PaxInitialData.getInitialSaints())
            paxDao.insertPrayers(PaxInitialData.getInitialPrayers())
            paxDao.insertLiturgicalDays(PaxInitialData.getInitialLiturgicalDays())
            paxDao.insertReminders(PaxInitialData.getInitialReminders())
            paxDao.insertAudioPackages(PaxInitialData.getInitialAudioPackages())
        }
    }
}
