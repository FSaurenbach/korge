package com.soywiz.korio.compression

import com.soywiz.kmem.readIntArrayLE
import com.soywiz.korio.compression.deflate.Deflate
import com.soywiz.korio.compression.deflate.DeflatePortable
import com.soywiz.korio.compression.deflate.GZIP
import com.soywiz.korio.compression.deflate.GZIPNoCrc
import com.soywiz.korio.compression.deflate.ZLib
import com.soywiz.korio.lang.UTF8
import com.soywiz.korio.lang.toByteArray
import com.soywiz.korio.lang.toString
import com.soywiz.krypto.encoding.fromBase64
import kotlin.test.Test
import kotlin.test.assertEquals

class CompressionJvmTest {
	val compressedData =
		"H4sIAAAAAAAAA+3SsREAEBSD4WcFm2ACTID9dxGFxgDcub/4mjQpEmdmDuYPKwsSJT3qz1KkXu7fWZMu4/IGr78AAAAAAD+a6ywcnAAQAAA=".fromBase64()
	val expectedData = "" +
			"1111111111111111111111111111111111111111111111111111111111111111111818181814950511111111111111111111111111818181816566671111111" +
			"1111111111111111118181811818283111111111111111111111111118181111111111111111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
			"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
			"111111111111111111111111111111"

	@Test
	fun gzip() {
		val data = compressedData
		val res = data.uncompress(GZIPNoCrc)
		val res2 = res.readIntArrayLE(0, 4096 / 4)
		val actualData = res2.toList().joinToString("")
		if (expectedData != actualData) {
			println("EX: $expectedData")
			println("AC: $actualData")
		}
		assertEquals(expectedData, actualData)
	}

	@Test
	fun gzip2() {
		val data = compressedData
		val res = data.uncompress(GZIPNoCrc)
		val res2 = res.readIntArrayLE(0, 4096 / 4)
		assertEquals(expectedData, res2.toList().joinToString(""))
	}

	@Test
	fun compressGzipNoCrcSync() = compressSync(GZIPNoCrc)

	@Test
	fun compressGzipSync() = compressSync(GZIP)

	@Test
	fun compressZlibSync() = compressSync(ZLib)

	@Test
	fun compressDeflatePortableSync() = compressSync(DeflatePortable)

	@Test
	fun compressDeflateSync() = compressSync(Deflate)

	fun compressSync(method: CompressionMethod) {
		val str = "HELLO HELLO HELLO!"
		val uncompressed = str.toByteArray(UTF8)
		val compressed = uncompressed.compress(method)
		val decompressed = compressed.uncompress(method)
		assertEquals(decompressed.toString(UTF8), str, "With $method")
	}
}
