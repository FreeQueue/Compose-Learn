package pers.kwk.newspaper.client.data.entity

import com.squareup.moshi.JsonClass
import pers.kwk.newspaper.remote.entity.Token

@JsonClass(generateAdapter = true)
data class TokenEntity(
	val userId: Int,
	val password:String,
){
	fun toRemote():Token=Token(userId, password)
}