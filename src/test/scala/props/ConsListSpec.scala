package props

import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks

import org.scalacheck._
import org.scalacheck.Gen

/**
 * Properties for the ConsList datastructure and functions
 */
class ConsListSpec extends PropSpec with GeneratorDrivenPropertyChecks {


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

  property("size is non-negative") {
    ???
  }

  // TODO find kore properties!

}
