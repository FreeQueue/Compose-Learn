package pers.kwk.newspaper.client.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pers.kwk.newspaper.client.remote.NewspaperApi
import pers.kwk.newspaper.client.remote.OrderApi
import pers.kwk.newspaper.client.remote.UserApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.build()
	}

	@Singleton
	@Provides
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
		.client(okHttpClient)
		.baseUrl("http://localhost:8080/")
		.addConverterFactory(MoshiConverterFactory.create())
		.build()

	@Provides
	@Singleton
	fun provideUserService(retrofit: Retrofit): UserApi =
		retrofit.create(UserApi::class.java)

	@Provides
	@Singleton
	fun provideOrderService(retrofit: Retrofit): OrderApi =
		retrofit.create(OrderApi::class.java)

	@Provides
	@Singleton
	fun provideNewspaperService(retrofit: Retrofit): NewspaperApi =
		retrofit.create(NewspaperApi::class.java)

}