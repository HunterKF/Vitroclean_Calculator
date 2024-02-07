package com.jaegerapps.trivitro_calculator.shared.data.mappers

import com.jaegerapps.trivitro_calculator.shared.data.remote.dtos.PoolFilterDto
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

fun PoolFilterDto.toPoolFilter(): PoolFilter {
    return PoolFilter(
        manufacturer = this.manufacturer_name,
        model = this.model,
        recommendedVitrocleanVfaLoad = this.recommended_vitroclean_vfa_load_lbs,
        recommendedSandLoad = this.recommended_sand_load_lbs,
        recommendedPebble = this.recommended_vitroclean_pebble_vfeight_load_lbs,
        fiftyBagVitroclean = this.fifty_lb_bags_of_vitroclean_vfa.toDouble(),
        fiftyBagPebble = this.fifty_lb_bags_of_pebble_vfeight.toDouble()
    )
}

