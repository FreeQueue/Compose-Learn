package pers.kwk.newspaper.client.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import pers.kwk.newspaper.client.data.entity.NewspaperEntity
import pers.kwk.newspaper.client.data.local.dao.NewspaperDao
import pers.kwk.newspaper.client.remote.NewspaperApi
import pers.kwk.newspaper.remote.NetCode
import pers.kwk.newspaper.remote.Success
import pers.kwk.newspaper.remote.parse
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class NewspapersRepository @Inject constructor(
	private val newspaperDao: NewspaperDao,
	private val newspaperApi: NewspaperApi
) {
	private val searchIdsCache = mutableMapOf<String, List<String>>()
	private val searchIdsFlow = MutableStateFlow(emptyList<String>())
	fun observeNewspapers() = searchIdsFlow.map {
		it.mapNotNull { newspaperDao.getNewspaperById(it) }
	}

	fun getNewspaper(newspaperId:String): NewspaperEntity?{
		return newspaperDao.getNewspaperById(newspaperId)
	}

	suspend fun searchNewspaperIds(query: String,searchIdsFlow: MutableStateFlow<List<String>>): NetCode {
		return if (searchIdsCache.containsKey(query)) {
			searchIdsFlow.value = searchIdsCache[query]!!
			Success()
		} else {
			newspaperApi.searchNewspaperIds(query).parse {
				searchIdsCache[query] = it.data
				searchIdsFlow.value = it.data
			}
		}
	}

	suspend fun searchNewspapers(query: String): NetCode {
		return searchNewspaperIds(query,searchIdsFlow)
	}
}