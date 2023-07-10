package pers.kwk.newspaper.client.ui.home.orderpage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pers.kwk.newspaper.client.repository.OrdersRepository
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
	private val ordersRepository: OrdersRepository,
) : ViewModel() {


//	fun addOrder( address: String, orderYear: Int) {
//		viewModelScope.launch {
//			val result= ordersRepository.addOrder(newspaperId, address, orderYear)
//		}
//	}


}