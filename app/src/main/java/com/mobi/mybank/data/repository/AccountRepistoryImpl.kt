package com.mobi.mybank.data.repository

import com.mobi.mybank.data.model.AccountDTOItem
import com.mobi.mybank.data.remote.AccountAPI
import com.mobi.mybank.domain.repository.AccountRepository

class AccountRepistoryImpl(private val mealSearchAPI: AccountAPI) : AccountRepository {


    override suspend fun getAccountListe(): ArrayList<AccountDTOItem> {
        return mealSearchAPI.getAccountListe()
    }


}