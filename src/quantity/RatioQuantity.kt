/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

// Understands a particular measurement
class RatioQuantity internal constructor(amount: Number, private val unit: Unit) {

    private val amount = amount.toDouble()

    override fun equals(other: Any?) = this === other || other is RatioQuantity && this.equals(other)

    private fun equals(other: RatioQuantity) = this.isCompatible(other) && this.amount == convertedAmount(other)

    override fun hashCode() = unit.hashCode(amount)

    private fun isCompatible(other: RatioQuantity) = this.unit.isCompatible(other.unit)

    private fun convertedAmount(other: RatioQuantity): Double {
        return this.unit.convertedAmount(other.amount, other.unit)
    }

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), this.unit)

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun unaryPlus() = this

    operator fun minus(other: RatioQuantity) = this + -other
}