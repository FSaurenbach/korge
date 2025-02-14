package com.soywiz.korim.color

import kotlin.test.Test
import kotlin.test.assertEquals

class ColorMatrixTest {
    @Test
    fun test() {
        assertEquals(Colors.RED, Colors.RED.transform(ColorMatrix()))
        assertEquals(RGBA.float(0.5, 0.5, 1, 1), Colors.WHITE.transform(ColorMatrix(
            0, 0, 0, 0, 0.5,
            0, 0, 0, 0, 0.5,
            1, 0, 0, 0, 0,
            0, 0, 0, 1, 0
        )))
        //assertEquals(Colors.BLUE, Colors.RED.toYUVA().transform(ColorMatrix.rotateHue((+240).degrees)))
    }
}
