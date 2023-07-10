package pers.kwk.newspaper.client.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pers.kwk.newspaper.client.data.entity.TokenEntity
import pers.kwk.newspaper.client.data.entity.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {
	@Query("SELECT * FROM UserEntity LIMIT 1")
	fun getUserFlow(): Flow<UserEntity?>

	@Query("SELECT * FROM UserEntity LIMIT 1")
	fun getUser(): UserEntity?

	@Query("SELECT * FROM UserEntity LIMIT 1")
	suspend fun getUserAsync(): UserEntity?

	@Query("SELECT username FROM UserEntity LIMIT 1")
	fun getUserName(): String?

	@Query("SELECT mainAddress FROM UserEntity LIMIT 1")
	fun getMainAddress(): String?

	@Query("DELETE FROM UserEntity")
	fun clear()

	suspend fun getTokenAsync(): TokenEntity? {
		return getUserAsync()?.token
	}
}