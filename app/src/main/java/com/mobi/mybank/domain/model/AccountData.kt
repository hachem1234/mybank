package com.mobi.mybank.domain.model

import com.mobi.mybank.data.model.Account

data class AccountData(
    val accounts: List<Account>,
    val isCA: Int,
    val name: String
)