package pers.kwk.newspaper.client.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pers.kwk.newspaper.client.data.local.AppDatabase
import pers.kwk.newspaper.client.data.local.dao.NewspaperDao
import pers.kwk.newspaper.client.data.local.dao.OrderDao
import pers.kwk.newspaper.client.data.local.dao.UserDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

	@Provides
	fun provideOrderDao(appDatabase: AppDatabase): OrderDao {
		return appDatabase.orderDao()
	}

	@Provides
	fun provideUserDao(appDatabase: AppDatabase): UserDao {
		return appDatabase.userDao()
	}
	@Provides
	fun provideNewspaperDao(appDatabase: AppDatabase): NewspaperDao {
		return appDatabase.newspaperDao()
	}
}