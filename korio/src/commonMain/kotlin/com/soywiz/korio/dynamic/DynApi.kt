package com.soywiz.korio.dynamic

import kotlin.native.concurrent.ThreadLocal

interface DynApi {
    val global: Any? get() = null

    fun get(instance: Any?, key: String): Any?
    fun set(instance: Any?, key: String, value: Any?)
    fun invoke(instance: Any?, key: String, args: Array<out Any?>): Any?

    fun getOrThrow(instance: Any?, key: String): Any? = get(instance, key)
    fun invokeOrThrow(instance: Any?, key: String, args: Array<out Any?>): Any? = invoke(instance, key, args)

    suspend fun suspendGet(instance: Any?, key: String): Any? = get(instance, key)
    suspend fun suspendSet(instance: Any?, key: String, value: Any?): Unit = set(instance, key, value)
    suspend fun suspendInvoke(instance: Any?, key: String, args: Array<out Any?>): Any? = invoke(instance, key, args)
}

val defaultDynApi: DynApi get() = DynamicInternal

// @TODO: We should be able to plug-in a kotlinx-serialization version for this
@ThreadLocal
var dynApi: DynApi = DynamicInternal

internal expect object DynamicInternal : DynApi
