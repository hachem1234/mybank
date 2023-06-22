package com.mobi.mybank.hilt

import com.mobi.mybank.common.Constants
import com.mobi.mybank.data.remote.AccountAPI
import com.mobi.mybank.data.repository.AccountRepistoryImpl
import com.mobi.mybank.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HIltModules {


    @Provides
    @Singleton
    fun provideMealSearchAPI(): AccountAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(AccountAPI::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchAPI: AccountAPI): AccountRepository {
        return AccountRepistoryImpl(mealSearchAPI)
    }


}