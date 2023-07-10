package pers.kwk.newspaper.client.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import pers.kwk.newspaper.client.data.local.converter.TokenConverter

@Entity
@JsonClass(generateAdapter = true)
@TypeConverters(TokenConverter::class)
data class UserEntity(
	@PrimaryKey
	val token: TokenEntity,
	val username: String,
	val mainAddress: String?,
)

