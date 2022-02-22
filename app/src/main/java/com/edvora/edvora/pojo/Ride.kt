package com.edvora.edvora.pojo

data class Ride(
    var id: Int,
    var origin_station_code: Int,
    var station_path: List<Int>,
    var destination_station_code: Int,
    var date: Long,
    var map_url: String,
    var state: String,
    var city: String
    )