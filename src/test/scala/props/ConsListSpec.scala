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

    forAll { list: ConsList[Int] =>
      assert( size(list) >= 0 )
    }
  }

  property("size of appended lists is combined size of lists") {

    // we need to know this is true for induction
    assert(size(Empty) == 0)

    forAll { (a: ConsList[String], b: ConsList[String]) =>
      assert( size(append(a,b)) == size(a) + size(b) )
    }
  }

  property("append is associative") {
    forAll { (a: ConsList[String], b: ConsList[String], c: ConsList[String]) =>
      assert( append(a, append(b,c)) == append(append(a,b),c) )
    }
  }

  property("append has neutral element: Empty") {
    forAll { (list: ConsList[String]) =>
      // append isn't commutative, so we need to check both ways
      assert( append(Empty,list) == list && append(list,Empty) == list )
    }
  }

  property("appended result starts with first argument") {
    forAll { (a: ConsList[String], b: ConsList[String]) =>
      assert( startsWith(append(a,b), a) )
    }
  }

  property("size(append(a,b)) is commutative") {
    forAll { (a: ConsList[String], b: ConsList[String]) =>
      assert( size(append(a,b)) == size(append(b,a)) )
    }
  }

  property("every list starts with empty") {
    forAll { (a: ConsList[String]) =>
      assert( startsWith(a, Empty) )
    }
  }

  property("reverse retains size") {
    forAll { (a: ConsList[Int]) =>
      assert( size(reverse(a)) == size(a) )
    }
  }

  property("reversed reverse list is original") {
    forAll { (a: ConsList[Int]) =>
      assert( reverse(reverse(a)) == a )
    }
  }

  property("append list to its reverse is palindromic") {
    forAll { (a: ConsList[Int]) =>
      assert( append(a, reverse(a)) == reverse(append(a, reverse(a))) )
    }
  }

  property("sumRepeated contains no repeated elements") {

    def containsRep(list: ConsList[Int]): Boolean =
      list match {
        case Empty => false
        case Cons(h1, Cons(h2, tail)) if h1 == h2 => true
        case Cons(h,t) => containsRep(t)
      }

    forAll { (a: ConsList[Int]) =>
      assert( !containsRep(sumRepeated(a)) )
    }
  }

}
