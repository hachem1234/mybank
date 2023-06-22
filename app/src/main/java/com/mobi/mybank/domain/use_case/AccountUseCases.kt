package com.mobi.mybank.domain.use_case

import com.mobi.mybank.common.Resource
import com.mobi.mybank.data.model.AccountDTOItem
import com.mobi.mybank.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AccountUseCases @Inject constructor(private val repository: AccountRepository) {


    operator fun invoke(): Flow<Resource<ArrayList<AccountDTOItem>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getAccountListe()
            val domainData =
                if (data != null) data.map { it} else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    } as Flow<Resource<ArrayList<AccountDTOItem>>>


}