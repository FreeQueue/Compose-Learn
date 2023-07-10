package pers.kwk.newspaper.client.remote

import pers.kwk.newspaper.remote.NetCode
import pers.kwk.newspaper.remote.NetResponse
import pers.kwk.newspaper.remote.entity.Newspaper
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewspaperApi {
	@GET("/newspapers/search")
	suspend fun searchNewspapers(@Query("search") search: String): NetResponse<List<Newspaper>, NetCode>

	@GET("/newspapers/search/id")
	suspend fun searchNewspaperIds(@Query("search") search: String): NetResponse<List<String>, NetCode>

	@FormUrlEncoded
	@POST("/newspapers")
	suspend fun getNewspapersByIds(@Field("newspaper_ids") newspaperIds: List<String>): NetResponse<List<Newspaper>, NetCode>
}