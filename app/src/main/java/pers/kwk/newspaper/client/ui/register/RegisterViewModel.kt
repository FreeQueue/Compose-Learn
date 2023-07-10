package pers.kwk.newspaper.client.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pers.kwk.newspaper.client.repository.UserRepository
import pers.kwk.newspaper.client.ui.GlobalViewModel
import pers.kwk.newspaper.client.ui.Route
import pers.kwk.newspaper.remote.case
import pers.kwk.newspaper.remote.code.RegisterCode
import pers.kwk.newspaper.remote.failure
import pers.kwk.newspaper.remote.success
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
	private val userRepository: UserRepository
) : ViewModel() {

	var usernameError by mutableStateOf("")
	var passwordError by mutableStateOf("")
	var rePasswordError by mutableStateOf("")

	fun register(globalViewModel: GlobalViewModel, navController: NavController, username: String, password: String, rePassword: String) {
		viewModelScope.launch(Dispatchers.IO) {
			val result = userRepository.register(username, password, rePassword)
			result.failure {
				usernameError = when (result) {
					RegisterCode.EmptyUsername -> "用户名不能为空"
					RegisterCode.DuplicateUsername -> "用户名重复"
					else -> ""
				}
				passwordError = when (result) {
					RegisterCode.EmptyPassword -> "用户密码不能为空"
					else -> ""
				}
				rePasswordError = when (result) {
					RegisterCode.EmptyRePassword -> "重复密码不能为空"
					RegisterCode.MismatchRePassword -> "密码和重复密码不匹配"
					else -> ""
				}
			}.case(RegisterCode.DuplicateUsername){
				globalViewModel.showSnackBar("")
			}.success {
				navController.navigate(Route.HOME)
			}
		}
	}
}