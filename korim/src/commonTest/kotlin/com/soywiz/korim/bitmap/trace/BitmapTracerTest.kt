package com.soywiz.korim.bitmap.trace

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.context2d
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Rectangle
import com.soywiz.korma.geom.vector.Winding
import com.soywiz.korma.geom.vector.circle
import com.soywiz.korma.geom.vector.rect
import com.soywiz.korma.geom.vector.rectHole
import com.soywiz.korma.geom.vector.roundRect
import kotlin.test.Test
import kotlin.test.assertEquals

class BitmapTracerTest {
    @Test
    fun testSmokeTrace() {
        val bmp = Bitmap32(300, 200, premultiplied = true).context2d {
            fill(Colors.WHITE, winding = Winding.EVEN_ODD) {
                rect(Rectangle.fromBounds(2, 2, 18, 18))
                rectHole(Rectangle.fromBounds(6, 6, 9, 12))
                rectHole(Rectangle.fromBounds(10, 5, 15, 12))
                rect(Rectangle.fromBounds(50, 2, 68, 18))
                circle(100, 100, 40)
                circle(100, 100, 20)
                roundRect(200, 50, 50, 50, 5, 5)
                circle(130, 100, 20)
            }
        }
        assertEquals(
            "M18,2 L18,5 L2,5 L2,2 Z M10,5 L10,6 L2,6 L2,5 Z M6,6 L6,12 L2,12 L2,6 Z M10,6 L10,12 L9,12 L9,6 Z M18,5 L18,12 L15,12 L15,5 Z M18,12 L18,17 L2,17 L2,12 Z M68,2 L68,17 L50,17 L50,2 Z M107,60 L111,61 L117,63 L119,64 L120,65 L122,66 L123,67 L125,68 L132,75 L132,76 L134,78 L134,79 L135,80 L65,80 L65,79 L67,77 L67,76 L76,67 L78,66 L79,65 L83,63 L86,62 L88,61 L93,60 Z M248,50 L250,52 L250,97 L248,99 L202,99 L200,97 L200,52 L202,50 Z M135,80 L138,81 L140,82 L141,83 L143,84 L147,88 L147,89 L148,90 L148,91 L149,92 L149,94 L150,95 L150,104 L149,105 L149,107 L148,108 L148,109 L147,110 L147,111 L142,116 L138,118 L135,118 L136,117 L136,116 L137,115 L137,113 L138,112 L138,110 L139,109 L139,104 L140,103 L140,96 L139,95 L139,90 L138,89 L138,87 L137,86 L137,84 L136,83 L136,82 L135,81 L131,80 Z M98,80 L93,81 L89,83 L83,89 L83,90 L82,91 L82,92 L81,93 L81,96 L80,97 L80,102 L81,103 L81,106 L82,107 L82,108 L83,109 L83,110 L84,111 L84,112 L87,115 L93,118 L96,119 L96,120 L65,120 L65,119 L64,118 L64,117 L63,116 L63,115 L62,114 L62,112 L61,111 L61,108 L60,107 L60,92 L61,91 L61,88 L62,87 L62,85 L63,84 L63,83 L64,82 L64,81 L65,80 Z M128,80 L123,81 L119,83 L116,86 L116,88 L118,90 L118,91 L119,92 L119,94 L120,95 L120,104 L119,105 L119,107 L118,108 L118,109 L117,110 L117,111 L115,113 L117,115 L119,116 L120,117 L126,119 L126,120 L103,120 L103,119 L107,118 L111,116 L112,115 L114,114 L114,112 L113,111 L113,110 L112,109 L112,108 L111,107 L111,106 L110,105 L110,94 L111,93 L111,92 L112,91 L112,90 L113,89 L113,88 L114,87 L114,86 L111,83 L107,81 L101,80 Z M134,119 L134,120 L133,120 L133,119 Z M135,120 L133,122 L133,123 L124,132 L122,133 L121,134 L115,137 L112,138 L108,139 L91,139 L85,137 L79,134 L78,133 L76,132 L72,128 L70,127 L70,126 L67,123 L67,122 L65,120 Z",
            bmp.trace().toSvgString()
        )
    }
}
