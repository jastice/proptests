package props


import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck._

class GCDSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  // domain for which our properties hold
  implicit val domain = Arbitrary(Gen.posNum[Int])

  property("smaller a and b") {
    forAll { (a:Int,b:Int) =>
      val g = gcd(a,b)
      assert( g <= a && g <= b )
    }
  }

  property("commutativity") {
    forAll { (a:Int,b:Int) =>
      assert( gcd(a,b) == gcd(b,a) )
    }
  }

  property("associativity") {
    forAll { (a:Int,b:Int,c:Int) =>
      assert( gcd(gcd(a,b),c) == gcd(a,gcd(b,c)) )
    }
  }

  property("gcd is a divisor of a and b") {
    forAll { (a:Int,b:Int) =>
      val g = gcd(a,b)
      assert( a % g == 0 )
    }
  }

  property("distributivity over multiplication") {
    forAll { (a:Int,b:Int,m:Int) =>
      assert( gcd(a*m,b*m) == m*gcd(a,b) )
    }
  }




  // gcd(Int,Int): Int seems to form a (commutative) Monoid:
  // http://en.wikipedia.org/wiki/Monoid

  // Closure
  // For all a, b in A, the result of the operation a • b is also in A.
  // --> is guaranteed by the type gcd(Int,Int): Int
  // types are constructive proofs!
  // well not quite guaranteed, because null.


  // Associativity
  // For all a, b and c in A, the equation (a • b) • c = a • (b • c) holds.
  // this property means you can use this op in fold and map-reduce type algorithms
  // without much hassle
  property("gcd on positive Ints satisfies associativity") {
    ???
  }


  // Commutativity
  // For all a, b in A, a • b = b • a.
  // this property means you can parallelize this operation even more generally
  property("gcd on positive Ints satisfies commutativity") {
    ???
  }

  // Neutral Element
  // There exists an element e in G, such that for every element a in G, the equation e • a = a • e = a holds.
  // a neutral element makes it easy to fold
  property("gcd on all Ints should have neutral element 0") {
    ???
  }

  // Idempotency
  property("gcd is idempotent") {
    ???
  }

  property("gcd(a,b) <= a and b for positive Ints") {
    ???
  }

  property("gcd is >= 1 for positive Ints") {
    ???
  }

  // some properties are more specific to the function
  property("positive inputs reduced by their gcd are coprime") {
    ???
  }

}
