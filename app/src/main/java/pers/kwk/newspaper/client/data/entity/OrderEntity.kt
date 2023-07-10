package pers.kwk.newspaper.client.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import pers.kwk.newspaper.client.data.local.converter.TimestampConverter
import pers.kwk.newspaper.remote.entity.Order
import java.sql.Timestamp

@Entity
@TypeConverters(TimestampConverter::class)
@JsonClass(generateAdapter = true)
data class OrderEntity(
	@PrimaryKey
	val id: Int,
	val userId: Int,
	val newspaperId: String,
	val address: String,
	val date: Timestamp,
	val orderYear: Int
)

fun Order.toEntity(): OrderEntity = OrderEntity(id, userId, newspaperId, address, date, orderYear)