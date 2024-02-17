package edu.mirea.onebeattrue.terminal.data

import retrofit2.http.GET

interface ApiService {
    @GET("aggs/ticker/AAPL/range/1/hour/2022-01-09/2023-01-09?adjusted=true&sort=asc&limit=120&apiKey=2mjT9O40zeOtfan1ntMkG9sz_1NixYyW")
    suspend fun loadBars(): Result
}