package com.jaegerapps.trivitro_calculator.shared.data.remote.dtos

@kotlinx.serialization.Serializable
data class PoolFilterDto(
    val id: Int,
    val manufacturer_name: String,
    val model: String,
    val recommended_vitroclean_vfa_load_lbs: Int,
    val recommended_sand_load_lbs: Int,
    val recommended_vitroclean_pebble_vfeight_load_lbs: Int,
    val fifty_lb_bags_of_vitroclean_vfa : Int,
    val fifty_lb_bags_of_pebble_vfeight : Int,
)
