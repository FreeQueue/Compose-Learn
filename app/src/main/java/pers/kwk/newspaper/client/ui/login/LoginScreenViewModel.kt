package pers.kwk.newspaper.client.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pers.kwk.newspaper.client.repository.UserRepository
import pers.kwk.newspaper.client.ui.GlobalViewModel
import pers.kwk.newspaper.client.ui.Route
import pers.kwk.newspaper.remote.case
import pers.kwk.newspaper.remote.code.LoginCode
import pers.kwk.newspaper.remote.failure
import pers.kwk.newspaper.remote.success
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
	private val userRepository: UserRepository,
) : ViewModel() {
	var usernameError by mutableStateOf("")
	var passwordError by mutableStateOf("")

	fun login(globalViewModel: GlobalViewModel, navController: NavController, username: String, password: String) {
		viewModelScope.launch {
			val result = userRepository.login(username, password)
			result.failure {
				usernameError = when (result) {
					LoginCode.EmptyUsername -> "用户名不能为空"
					else -> ""
				}
				passwordError = when (result) {
					LoginCode.EmptyPassword -> "用户密码不能为空"
					else -> ""
				}
			}.case(LoginCode.Mismatch){
				globalViewModel.showSnackBar("用户名密码不匹配",2000)
			}.success {
				navController.navigate(Route.HOME)
			}
		}
	}
}