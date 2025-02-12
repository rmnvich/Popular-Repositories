package com.splunk.test.data.datasource

import com.splunk.test.data.model.TrendingRepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubRepositoryService {

    @GET("search/repositories?q=stars:>1000&sort=start&order=desc")
    suspend fun getTrendingRepositories(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): TrendingRepositoriesResponse

    @GET
    suspend fun getRepositoryLanguages(
        @Url url: String,
    ): Map<String, Int>
}