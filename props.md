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

    class ExampleSpec extends PropSpec with GeneratorDrivenPropertyChecks {

        property("a number is equal to itself") {
          forAll { (a:Int) =>
            assert( a == a )
          }
        }

    }

You can use any ScalaTest testing style, or use ScalaCheck standalone (with a different syntax)

# What will ScalaTest do for you?

* generate various random input for many data structures
* have a bias towards typical edge cases (0, negative, very large/small numbers, empty sets, etc)
* when a failing example is found, shrink the input to a minimal reproducible test input
* the same example might not reappear on next test run, so make a unit test or add it to a property table


## Examples

### A supposedly well-behaved simple function: GCD

We know or can guess some basic mathematical properties about GCD:

* associativity
* commutativity
* neutral element

This is also known as commutative monoid, and knowing this has some very practical and useful implications
for parallelization.

* Our tests should prove it really works.
* Does it?
* Where does it fail?

It looks like we will have to limit our assumptions. Doing this in the tests also is a form of
executable documentation.

We can also test some more specific properties:

### A continuous function on reals: Logistic curve

A one-argument function on Double.

Looking at the graph, it seems the following holds:

* strictly monotonic increasing
* all values are > -1 and < 1
* dot-symmetry around 0

Again, our data type does not quite fit the assumptions from pure math,
calculations with very large or small numbers yield slightly unexpected results:

* strict monotonicity is violated
* infinity-bounds are reached with regular numbers

### A weirdly behaved object with side effecty method

Side effects in general are harder to test, but we can still check some assumptions.


### Set operations

Set union has some very similar properties to, say, gcd.

* associativity
* commutativity
* neutral element

It also has another nice property: idempotence. This turns out to be useful for distributed systems,
and again, parallelization.


## Generators

* ScalaCheck contains various generators for standard data structures
* But user-defined data needs to be generated

### Rock Paper Scissors

Let's define our own datatype:

    sealed trait RockPaperScissors
    case object Rock extends RockPaperScissors
    case object Paper extends RockPaperScissors
    case object Scissors extends RockPaperScissors

This doesn't have any prebuilt generators, of course, but we can easily make our own:

    implicit val genRPS: Arbitrary[RockPaperScissors] = Arbitrary(Gen.oneOf(Rock,Paper,Scissors))

What properties does the `rockPaperScissors` function have?

* commutative?
* associative?
* neutral element?
* idempotency?
* anything else?


### List operations

Set and list operations have many interesting properties that you can check in terms of each other.

* concatenation of lists relates to their size
* concatenation is another monoid
* reverse retains size
* reversing a palindrome yields itself
* appending a reverse to itself creates a palindrome
* ...

For such home-grown recursive parameterized data structures, we need a more complex generator
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


## Caveat emptor!

* ScalaCheck will not necessarily generate all edge case values (currently no NaN, Infinity, etc)
* it's still probabilistic: sometimes a rare error will only occur on one run, but not the other
    -> make specific test cases when you find such an error
* property checks are checks, not proofs.
    -> They test your implementation for properties that you can reasonably infer.

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