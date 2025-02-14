package com.soywiz.kmem

public class ByteArrayReader(public val data: ByteArray, public val start: Int, public val size: Int = 0) {
    private var offset = start
    public val remaining: Int get() = size - offset
    public val hasMore: Boolean get() = remaining > 0

    private fun <T> move(count: Int, callback: ByteArray.(Int) -> T): T {
        val res = callback(data, this.offset)
        this.offset += count
        return res
    }

    public fun u8(): Int = move(1) { readU8(it) }
    public fun s8(): Int = move(1) { readS8(it) }

    public fun u16(little: Boolean): Int = move(2) { readU16(it, little) }
    public fun s16(little: Boolean): Int = move(2) { readS16(it, little) }
    public fun u16LE(): Int = move(2) { readU16LE(it) }
    public fun s16LE(): Int = move(2) { readS16LE(it) }
    public fun u16BE(): Int = move(2) { readU16BE(it) }
    public fun s16BE(): Int = move(2) { readS16BE(it) }

    public fun u24(little: Boolean): Int = move(3) { readU24(it, little) }
    public fun s24(little: Boolean): Int = move(3) { readS24(it, little) }
    public fun u24LE(): Int = move(3) { readU24LE(it) }
    public fun s24LE(): Int = move(3) { readS24LE(it) }
    public fun u24BE(): Int = move(3) { readU24BE(it) }
    public fun s24BE(): Int = move(3) { readS24BE(it) }

    public fun u32(little: Boolean): Long = move(4) { readU32(it, little) }
    public fun s32(little: Boolean): Int = move(4) { readS32(it, little) }
    public fun u32LE(): Long = move(4) { readU32LE(it) }
    public fun s32LE(): Int = move(4) { readS32LE(it) }
    public fun u32BE(): Long = move(4) { readU32BE(it) }
    public fun s32BE(): Int = move(4) { readS32BE(it) }

    public fun f16(little: Boolean): Float16 = move(2) { readF16(it, little) }
    public fun f16LE(): Float16 = move(2) { readF16LE(it) }
    public fun f16BE(): Float16 = move(2) { readF16BE(it) }
    public fun f32(little: Boolean): Float = move(4) { readF32(it, little) }
    public fun f32LE(): Float = move(4) { readF32LE(it) }
    public fun f32BE(): Float = move(4) { readF32BE(it) }
    public fun f64(little: Boolean): Double = move(8) { readF64(it, little) }
    public fun f64LE(): Double = move(8) { readF64LE(it) }
    public fun f64BE(): Double = move(8) { readF64BE(it) }
}

public inline class ByteArrayReaderLE(public val bar: ByteArrayReader)

public val ByteArrayReaderLE.size: Int get() = bar.size
public val ByteArrayReaderLE.remaining: Int get() = bar.remaining
public val ByteArrayReaderLE.hasMore: Boolean get() = bar.hasMore
public fun ByteArrayReaderLE.u8(): Int = bar.u8()
public fun ByteArrayReaderLE.s8(): Int = bar.s8()
public fun ByteArrayReaderLE.u16(): Int = bar.u16LE()
public fun ByteArrayReaderLE.s16(): Int = bar.s16LE()
public fun ByteArrayReaderLE.u24(): Int = bar.u24LE()
public fun ByteArrayReaderLE.s24(): Int = bar.s24LE()
public fun ByteArrayReaderLE.u32(): Long = bar.u32LE()
public fun ByteArrayReaderLE.s32(): Int = bar.s32LE()
public fun ByteArrayReaderLE.f16(): Float16 = bar.f16LE()
public fun ByteArrayReaderLE.f32(): Float = bar.f32LE()
public fun ByteArrayReaderLE.f64(): Double = bar.f64LE()

public inline class ByteArrayReaderBE(public val bar: ByteArrayReader)

public val ByteArrayReaderBE.size: Int get() = bar.size
public val ByteArrayReaderBE.remaining: Int get() = bar.remaining
public val ByteArrayReaderBE.hasMore: Boolean get() = bar.hasMore
public fun ByteArrayReaderBE.u8(): Int = bar.u8()
public fun ByteArrayReaderBE.s8(): Int = bar.s8()
public fun ByteArrayReaderBE.u16(): Int = bar.u16BE()
public fun ByteArrayReaderBE.s16(): Int = bar.s16BE()
public fun ByteArrayReaderBE.u24(): Int = bar.u24BE()
public fun ByteArrayReaderBE.s24(): Int = bar.s24BE()
public fun ByteArrayReaderBE.u32(): Long = bar.u32BE()
public fun ByteArrayReaderBE.s32(): Int = bar.s32BE()
public fun ByteArrayReaderBE.f16(): Float16 = bar.f16BE()
public fun ByteArrayReaderBE.f32(): Float = bar.f32BE()
public fun ByteArrayReaderBE.f64(): Double = bar.f64BE()

public fun ByteArray.reader(offset: Int = 0, size: Int = this.size): ByteArrayReader = ByteArrayReader(this, offset, size)
public fun ByteArray.readerLE(offset: Int = 0, size: Int = this.size): ByteArrayReaderLE = ByteArrayReaderLE(reader(offset, size))
public fun ByteArray.readerBE(offset: Int = 0, size: Int = this.size): ByteArrayReaderBE = ByteArrayReaderBE(reader(offset, size))

public fun <T> ByteArray.read(offset: Int = 0, size: Int = this.size, callback: ByteArrayReader.() -> T): T =
    callback(reader(offset, size))

public fun <T> ByteArray.readLE(offset: Int = 0, size: Int = this.size, callback: ByteArrayReaderLE.() -> T): T =
    callback(readerLE(offset, size))

public fun <T> ByteArray.readBE(offset: Int = 0, size: Int = this.size, callback: ByteArrayReaderBE.() -> T): T =
    callback(readerBE(offset, size))
