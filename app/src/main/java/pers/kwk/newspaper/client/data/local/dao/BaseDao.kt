package pers.kwk.newspaper.client.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<E> {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entity: E): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAsync(entity: E): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg entity: E)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAsync(vararg entity: E)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entities: List<E>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAsync(entities: List<E>)

	@Update
	fun update(entity: E)

	@Update
	suspend fun updateAsync(entity: E)

	@Update
	fun update(vararg entity: E)

	@Update
	suspend fun updateAsync(vararg entity: E)

	@Delete
	fun delete(entity: E): Int

	@Delete
	suspend fun deleteAsync(entity: E): Int

	@Delete
	fun delete(vararg entity: E): Int

	@Delete
	suspend fun deleteAsync(vararg entity: E): Int
}