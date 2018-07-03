/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package unit

import chance.Chance
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ChanceTest {

    @Test fun equality() {
        assertEquals(Chance(0.75), Chance(0.75))
        assertNotEquals(Chance(0.75), Chance(0.25))
        assertNotEquals(Chance(0.75), "a string")
        assertNotEquals(Chance(0.75), null)
    }

}