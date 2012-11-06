package rational

import org.junit.Test
import kotlin.test.*

/**
 * Created with IntelliJ IDEA.
 * User: ian
 * Date: 11/6/12
 * Time: 8:08 AM
 * To change this template use File | Settings | File Templates.
 */

class RationalTest {
    Test fun add2RationalsTest() {
        expect (Rational(3, 4)) {
            Rational(1, 2) + Rational(1, 4)
        }
    }

    Test fun subtract2RationalsTest() {
        expect (Rational(3, 4)) {
            Rational(1, 1) - Rational(1, 4)
        }
    }

    Test fun multiply2RationalsTest() {
        expect (Rational(1, 4)) {
            Rational(1, 2) * Rational(1, 2)
        }
    }

    Test fun divide2RationalsTest() {
        expect (Rational(1, 4)) {
            Rational(1, 2) / Rational(2, 1)
        }
    }

    Test fun simplifyTest() {
        expect (Rational(1, 2)) {Rational(2, 4)}
    }

    Test fun doubleConvertTest() {
        expect (Rational(1, 2)) {Rational(0.5)}
    }
}
