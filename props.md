# Property Based Testing

## Motivation

* Unit tests only test example inputs and expected output
    - you will miss a lot of edge cases: weird numbers (infinity, NaN), chinese characters, etc
* Unit tests are tedious
    - you won't write as many as are necessary

Generate the tests!

> But what's the catch?

You will need to think more about what the *properties* of your functions are.

* What invariants hold for any input?
* How does the input relate to the output?

## Basic setup with ScalaTest

in `build.sbt`:

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.1" % "test", // testing framework
      "org.scalacheck" %% "scalacheck" % "1.11.6" % "test", // property tests
      )

in your test file:

    import org.scalatest._
    import org.scalatest.prop.GeneratorDrivenPropertyChecks
    import org.scalacheck._

    class MySpec extends PropSpec with GeneratorDrivenPropertyChecks {

        property("a number is equal to itself") {
          forAll { (a:Int) =>
            assert( a == a )
          }
        }

    }

# What will ScalaTest do for you?

* generate various random input for many data structures
* have a bias towards typical edge cases (0, negative, very large/small numbers, empty sets, etc)
* when a failing example is found, shrink the input to a minimal reproducible test input
* the same example might not reappear on next test run, so make a unit test or add it to a property table


## Examples

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


## Generators

* ScalaCheck contains various generators for standard data structures
* But user-defined data needs to be generated

Simple example: Rock Paper Scissors

    sealed trait RockPaperScissors
    case object Rock extends RockPaperScissors
    case object Paper extends RockPaperScissors
    case object Scissors extends RockPaperScissors

    implicit val genRPS: Arbitrary[RockPaperScissors] = Arbitrary(Gen.oneOf(Rock,Paper,Scissors))

More complex example: Recursive parameterized data structures, such as a linked list ...

      implicit def arbConsList[T](implicit a: Arbitrary[T]): Arbitrary[ConsList[T]] = {

        val genEmpty = Gen.const(Empty)
        def genCons: Gen[ConsList[T]] =
          for {
            h <- Arbitrary.arbitrary[T](a)
            t <- genConsList
          } yield Cons(h,t)

        def genConsList = Gen.frequency((1,genEmpty), (5,genCons))

        Arbitrary(genConsList)
      }

Don't worry, you don't need to do this yourself for regular collections!


## Some examples to think about

* average of 2 numbers
* addition of numbers
* database write/read
* hash function
* current time
* mixing colors
* snails having children (snails are usually hermaphroditic)
* Collatz conjecture



## References

* http://scalacheck.org/
* http://www.scalatest.org/user_guide/property_based_testing