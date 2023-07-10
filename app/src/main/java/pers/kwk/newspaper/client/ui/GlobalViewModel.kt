package pers.kwk.newspaper.client.ui

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pers.kwk.newspaper.client.repository.UserRepository
import javax.inject.Inject


@HiltViewModel
class GlobalViewModel @Inject constructor(
	private val userRepository: UserRepository,
) : ViewModel() {
	var loggedIn by mutableStateOf(false)
		private set

	var snackBarVisible by mutableStateOf(false)
		private set
	var snackBarText by mutableStateOf("")
		private set

//	init {
//		viewModelScope.launch {
//			userRepository.observeUser().collectLatest {
//				loggedIn = it != null
//			}
//		}
//	}

	fun showSnackBar(text:String,time:Long=2000){
		snackBarVisible=true
		snackBarText=text
		viewModelScope.launch {
			delay(time)
			snackBarVisible = false
		}
	}
}