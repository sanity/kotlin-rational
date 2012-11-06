/**
 * Created with IntelliJ IDEA.
 * User: ian
 * Date: 10/23/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */

package rational

fun main(args: Array<String>): Unit {
    println(5 * (15 * Rational("20.4") + Rational("15.2")))
}

fun Rational(str: String): Rational {
    val dpIx = str.indexOf('.')
    if (dpIx == -1) {
        return Rational(java.lang.Long.parseLong(str))
    }
    val raw = str.substring(0, dpIx) + str.substring(dpIx + 1, str.length)

    return Rational(java.lang.Long.parseLong(raw), pow10((str.length - dpIx).toLong()))
}

fun Rational(number: Number): Rational =
        when (number) {
            is Int -> Rational(number.toLong(), 1)
            is Long -> Rational(number, 1)
            is Rational -> number
            else -> {
                if (number is Double || number is Float) {
                    require (!java.lang.Double.isInfinite(number.toDouble()) && !java.lang.Double.isNaN(number.toDouble()))
                }
                // Stolen from http://goo.gl/4oXPj
                val numberAsDouble = number.toDouble()
                if (numberAsDouble == 0.0) {
                    Rational(0, 1)
                } else {
                    val bits = java.lang.Double.doubleToLongBits(numberAsDouble)
                    val sign = bits.ushr(63).toLong()
                    val exponent = (((bits.ushr(52)) xor (sign shl 11)) - 1023).toLong()
                    val fraction = (bits shl 12).toLong()
                    var a = 1.toLong()
                    var b = 1.toLong()
                    var i = 63
                    while (i >= 12) {
                        a = a * 2.toLong() + ((fraction.ushr(i)) and 1)
                        b *= 2.toLong()
                        i--
                    }
                    if (exponent > 0)
                        a *= 1 shl exponent.toInt()
                    else
                        b *= 1 shl -exponent.toInt()
                    if (sign == 1.toLong())
                        a *= -1
                    Rational(a, b)
                }
            }
        }

private fun gcd(var a: Long, var b: Long): Long  = if (b == 0.toLong()) a else gcd(b, a % b)

public fun Rational.minus(): Rational = Rational(-numerator, denominator)
public fun Rational.plus(): Rational = this

public fun Number.plus(r: Rational): Rational = r + Rational(this)
public fun Number.minus(r: Rational): Rational = r - Rational(this)
public fun Number.times(r: Rational): Rational = r * Rational(this)
public fun Number.div(r: Rational): Rational = r / Rational(this)

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

class Rational(num: Long, denom: Long): Number(), Comparable<Rational> {

    val numerator: Long
    val denominator: Long
    {
        require (denom != 0.toLong())
        val absNum = Math.abs(num);
        val absDenom = Math.abs(denom);
        val gcd = gcd(absNum, absDenom)
        val sign = if ((num < 0) == (denom < 0)) 1 else -1
        numerator = (absNum / gcd) * sign
        denominator = absDenom / gcd
    }

    public override fun hashCode(): Int = numerator.hashCode() * 31 + denominator.hashCode();

    public override fun equals(other: Any?): Boolean {
        if (other is Rational) {
            return numerator * other.denominator == other.numerator * denominator
        } else {
            return false
        }
    }

    public override fun compareTo(other: Rational): Int {
        val mNum = numerator * other.denominator;
        val oNum = other.numerator * denominator;
        return mNum.compareTo(oNum)
    }

    public override fun toFloat(): Float = numerator.toFloat() / denominator.toFloat()

    public override fun toLong(): Long = numerator / denominator

    public override fun toInt(): Int = (numerator / denominator) as Int

    public override fun toChar(): Char = (numerator / denominator) as Char

    public override fun toShort(): Short = (numerator / denominator) as Short

    public override fun toByte(): Byte = (numerator / denominator) as Byte

    public override fun toDouble(): Double = numerator.toDouble() / denominator.toDouble()

    public fun plus(other: Number): Rational {
         return when (other) {
             is Rational -> {
                 val mn = numerator * other.denominator
                 val md = other.numerator * denominator
                 Rational(mn + md, denominator * other.denominator)
             }
             else -> {
                 this + Rational(other)
             }
         }
     }

    public fun minus(other: Number): Rational {
        return when (other) {
            is Rational -> {
                val mn = numerator * other.denominator
                val md = other.numerator * denominator
                Rational(mn - md, denominator * other.denominator)
            }
            else -> {
                this - Rational(other)
            }
        }
    }


    public fun times(other: Number): Rational {
        return when (other) {
            is Rational -> {
                Rational(numerator * other.numerator, denominator * other.denominator)
            }
            else -> {
                this * Rational(other)
            }
        }
    }

    public fun div(other: Number): Rational {
        return when (other) {
            is Rational -> {
                Rational(numerator * other.denominator, denominator * other.numerator)
            }
            else -> {
                this / Rational(other)
            }
        }
    }

    public fun plus(): Rational = this

    public fun toString(): String = if (denominator != 1.toLong()) "($numerator/$denominator)" else "$numerator"
}