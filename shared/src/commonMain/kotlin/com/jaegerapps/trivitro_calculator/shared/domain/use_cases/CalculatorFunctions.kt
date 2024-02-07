package com.jaegerapps.trivitro_calculator.shared.domain.use_cases

import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter
import kotlin.math.round

class CalculatorFunctions {

    companion object {
        private fun lowValueVFLoad(value: Int): Double {
            return if (value >= 5) {
                (value * .80) * .7
            } else {
                (value * .80).toDouble()
            }
        }
        private fun lowValueVPLoad(value: Int): Double {
            return if (value >= 5) {
                ((value * .80) * .3)
            } else {
                0.0
            }
        }

        private fun sandLoadVFLoad(value: Int): Double {
            return if (value >= 400) {
                ((value * .80) * .7)
            } else {
                (value * .80)
            }
        }
        private fun sandVPLoad(value: Int): Double {
            return if (value >= 400) {
                ((value * .80) * .3)
            } else {
                0.0
            }
        }

        private fun roundToNearestTenth(value: Double): Double {
            return round(value * 10) / 10
        }
        fun createStatsByCubicFeet(value: Int): PoolFilter {
            return PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = lowValueVFLoad(value).toInt(),
                recommendedSandLoad = (value * 100),
                recommendedPebble = lowValueVPLoad(value).toInt(),
                fiftyBagPebble = roundToNearestTenth((lowValueVFLoad(value) / 50)),
                fiftyBagVitroclean = roundToNearestTenth((lowValueVPLoad(value) / 50))
            )
        }
        fun createStatsBySandNeeded(value: Int): PoolFilter {
            return PoolFilter(
                manufacturer = "",
                model = "",
                recommendedVitrocleanVfaLoad = sandLoadVFLoad(value).toInt(),
                recommendedSandLoad = value,
                recommendedPebble = sandVPLoad(value).toInt(),
                fiftyBagPebble = roundToNearestTenth((sandVPLoad(value) / 50)),
                fiftyBagVitroclean = roundToNearestTenth((sandLoadVFLoad(value) / 50))
            )
        }
    }

}