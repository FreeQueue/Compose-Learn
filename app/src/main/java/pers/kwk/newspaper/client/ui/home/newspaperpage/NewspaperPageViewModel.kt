package pers.kwk.newspaper.client.ui.home.newspaperpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import pers.kwk.newspaper.client.data.entity.NewspaperEntity
import pers.kwk.newspaper.client.repository.NewspapersRepository
import pers.kwk.newspaper.remote.failure
import pers.kwk.newspaper.remote.success
import javax.inject.Inject

internal enum class ViewModelStatus {
	Default,
	Loading,
	Error,
}

@HiltViewModel
class NewspaperPageViewModel @Inject constructor(
	private val newspapersRepository: NewspapersRepository,
) : ViewModel() {

	internal var status by mutableStateOf(ViewModelStatus.Default)
		private set
	private val _newspapersFlow = MutableStateFlow(emptyList<NewspaperEntity>())
	val newspapersFlow = _newspapersFlow.asStateFlow()
	private val _tmpSearchIdsFlow = MutableStateFlow(emptyList<String>())
	val tmpSearchIdsFlow = _tmpSearchIdsFlow.asStateFlow()

	init {
		viewModelScope.launch {
			newspapersRepository.observeNewspapers().distinctUntilChanged().collectLatest {
				_newspapersFlow.emit(it)
			}
		}
	}

	fun search(query: String) {
		viewModelScope.launch {
			status = ViewModelStatus.Loading
			// 发起网络请求并搜索 Newspapers
			newspapersRepository.searchNewspapers(query).success {
				status = ViewModelStatus.Default
			}.failure {
				status = ViewModelStatus.Error
			}
		}
	}

	fun searchIds(query: String) {
		viewModelScope.launch {
			newspapersRepository.searchNewspaperIds(query, _tmpSearchIdsFlow)
		}
	}
}