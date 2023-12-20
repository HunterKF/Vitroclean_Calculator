package com.jaegerapps.trivitro_calculator.shared.domain.use_cases

import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

class CalcuatorFunctions {

    companion object {
        fun createStatsByCubicFeet(value: Int): PoolFilter {
            return PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = (value * 0.8).toInt(),
                recommendedSandLoad = (value * 0.2).toInt(),
                recommendedPebble = (value * 0.3).toInt(),
                fiftyBagPebble = (value * 1.8).toInt(),
                fiftyBagVitroclean = (value * 0.5).toInt()
            )
        }
        fun createStatsBySandNeeded(value: Int): PoolFilter {
            return PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = (value * 0.1).toInt(),
                recommendedSandLoad = (value * 0.2).toInt(),
                recommendedPebble = (value * 0.4).toInt(),
                fiftyBagPebble = (value * 0.1).toInt(),
                fiftyBagVitroclean = (value * 2.5).toInt()
            )
        }
    }

}