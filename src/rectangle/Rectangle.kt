/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package rectangle

// Understands a four-sided polygon with sides at right angles
class Rectangle (height: Number, width: Number) {

    init {
        if (height.toDouble() <= 0.0 || width.toDouble() <= 0)
            throw IllegalArgumentException("Dimensions must be > zero")
    }

    private val height: Double = height.toDouble()
    private val width: Double = width.toDouble()

    val area: Double get() = width * height

    fun perimeter() = 2 * (width + height)
}