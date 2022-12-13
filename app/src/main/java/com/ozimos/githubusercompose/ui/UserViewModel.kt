package com.ozimos.githubusercompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozimos.githubusercompose.data.model.response.UserModel
import com.ozimos.githubusercompose.data.repo.UserRepo
import com.ozimos.githubusercompose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepo) : ViewModel() {

    private val _listUser = MutableStateFlow<UiState<List<UserModel>>>(UiState.Loading)
    val listUser: StateFlow<UiState<List<UserModel>>>
        get() = _listUser

    init {
        getListUser("kharozim")
    }

    fun getListUser(name: String) {
        viewModelScope.launch {
            _listUser.value = UiState.Loading
            repository.getListUserByName(name)
                .catch { e ->
                    _listUser.value = UiState.Error(e.localizedMessage ?: "Failed load data")
                }
                .collect { data ->
                    delay(300)
                    _listUser.value =
                        UiState.Success(data = data.asSequence().map { it.toModel() }.toList())
                }
        }
    }

    private val _listUserFav = MutableStateFlow<UiState<List<UserModel>>>(UiState.Loading)
    val listUserFav: StateFlow<UiState<List<UserModel>>>
        get() = _listUserFav

    fun getListFavorite() {
        viewModelScope.launch {
            _listUserFav.value = UiState.Loading
            val data = repository.getListUserFavorite()
            delay(300)
            _listUserFav.value =
                UiState.Success(data = data.asSequence().map { it.toModel() }.toList())
        }
    }

    fun clearData() {
        _listUser.value = UiState.Success(data = emptyList())
    }


    private val _detailUser = MutableStateFlow<UiState<UserModel>>(UiState.Loading)
    val detailUser: StateFlow<UiState<UserModel>>
        get() = _detailUser

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            _detailUser.emit(UiState.Loading)
            repository.getDetailUser(username)
                .catch { exception ->
                    _detailUser.emit(UiState.Error(exception.localizedMessage ?: "Unknown Error"))
                }
                .collect { result ->
                    if (result != null) {
                        _detailUser.emit(UiState.Success(result.toModel()))
                    }
                }

        }
    }

    fun updateUserFavorite(user: UserModel) {
        viewModelScope.launch {
            _detailUser.emit(UiState.Loading)
            val result = repository.updateUserFavorite(user)
            _detailUser.emit(UiState.Success(result.toModel()))
        }
    }
}