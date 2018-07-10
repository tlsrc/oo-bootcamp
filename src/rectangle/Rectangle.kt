/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package rectangle

import order.Orderable

// Understands a four-sided polygon with sides at right angles
class Rectangle (height: Number, width: Number): Orderable<Rectangle> {
    companion object {
        fun square(dimension: Number) = Rectangle(dimension, dimension)
    }

    init {
        if (height.toDouble() <= 0.0 || width.toDouble() <= 0)
            throw IllegalArgumentException("Dimensions must be > zero")
    }

    private val height: Double = height.toDouble()
    private val width: Double = width.toDouble()

    // Note choice of style below: Invoke area without parenthesis, or perimeter with
    val area: Double get() = width * height

    fun perimeter() = 2 * (width + height)

    override fun isBetterThan(other: Rectangle) = this.area > other.area
}