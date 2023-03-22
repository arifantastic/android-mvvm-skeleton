package xyz.arifz.dfatest.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.arifz.dfatest.data.local.models.UserWithPosts
import xyz.arifz.dfatest.domain.usecase.GetUsersWithPostsUseCase

class MainViewModel(private val getUsersWithPostsUseCase: GetUsersWithPostsUseCase) : ViewModel() {

    private val _usersWithPosts = MutableLiveData<Result<List<UserWithPosts>>>()
    val usersWithPosts: LiveData<Result<List<UserWithPosts>>> = _usersWithPosts

    fun getUsersWithPosts() {
        viewModelScope.launch {
            _usersWithPosts.value = getUsersWithPostsUseCase()
        }
    }
}


class MainViewModelFactory(private val getUsersWithPostsUseCase: GetUsersWithPostsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(getUsersWithPostsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
