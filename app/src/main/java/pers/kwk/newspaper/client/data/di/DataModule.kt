package pers.kwk.newspaper.client.data.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pers.kwk.newspaper.client.data.local.AppDatabase
import pers.kwk.newspaper.client.data.local.converter.TokenConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

	@Provides
	@Singleton
	fun provideAppDatabase(
		application: Application,
		tokenConverter: TokenConverter
	): AppDatabase {
		return Room.databaseBuilder(
			application,
			AppDatabase::class.java,
			"Newspaper.db"
		).fallbackToDestructiveMigration().addTypeConverter(tokenConverter).build()
	}

	@Singleton
	@Provides
	fun provideMoshi(): Moshi {
		return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
	}
}