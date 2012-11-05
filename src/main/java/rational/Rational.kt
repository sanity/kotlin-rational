/**
 * Created with IntelliJ IDEA.
 * User: ian
 * Date: 10/23/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */

package rational

import com.google.common.base.Objects

fun main(args: Array<String>): Unit {
    println(Rational("20.4") + Rational("15.2"))
}

fun Rational(str: String): Rational {
    val dpIx = str.indexOf('.')
    if (dpIx == -1) {
        return Rational(java.lang.Long.parseLong(str))
    }
    val raw = str.substring(0, dpIx) + str.substring(dpIx + 1, str.length)

    return Rational(java.lang.Long.parseLong(raw), pow10((str.length - dpIx).toLong()))
}

fun Rational(number : Number) : Rational =
     when (number) {
        is Int -> Rational(number.toLong(), 1)
        is Long -> Rational(number, 1)
        is Rational -> number
        else -> {
            // TODO: Make more efficient, should probably pull the double apart using Double.doubleToLongBits
            Rational(number.toDouble().toString())
        }
    }

fun gcf(a: Long, b: Long): Long = if (b == 0.toLong()) a else gcf(b, a % b)

private fun pow10(var exp: Long): Long {
    var result: Long = 1
    while (exp > 0) {
        if (exp and 1 > 0) {
            result *= 10
        }
        exp = exp shr 1
    }
    return result
}

class Rational(num: Long, denom: Long): Number() {
    val numerator : Long
    val denominator : Long
    {
        val absNum = Math.abs(num);
        val absDenom = Math.abs(denom);
        val gcd = gcf(absNum, absDenom)
        val sign = if ((num < 0) == (denom < 0)) 1 else -1
        numerator = (absNum / gcd) * sign
        denominator = absDenom / gcd
    }

    public override fun hashCode(): Int = Objects.hashCode(numerator, denominator)

    public override fun equals(other: Any?): Boolean {
        if (other is Rational) {
            return numerator * other.denominator == other.numerator * denominator
        } else {
            return false
        }
    }
    public override fun toFloat(): Float = numerator.toFloat() / denominator.toFloat()

    public override fun toLong(): Long = numerator / denominator

    public override fun toInt(): Int = (numerator / denominator) as Int

    public override fun toChar(): Char = (numerator / denominator) as Char

    public override fun toShort(): Short = (numerator / denominator) as Short

    public override fun toByte(): Byte = (numerator / denominator) as Byte

    public override fun toDouble(): Double = numerator.toDouble() / denominator.toDouble()

    public fun minus(other : Rational): Rational {
        val mn = numerator * other.denominator
        val md = other.numerator * denominator
        return Rational(mn - md, denominator * other.denominator)
    }

    public fun plus(other : Rational): Rational {
        val mn = numerator * other.denominator
        val md = other.numerator * denominator
        return Rational(mn + md, denominator * other.denominator)
    }

    public fun times(other: Long) : Rational = Rational(numerator * other, denominator * other)

    public fun times(other: Rational): Rational = Rational(numerator * other.numerator, denominator * other.denominator)

    public fun div(other: Rational): Rational = Rational(numerator * other.denominator, denominator * other.numerator)

    public fun plus(): Rational = this

    public fun toString() : String = "($numerator/$denominator)"
}