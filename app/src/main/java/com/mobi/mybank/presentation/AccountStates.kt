package com.mobi.mybank.presentation

import com.mobi.mybank.data.model.AccountDTOItem

data class AccountStates(
    val isLoading: Boolean = false,
    val data: ArrayList<AccountDTOItem>? = null,
    val error: String = ""
)
