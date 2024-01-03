package vn.kietnguyendev.retrofitwithflow

import kotlinx.coroutines.flow.Flow


interface ApiHelper {
    fun getUsers(): Flow<List<User>>
}