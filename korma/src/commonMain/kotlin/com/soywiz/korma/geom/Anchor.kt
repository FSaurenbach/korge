package com.soywiz.korma.geom

import com.soywiz.korma.interpolation.Interpolable
import com.soywiz.korma.interpolation.interpolate

data class Anchor(val sx: Double, val sy: Double) : Interpolable<Anchor> {
    companion object {
        operator fun invoke(sx: Int, sy: Int) = Anchor(sx.toDouble(), sy.toDouble())
        operator fun invoke(sx: Float, sy: Float) = Anchor(sx.toDouble(), sy.toDouble())

        val TOP_LEFT = Anchor(0.0, 0.0)
        val TOP_CENTER = Anchor(0.5, 0.0)
        val TOP_RIGHT = Anchor(1.0, 0.0)

        val MIDDLE_LEFT = Anchor(0.0, 0.5)
        val MIDDLE_CENTER = Anchor(0.5, 0.5)
        val MIDDLE_RIGHT = Anchor(1.0, 0.5)

        val BOTTOM_LEFT = Anchor(0.0, 1.0)
        val BOTTOM_CENTER = Anchor(0.5, 1.0)
        val BOTTOM_RIGHT = Anchor(1.0, 1.0)

        val TOP get() = TOP_CENTER
        val LEFT get() = MIDDLE_LEFT
        val RIGHT get() = MIDDLE_RIGHT
        val BOTTOM get() = BOTTOM_CENTER
        val CENTER get() = MIDDLE_CENTER
    }

    override fun interpolateWith(ratio: Double, other: Anchor): Anchor = Anchor(
        ratio.interpolate(this.sx, other.sx),
        ratio.interpolate(this.sy, other.sy)
    )

    fun toNamedString(): String = when (this) {
        TOP_LEFT -> "Anchor.TOP_LEFT"
        TOP -> "Anchor.TOP"
        TOP_RIGHT -> "Anchor.TOP_RIGHT"
        LEFT -> "Anchor.LEFT"
        CENTER -> "Anchor.MIDDLE_CENTER"
        RIGHT -> "Anchor.RIGHT"
        BOTTOM_LEFT -> "Anchor.BOTTOM_LEFT"
        BOTTOM_CENTER -> "Anchor.BOTTOM_CENTER"
        BOTTOM_RIGHT -> "Anchor.BOTTOM_RIGHT"
        else -> toString()
    }
}
