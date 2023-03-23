package com.example.loginusingmvvm.data.responses

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("output" ) var output : ArrayList<Output> = arrayListOf()
) {
    data class Output(
        @SerializedName("success") var success: String? = null,
        @SerializedName("message") var message: String? = null
    )
}