package com.mobi.mybank.data.remote

import com.mobi.mybank.data.model.AccountDTOItem
import retrofit2.http.GET

interface AccountAPI {


    @GET("banks.json")
    suspend fun getAccountListe(
    ): ArrayList<AccountDTOItem>

}