package pers.kwk.newspaper.client.repository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pers.kwk.newspaper.client.data.local.dao.NewspaperDao
import pers.kwk.newspaper.client.data.local.dao.OrderDao
import pers.kwk.newspaper.client.data.local.dao.UserDao
import pers.kwk.newspaper.client.remote.NewspaperApi
import pers.kwk.newspaper.client.remote.OrderApi
import pers.kwk.newspaper.client.remote.UserApi
//
//@Module
//@InstallIn(ViewModelComponent::class)
//object RepositoryModule {
//
//	@Binds
//	fun providesNewspaperRepo(
//		newspaperDao: NewspaperDao,
//		newspaperApi: NewspaperApi
//	): NewspapersRepository { // this is just fake repository
//		return NewspapersRepository(newspaperDao, newspaperApi)
//	}
//
//	@Binds
//	fun providesOrderRepo(
//		userDao: UserDao,
//		orderDao: OrderDao,
//		orderApi: OrderApi
//	): OrdersRepository { // this is just fake repository
//		return OrdersRepository(userDao,orderDao, orderApi)
//	}
//
//	@Binds
//	fun providesUserRepo(
//		userDao: UserDao,
//		userApi: UserApi
//	): UserRepository { // this is just fake repository
//		return UserRepository(userDao,userApi)
//	}
//}
