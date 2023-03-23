package om.example.loginusingmvvm.data.repository

import com.example.loginusingmvvm.data.network.AuthApi
import com.example.loginusingmvvm.data.network.Resource
import com.example.loginusingmvvm.data.responses.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.simplifiedcoding.data.network.RemoteDataSource
import net.simplifiedcoding.data.network.SafeApiCall
import okhttp3.Response


import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val remoteDataSource: RemoteDataSource,
) : SafeApiCall {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }


}