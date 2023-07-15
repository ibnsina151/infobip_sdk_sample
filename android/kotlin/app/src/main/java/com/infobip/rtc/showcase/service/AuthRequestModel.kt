package com.infobip.rtc.showcase.service

import com.google.gson.annotations.SerializedName

data class AuthRequestModel(

	@field:SerializedName("timeToLive")
	val timeToLive: Int? = null,

	@field:SerializedName("capabilities")
	val capabilities: Capabilities? = null,

	@field:SerializedName("identity")
	val identity: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("applicationId")
	val applicationId: String? = null
)

data class Capabilities(

	@field:SerializedName("recording")
	val recording: String? = null
)
