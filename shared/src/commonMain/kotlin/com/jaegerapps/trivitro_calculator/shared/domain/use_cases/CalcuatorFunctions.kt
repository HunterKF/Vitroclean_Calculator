package com.jaegerapps.trivitro_calculator.shared.domain.use_cases

import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

class CalcuatorFunctions {

    companion object {
        fun createStatsByCubicFeet(value: Int): PoolFilter {
            return PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = (value * 2.0).toInt(),
                recommendedSandLoad = (value * 3.0).toInt(),
                recommendedPebble = (value * 4.0).toInt(),
                fiftyBagPebble = (value * 5.0).toInt(),
                fiftyBagVitroclean = (value * 6.0).toInt()
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