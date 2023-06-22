package com.mobi.mybank.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobi.mybank.common.Resource
import com.mobi.mybank.data.model.AccountDTOItem
import com.mobi.mybank.domain.use_case.AccountUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCases: AccountUseCases

) : ViewModel() {

    private val _maccountLists = MutableStateFlow<AccountStates>(AccountStates())
    val maccountLists: StateFlow<AccountStates> = _maccountLists




    fun getAccountListe() {
        accountUseCases().onEach {
            when (it) {
                is Resource.Loading -> {
                    _maccountLists.value = AccountStates(isLoading = true)
                }
                is Resource.Success -> {
                    _maccountLists.value = AccountStates(data = it.data as ArrayList<AccountDTOItem>?)
                }
                is Resource.Error -> {
                    _maccountLists.value = AccountStates(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

}