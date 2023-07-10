package pers.kwk.newspaper.client.ui.addorder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pers.kwk.newspaper.client.repository.NewspapersRepository
import pers.kwk.newspaper.client.repository.OrdersRepository
import pers.kwk.newspaper.client.repository.UserRepository
import pers.kwk.newspaper.client.ui.GlobalViewModel
import pers.kwk.newspaper.client.ui.Route
import pers.kwk.newspaper.client.ui.mock.newspaperEntity
import pers.kwk.newspaper.client.ui.mock.userEntity
import pers.kwk.newspaper.remote.code.AddOrderCode
import javax.inject.Inject

@HiltViewModel
class AddOrderScreenViewModel @Inject constructor(
	private val userRepository: UserRepository,
	private val ordersRepository: OrdersRepository,
	private val newspapersRepository: NewspapersRepository
) : ViewModel() {
	var user by mutableStateOf(userEntity)
		private set
	var newspaper by mutableStateOf(newspaperEntity)
		private set
	var address by mutableStateOf<String?>(null)
	var orderYear by mutableStateOf(0)

	fun addOrder(navController: NavController,globalViewModel: GlobalViewModel) {
		if (address==null){
			globalViewModel.showSnackBar("地址不能为空")
			return
		}
		viewModelScope.launch {
			when (ordersRepository.addOrder(newspaper.id, address!!, orderYear)) {
				AddOrderCode.InvalidNewspaperId -> {
					globalViewModel.showSnackBar("无效的报刊号")
					navController.navigateUp()
				}
				AddOrderCode.InvalidAddress -> globalViewModel.showSnackBar("无效的地址")
				AddOrderCode.InvalidOrderYear -> globalViewModel.showSnackBar("无效的订阅年份")
				AddOrderCode.EmptyMainAddress -> globalViewModel.showSnackBar("地址不能为空")
			}
			navController.navigateUp()
		}
	}

	fun showOrderPage(navController: NavController, globalViewModel: GlobalViewModel, newspaperId: String) {
		val userEntity=userRepository.getUser()
		if (userEntity==null){
			userRepository.logout()
			return
		}
		user=userEntity
		address=user.mainAddress
		val newspaperEntity= newspapersRepository.getNewspaper(newspaperId)
		if (newspaperEntity==null){
			globalViewModel.showSnackBar("报刊[${newspaperId}]不存在")
			return
		}
		newspaper=newspaperEntity
		navController.navigate(Route.ADD_ORDER)
	}
}