package com.jagerapps.vitroclean.shared.domain.use_cases

import com.jaegerapps.vitroclean.shared.domain.use_cases.CalculatorFunctions
import kotlin.test.Test
import kotlin.test.assertEquals

class CalculatorFunctionsTest {


    @Test
    fun test() {
        // Test case for value below 5
        val result = CalculatorFunctions.createStatsByCubicFeet(4)
        assertEquals(224, result.recommendedVitrocleanVfaLoad)
        assertEquals(400, result.recommendedSandLoad)
        assertEquals(96, result.recommendedPebble)
        assertEquals(1.9, result.fiftyBagPebble)
        assertEquals(4.5, result.fiftyBagVitroclean)
    }

    @Test
    fun testCreateStatsByCubicFeet() {
        // Test case for value equal or above 5
        val result = CalculatorFunctions.createStatsByCubicFeet(5)
        assertEquals(280, result.recommendedVitrocleanVfaLoad)
        assertEquals(500, result.recommendedSandLoad)
        assertEquals(120, result.recommendedPebble)
        assertEquals(2.4, result.fiftyBagPebble)
        assertEquals(5.6, result.fiftyBagVitroclean)
    }

    @Test
    fun testCreateStatsBySandNeededBelow400() {
        // Test case for value below 400
        val result = CalculatorFunctions.createStatsBySandNeeded(300)
        assertEquals(240, result.recommendedVitrocleanVfaLoad) // (300 * .80)
        assertEquals(300, result.recommendedSandLoad)
        assertEquals(0, result.recommendedPebble)
        assertEquals(0.0, result.fiftyBagPebble)
        assertEquals(4.8, result.fiftyBagVitroclean) // roundToNearestTenth((240 / 50))

    }

    @Test
    fun testCreateStatsBySandNeededAbove400() {
        // Test case for value equal or above 400
        val result = CalculatorFunctions.createStatsBySandNeeded(400)
        assertEquals(224, result.recommendedVitrocleanVfaLoad) // ((400 * .80) * .7).toInt()
        assertEquals(400, result.recommendedSandLoad)
        assertEquals(96, result.recommendedPebble) // ((400 * .80) * .3).toInt()
        assertEquals(1.9, result.fiftyBagPebble) // roundToNearestTenth((96 / 50))
        assertEquals(4.5, result.fiftyBagVitroclean) // roundToNearestTenth((224 / 50))

    }

}