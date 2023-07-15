package com.infobip.rtc.showcase.service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*

object TokenService {

    private const val TOKEN_API_BASE_URL = "                                                                                                                                 "
//    https://r59er1.api.infobip.com/webrtc/1/token
    private val accessTokenService = Retrofit.Builder()
        .baseUrl(TOKEN_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AccessTokenService::class.java)

    private var accessToken = obtainToken()

    fun getAccessToken(): AccessToken {
        if (expired()) {
            accessToken = obtainToken()
        }
        return accessToken
    }

    private fun obtainToken(): AccessToken {
        return accessTokenService.generate("App 560e938300117598f2e80b2243996c39-668799a9-571f-424a-847a-aeef143c7fb4",RequestBody.authRequestBody).execute().body()!!
    }

    private fun expired(): Boolean {
        return Date().after(accessToken.expirationTime)
    }
}

data class AccessToken (
    val token: String,
    val identity: String,
    val expirationTime: Date
)

interface AccessTokenService {
    @POST("webrtc/1/token")
    fun generate(@Header("Authorization") api_key: String, @Body requestyBody:AuthRequestModel): Call<AccessToken>
}