package com.soywiz.korio.util

import com.soywiz.korio.async.suspendTest
import com.soywiz.korio.file.std.MemoryVfsMix
import kotlin.test.Test
import kotlin.test.assertEquals

class PropsTest {
	@Test
	fun test() = suspendTest {
		val vfs = MemoryVfsMix(
			"hello.properties" to """
    			hello=world
			""".trimIndent()
		)
		val props = vfs["hello.properties"].loadProperties()
		assertEquals("world", props["hello"])
		props["demo"] = "test"
		assertEquals("hello=world\ndemo=test", props.serialize())
		vfs["world.properties"].saveProperties(props)
		assertEquals("hello=world\ndemo=test", vfs["world.properties"].readString())
	}
}
