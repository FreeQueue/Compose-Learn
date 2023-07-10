package pers.kwk.newspaper.client.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pers.kwk.newspaper.client.data.entity.NewspaperEntity

@Dao
interface NewspaperDao : BaseDao<NewspaperEntity> {
	@Query("SELECT * FROM NewspaperEntity")
	fun getNewspapers(): Flow<List<NewspaperEntity>>

	@Query("SELECT * FROM NewspaperEntity WHERE id=:id")
	fun getNewspaperById(id: String): NewspaperEntity?

	@Query("SELECT * FROM NewspaperEntity WHERE id=:id")
	suspend fun getNewspaperByIdAsync(id: String): NewspaperEntity?

	@Query("DELETE FROM NewspaperEntity")
	fun clear()

	@Query("DELETE FROM NewspaperEntity")
	suspend fun clearAsync()
}