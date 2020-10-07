package com.example.myfeed.data

data class Posts (

	val id : String,
	val thumbnail_image : String,
	val event_name : String,
	val event_date : Int,
	val views : Int,
	val likes : Int,
	val shares : Int
)