package com.mobi.mybank.domain.repository

import com.mobi.mybank.data.model.AccountDTOItem

interface AccountRepository {


    suspend fun getAccountListe(): ArrayList<AccountDTOItem>




}