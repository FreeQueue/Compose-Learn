package pers.kwk.newspaper.client.remote

import pers.kwk.newspaper.client.data.entity.TokenEntity
import pers.kwk.newspaper.client.data.entity.UserEntity
import pers.kwk.newspaper.remote.NetResponse
import pers.kwk.newspaper.remote.code.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {
	@FormUrlEncoded
	@POST("login")
	suspend fun login(
		@Field("username") username: String,
		@Field("password") password: String
	): NetResponse<UserEntity, LoginCode>

	@FormUrlEncoded
	@POST("register")
	suspend fun register(
		@Field("username") username: String,
		@Field("password") password: String,
		@Field("re_password") rePassword: String,
	): RegisterCode

	@FormUrlEncoded
	@PUT("/user/password")
	suspend fun modifyPassword(
		@Field("token") token: TokenEntity,
		@Field("password") password: String,
		@Field("new_password") newPassword: String
	): ModifyPasswordCode

	@FormUrlEncoded
	@PUT("/user/username")
	suspend fun modifyUsername(
		@Field("token") token: TokenEntity,
		@Field("new_username") newUsername: String
	): ModifyUsernameCode

	@FormUrlEncoded
	@PUT("/user/address")
	suspend fun modifyAddress(
		@Field("token") token: TokenEntity,
		@Field("new_address") newAddress: String
	): ModifyMainAddressCode
}


