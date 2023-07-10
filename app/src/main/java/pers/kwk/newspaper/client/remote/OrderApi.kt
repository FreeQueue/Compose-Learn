package pers.kwk.newspaper.client.remote

import pers.kwk.newspaper.remote.NetCode
import pers.kwk.newspaper.remote.NetResponse
import pers.kwk.newspaper.remote.code.AddOrderCode
import pers.kwk.newspaper.remote.entity.Order
import pers.kwk.newspaper.remote.entity.Token
import pers.kwk.newspaper.remote.request.AddOrderRequest
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OrderApi {
	@FormUrlEncoded
	@POST("user/order")
	suspend fun getOrders(@Field("token") token: Token): NetResponse<List<Order>, NetCode>

	@FormUrlEncoded
	@POST("user/order/add")
	suspend fun addOrder(
		@Field("token") token: Token,
		@Field("order") request: AddOrderRequest
	): NetResponse<Order,AddOrderCode>

	@FormUrlEncoded
	@POST("user/order/delete")
	suspend fun deleteOrder(@Field("token") token: Token, @Field("order_id") orderId: Int): NetCode
}