package com.example.applicationrandomuser.data.database

import androidx.room.*
import com.example.applicationrandomuser.data.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemEntity: ItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(itemsEntity: List<ItemEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(itemEntity: ItemEntity): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItems(items: List<ItemEntity>)

    @Query("DELETE FROM items")
    suspend fun deleteAll()

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<ItemEntity>>

    @Query("SELECT COUNT(*) FROM items")
    fun getCount(): Int

}