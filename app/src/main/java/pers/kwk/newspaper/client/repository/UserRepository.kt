package pers.kwk.newspaper.client.repository

import dagger.hilt.android.scopes.ViewModelScoped
import pers.kwk.newspaper.client.data.local.dao.UserDao
import pers.kwk.newspaper.client.remote.UserApi
import pers.kwk.newspaper.remote.TokenFail
import pers.kwk.newspaper.remote.code.LoginCode
import pers.kwk.newspaper.remote.code.ModifyMainAddressCode
import pers.kwk.newspaper.remote.code.ModifyPasswordCode
import pers.kwk.newspaper.remote.code.ModifyUsernameCode
import pers.kwk.newspaper.remote.code.RegisterCode
import pers.kwk.newspaper.remote.parse
import pers.kwk.newspaper.remote.success
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class UserRepository @Inject constructor(
	private val userDao: UserDao,
	private val userApi: UserApi
) {
	fun getUser() = userDao.getUser()

	fun observeUser() = userDao.getUserFlow()

	fun logout() {
		userDao.clear()
	}

	suspend fun login(username: String, password: String): LoginCode {
		val response = userApi.login(username, password)
		return response.parse {
			userDao.insertAsync(it.data)
		}
	}

	suspend fun register(username: String, password: String, rePassword: String): RegisterCode {
		val response = userApi.register(username, password, rePassword)
		return response.success {
			login(username, password)
		}
	}

	suspend fun updatePassword(password: String, newPassword: String): ModifyPasswordCode {
		userDao.getTokenAsync()?.let { token ->
			val response = userApi.modifyPassword(token, password, newPassword)
			return response.success {
				userDao.updateAsync(userDao.getUser()!!.copy(token = token.copy(password=newPassword)))
			}
		}
		logout()
		return TokenFail()
	}

	suspend fun updateUserName(newUsername: String): ModifyUsernameCode {
		userDao.getTokenAsync()?.let {
			val response = userApi.modifyUsername(it, newUsername)
			return response.success {
				userDao.updateAsync(userDao.getUser()!!.copy(username =newUsername))
			}
		}
		logout()
		return TokenFail()
	}

	suspend fun updateMainAddress(newMainAddress: String): ModifyMainAddressCode {
		userDao.getTokenAsync()?.let {
			val response = userApi.modifyAddress(it, newMainAddress)
			return response.success {
				userDao.updateAsync(userDao.getUser()!!.copy(mainAddress = newMainAddress))
			}
		}
		logout()
		return TokenFail()
	}
}