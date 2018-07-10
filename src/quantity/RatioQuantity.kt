/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

import order.Orderable

// Understands a particular measurement
class RatioQuantity internal constructor(amount: Number, unit: Unit) : IntervalQuantity(amount, unit) {   private val amount = amount.toDouble()

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), this.unit)

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun minus(other: RatioQuantity) = this + -other
}