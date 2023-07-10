package pers.kwk.newspaper.client.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pers.kwk.newspaper.client.data.entity.OrderEntity

@Dao
interface OrderDao : BaseDao<OrderEntity> {
	@Query("SELECT * FROM OrderEntity")
	fun getOrdersFlow(): Flow<List<OrderEntity>>

	@Query("DELETE FROM OrderEntity WHERE id=:orderId")
	fun deleteById(orderId: Int)

	@Query("DELETE FROM OrderEntity WHERE id=:orderId")
	suspend fun deleteByIdAsync(orderId: Int)

	@Query("DELETE FROM OrderEntity")
	fun clear()

	@Query("DELETE FROM OrderEntity")
	suspend fun clearAsync()
}