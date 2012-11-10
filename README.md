kotlin-rational
===============

This is a simple rational number class for the [Kotlin](http://kotlin.jetbrains.org/) programming language.
It can be used to represent any number that can be expressed as one integer divided by another.  It has
the advantage that, unlike Floats or Doubles, it will not lose precision.

It takes advantage of Kotlin's operator overloading to allow you to use these Rational numbers as if they
were primitive types.  Rational numbers are immutable.

How do I install kotlin-rational
------------------------

QuickDT is distributed using Maven.  To use it in your project just add this repository to your pom.xml file:

```xml
    	<repository>
			<id>sanity-maven-repo</id>
			<name>Sanity's Maven repository on GitHub</name>
			<url>http://sanity.github.com/maven-repo/repository/</url>
		</repository>
```

And this dependency:

```xml
		<dependency>
			<groupId>us.locut</groupId>
			<artifactId>RationalNumber</artifactId>
			<version>1.0</version>
		</dependency>
```

Usage Example
=============
    Rational(1, 2) + Rational(1, 4)
    Rational(5, 6) * 3
    println(Rational(2, 6)) // This will print "1/3" because we always simplify if we can
    Rational(2, 3) > Rational(3, 4)

See [RationalTest.kt](https://github.com/sanity/kotlin-rational/blob/master/src/test/java/rational/RationalTest.kt) for
more usage examples.

License
=======
Copyright 2012 Ian Clarke.

Licensed under the Apache License, Version 2.0 (the "License"), you may not use this file except in compliance
with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0