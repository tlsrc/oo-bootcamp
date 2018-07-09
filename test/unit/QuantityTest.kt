/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quantity.*
import kotlin.test.assertFailsWith

// Ensures RatioQuantity operates correctly
class QuantityTest {

    @Test fun `equality of like units`() {
        assertEquals(8.tablespoons, 8.tablespoons)
        assertNotEquals(8.tablespoons, 6.tablespoons)
        assertNotEquals(8.tablespoons, "a string")
        assertNotEquals(8.tablespoons, null)
    }

    @Test fun `equality of unlike units`() {
        assertEquals(8.tablespoons, 0.5.cups)
        assertEquals(1.gallons, 768.teaspoons)
        assertEquals(1.miles, (12 * 5280).inches)
        assertNotEquals(8.tablespoons, 8.teaspoons)
    }

    @Test fun `hash set support`() {
        assert(hashSetOf(8.tablespoons).contains(0.5.cups))
        assert(hashSetOf(330.feet).contains(0.5.furlongs))
        assert(hashSetOf(10.celsius).contains(50.fahrenheit))
    }

    @Test fun `hash code`() {
        assertEquals(8.tablespoons.hashCode(), 0.5.cups.hashCode())
        assertEquals(330.feet.hashCode(), 0.5.furlongs.hashCode())
        assertEquals(10.celsius.hashCode(), 50.fahrenheit.hashCode())

    }

    @Test fun arithmetic() {
        assertEquals(0.5.quarts, +6.tablespoons + 13.ounces)
        assertEquals((-6).tablespoons, -6.tablespoons)
        assertEquals(-0.5.pints, 10.tablespoons - 13.ounces)
        assertEquals(-4.feet, 24.inches - 2.yards)
    }

    @Test fun crossMetricType()  {
        assertNotEquals(1.inches, 1.teaspoons)
        assertNotEquals(4.ounces, 2.feet)
    }

    @Test fun `incompatible units`() {
        assertFailsWith(IllegalArgumentException::class) {
            3.chains - 4.tablespoons
        }
    }

    @Test fun temperatures() {
        assertBidirectionalEquality(0.celsius, 32.fahrenheit)
        assertBidirectionalEquality(10.celsius, 50.fahrenheit)
        assertBidirectionalEquality(100.celsius, 212.fahrenheit)
        assertBidirectionalEquality((-40).celsius, (-40).fahrenheit)
    }

    @Test fun temperatureArithmetic() {
        // The following should not compile!
//         10.celsius - 32.fahrenheit; fail<Any?>("Prior line should not have compiled")
    }

    private fun assertBidirectionalEquality(left: IntervalQuantity, right: IntervalQuantity) {
        assertEquals(left, right)
        assertEquals(right, left)
    }
}