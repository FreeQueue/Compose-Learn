package pers.kwk.newspaper.client.repository

import dagger.hilt.android.scopes.ViewModelScoped
import pers.kwk.newspaper.client.data.entity.toEntity
import pers.kwk.newspaper.client.data.local.dao.OrderDao
import pers.kwk.newspaper.client.data.local.dao.UserDao
import pers.kwk.newspaper.client.remote.OrderApi
import pers.kwk.newspaper.remote.NetCode
import pers.kwk.newspaper.remote.NetCodes
import pers.kwk.newspaper.remote.TokenFail
import pers.kwk.newspaper.remote.case
import pers.kwk.newspaper.remote.code.AddOrderCode
import pers.kwk.newspaper.remote.parse
import pers.kwk.newspaper.remote.request.AddOrderRequest
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class OrdersRepository @Inject constructor(
	private val userDao: UserDao,
	private val orderDao: OrderDao,
	private val orderApi: OrderApi
) {
	fun observeOrders() = orderDao.getOrdersFlow()

	suspend fun addOrder(newspaperId: String, orderYear: Int): AddOrderCode {
		userDao.getMainAddress()?.let {
			return addOrder(newspaperId, it, orderYear)
		}
		return AddOrderCode.EmptyMainAddress
	}

	suspend fun addOrder(newspaperId: String, address: String, orderYear: Int): AddOrderCode {
		userDao.getTokenAsync()?.let { token ->
			val request = AddOrderRequest(newspaperId, address, orderYear)
			return orderApi.addOrder(token.toRemote(),request).parse {
				orderDao.insertAsync(it.data.toEntity())
			}.case(NetCodes.TokenFail) { userDao.clear() }
		}
		userDao.clear()
		return TokenFail()
	}

	suspend fun deleteOrder(orderId:Int): NetCode {
		userDao.getTokenAsync()?.let {
			return orderApi.deleteOrder(it.toRemote(),orderId)
				.case(NetCodes.TokenFail){ userDao.clear() }
		}
		userDao.clear()
		return TokenFail()
	}

	suspend fun refreshOrders(): NetCode {
		userDao.getTokenAsync()?.let { token ->
			return orderApi.getOrders(token.toRemote()).parse {
				orderDao.insertAsync(it.data.map { it.toEntity() })
			}.case(NetCodes.TokenFail){ userDao.clear() }
		}
		userDao.clear()
		return TokenFail()
	}
}