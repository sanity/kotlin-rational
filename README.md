kotlin-rational
===============

This is a simple rational number class for the [Kotlin](http://kotlin.jetbrains.org/) programming language.
It can be used to represent any number that can be expressed as one integer divided by another.  It has
the advantage that, unlike Floats or Doubles, it will not lose precision.

It takes advantage of Kotlin's operator overloading to allow you to use these Rational numbers as if they
were primitive types.

Usage Example
=============
    Rational(1, 2) + Rational(1, 4)

See [RationalTest.kt](https://github.com/sanity/kotlin-rational/blob/master/src/test/java/rational/RationalTest.kt) for
more usage examples.

License
=======
Copyright 2012 Ian Clarke.

Licensed under the Apache License, Version 2.0 (the "License"), you may not use this file except in compliance
with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0