package pers.kwk.newspaper.client.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import pers.kwk.newspaper.client.data.entity.TokenEntity
import javax.inject.Inject

@ProvidedTypeConverter
class TokenConverter @Inject constructor(
	private val moshi: Moshi
){
	@TypeConverter
	fun fromToken(value: TokenEntity): String {
		val adapter = moshi.adapter(TokenEntity::class.java)
		return adapter.toJson(value)

	}

	@TypeConverter
	fun toToken(value: String): TokenEntity? {
		val adapter = moshi.adapter(TokenEntity::class.java)
		return adapter.fromJson(value)
	}
}