/*
 * Copyright (c) 2018 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */
package chance

// Understands the likelihood of something occurring
class Chance(likelihoodAsFraction: Number) {
    private val certainFraction = 1.0
    private val fraction = likelihoodAsFraction.toDouble()

    init {
        if (fraction !in 0.0..1.0)
            throw IllegalArgumentException("Fraction must be between 0.0 and 1.0 inclusive")
    }

    override fun equals(other: Any?): Boolean {
        return this === other || other is Chance && this.equals(other)
    }

    private fun equals(other: Chance) = this.fraction == other.fraction

    override fun hashCode() = fraction.hashCode()

    operator fun not() = Chance(certainFraction - fraction)

    fun and(other: Chance) = Chance(this.fraction * other.fraction)

    // Implemented with DeMorgan's Law https://en.wikipedia.org/wiki/De_Morgan%27s_laws
    fun or(other: Chance) = !((!this).and(!other))
}