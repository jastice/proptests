# Property Based Testing and Fuzzing

## Properties

### Motivation

* Unit tests only test example inputs and expected output
    - you will miss a lot of edge cases: weird numbers (infinity, NaN), chinese characters, etc
* Unit tests are tedious
    - you won't write as many as are necessary

Generate the tests!

> But what's the catch?

You will need to think more about what the *properties* of your functions are.

* What invariants hold for any input?
* How does the input relate to the output?

### Basic setup with ScalaTest

in `build.sbt`:

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.1" % "test", // testing framework
      "org.scalacheck" %% "scalacheck" % "1.11.6" % "test", // property tests
      )

## What will ScalaTest do for you?

* generate various random input for many data structures
* have a bias towards typical edge cases (0, negative, verly large/small numbers, empty sets, etc)
* when a failing example is found, shrink the input to a minimal reproducible test input
* the same example might not reappear on next test run, so make a unit test or add it to a property table

### Examples

* plain math: GCD
* continuous functions
    - logistic curve
* commutative/associative operations: set ops / list ops
    - set unions/intersections: commutative
    - list appending: associative
    - neutral elements
* recursive data structures: linked list
* stack programming: summing identical elements
* merging maps

### Generators

* ScalaCheck contains various generators for standard data structures
* But user-defined data needs to be generated

{some recursive data structure as example}

## Fuzzing

* A loosely related concept
* Feeding "random" input

### Cool example

That jpeg generating from just a "hello" seed

### Practical example

TODO

## References

* http://scalacheck.org/
* http://www.scalatest.org/user_guide/property_based_testing
* http://en.wikipedia.org/wiki/Fuzz_testing
* https://code.google.com/p/american-fuzzy-lop/
    - http://lcamtuf.blogspot.de/2014/11/pulling-jpegs-out-of-thin-air.html
* https://www.owasp.org/index.php/JBroFuzz

