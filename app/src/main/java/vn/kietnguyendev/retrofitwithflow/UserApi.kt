package vn.kietnguyendev.retrofitwithflow

import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}