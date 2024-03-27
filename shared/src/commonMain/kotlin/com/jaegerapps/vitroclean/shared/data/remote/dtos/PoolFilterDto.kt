package com.jaegerapps.vitroclean.shared.data.remote.dtos

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PoolFilterDto(
    @SerialName("id")val id: Int,
    @SerialName("manufacturer_name")val manufacturer_name: String,
    @SerialName("model")val model: String,
    @SerialName("recommended_sand_load_lbs")val recommended_sand_load_lbs: Int,
    @SerialName("recommended_vitroclean_load_lbs")val recommended_vitroclean_vfa_load_lbs: Int,
    @SerialName("recommended_vitroclean_pebble_load_lbs")val recommended_vitroclean_pebble_vfeight_load_lbs: Int,
    @SerialName("fifty_lb_bags_of_vitroclean")val fifty_lb_bags_of_vitroclean_vfa : Int,
    @SerialName("fifty_lb_bags_of_vitroclean_pebble")val fifty_lb_bags_of_pebble_vfeight : Int,
)
