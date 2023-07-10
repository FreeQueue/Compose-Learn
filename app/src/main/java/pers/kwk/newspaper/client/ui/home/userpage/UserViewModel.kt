package pers.kwk.newspaper.client.ui.home.userpage

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pers.kwk.newspaper.client.repository.OrdersRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val ordersRepository: OrdersRepository,
):ViewModel() {

}