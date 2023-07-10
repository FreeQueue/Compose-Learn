package pers.kwk.newspaper.client.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import pers.kwk.newspaper.client.data.local.converter.BigDecimalConverter
import java.math.BigDecimal

@Entity
@TypeConverters(BigDecimalConverter::class)
@JsonClass(generateAdapter = true)
data class NewspaperEntity(
	@PrimaryKey
	val id: String,
	val name: String,
	val imageUrl:String?,
	val period: String,
	val annualIssueNumber: Int,
	val publishingHouse: String,
	val annualPrice: BigDecimal
)
