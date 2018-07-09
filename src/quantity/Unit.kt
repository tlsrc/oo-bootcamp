/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package quantity

// Understands a specific metric
class Unit {

    companion object {
        internal val teaspoon = Unit()
        internal val tablespoon = Unit(3, teaspoon)
        internal val ounce = Unit(2, tablespoon)
        internal val cup = Unit(8, ounce)
        internal val pint = Unit(2, cup)
        internal val quart = Unit(2, pint)
        internal val gallon = Unit(4, quart)

        internal val inch = Unit()
        internal val foot = Unit(12, inch)
        internal val yard = Unit(3, foot)
        internal val chain = Unit(22, yard)
        internal val furlong = Unit(10, chain)
        internal val mile = Unit(8, furlong)

        internal val celsius = Unit()
        internal val fahrenheit = Unit(5/9.0, 32, celsius)
    }

    private val baseUnit: Unit
    private val baseUnitRatio: Double
    private val offset: Double

    private constructor() {
        baseUnitRatio = 1.0
        offset = 0.0
        baseUnit = this
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit)
            : this(relativeRatio, 0.0, relativeUnit)

    private constructor(relativeRatio: Number, offset: Number, relativeUnit: Unit) {
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        this.offset = offset.toDouble()
        baseUnit = relativeUnit.baseUnit
    }

    internal fun convertedAmount(otherAmount: Double, other: Unit): Double {
        if (!this.isCompatible(other)) throw IllegalArgumentException("Incompatible units")
        return (otherAmount - other.offset) * other.baseUnitRatio / this.baseUnitRatio + this.offset
    }

    internal fun hashCode(amount: Double) = ((amount - offset) * baseUnitRatio).hashCode()

    fun isCompatible(other: Unit) = this.baseUnit == other.baseUnit
}

val Number.teaspoons get() = RatioQuantity(this, Unit.teaspoon)
val Number.tablespoons get() = RatioQuantity(this, Unit.tablespoon)
val Number.ounces get() = RatioQuantity(this, Unit.ounce)
val Number.cups get() = RatioQuantity(this, Unit.cup)
val Number.pints get() = RatioQuantity(this, Unit.pint)
val Number.quarts get() = RatioQuantity(this, Unit.quart)
val Number.gallons get() = RatioQuantity(this, Unit.gallon)

val Number.inches get() = RatioQuantity(this, Unit.inch)
val Number.feet get() = RatioQuantity(this, Unit.foot)
val Number.yards get() = RatioQuantity(this, Unit.yard)
val Number.chains get() = RatioQuantity(this, Unit.chain)
val Number.furlongs get() = RatioQuantity(this, Unit.furlong)
val Number.miles get() = RatioQuantity(this, Unit.mile)

val Number.celsius get() = IntervalQuantity(this, Unit.celsius)
val Number.fahrenheit get() = IntervalQuantity(this, Unit.fahrenheit)