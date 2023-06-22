package com.mobi.mybank.data.model

import com.mobi.mybank.domain.model.AccountData

data class AccountDTOItem(
    val accounts: List<Account>,
    val isCA: Int?,
    val name: String?
)


fun AccountDTOItem.toDomainAccount(): AccountData {
    return AccountData(
      accounts=this.accounts,
        isCA=this.isCA?: 0,
        name=this.name?: ""
    )
}


