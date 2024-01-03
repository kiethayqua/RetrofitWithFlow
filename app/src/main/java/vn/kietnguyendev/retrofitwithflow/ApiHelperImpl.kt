package vn.kietnguyendev.retrofitwithflow

import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val userApi: UserApi): ApiHelper {
    override fun getUsers() = flow {
        emit(userApi.getUsers())
    }
}