package com.example.myfeed.data

import com.example.myfeed.data.Posts

data class Feed (

	val posts : List<Posts>,
	val page : Int
)