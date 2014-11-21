/*
 * Copyright (C) 2014 Clueda AG.
 * This work is proprietary and confidential. Any distribution,
 * reproduction, or modification is strictly prohibited under any
 * circumstances without the express prior written permission of Clueda
 * AG. All rights reserved.
 */
package com.clueda.props


import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck._

class GCDSpec extends PropSpec with GeneratorDrivenPropertyChecks {


  // gcd(Int,Int): Int seems to form a (commutative) Monoid:
  // http://en.wikipedia.org/wiki/Monoid

  // Closure
  // For all a, b in A, the result of the operation a • b is also in A.
  // --> is guaranteed by the type gcd(Int,Int): Int
  // types are constructive proofs!


  // Associativity
  // For all a, b and c in A, the equation (a • b) • c = a • (b • c) holds.
  // this property means you can use this op in fold and map-reduce type algorithms
  // without much hassle
  property("gcd on positive Ints satisfies associativity") {
    forAll (Gen.posNum[Int], Gen.posNum[Int], Gen.posNum[Int]) { (a: Int, b: Int, c: Int) =>
      assert(gcd(gcd(a,b),c) == gcd(a,gcd(b,c)))
    }
  }


  // Commutativity
  // For all a, b in A, a • b = b • a.
  // this property means you can parallelize this operation even more generally
  property("gcd on positive Ints satisfies commutativity") {
    forAll (Gen.posNum[Int], Gen.posNum[Int]) { (a: Int, b: Int) =>
      assert( gcd(a,b) == gcd(b,a) )
    }
  }

  // Neutral Element
  // There exists an element e in G, such that for every element a in G, the equation e • a = a • e = a holds.
  // a neutral element makes it easy to fold
  property("gcd on all Ints should have neutral element 0") {
    forAll { (a:Int) =>
      assert( gcd(a,0) == a && a == gcd(0,a))
    }
  }

  // Idempotency
  property("gcd is idempotent") {
    forAll (Gen.posNum[Int]) { (a: Int) =>
      assert( gcd(a,a) == a )
    }
  }

  property("gcd(a,b) <= a and b for positive Ints") {
    forAll (Gen.posNum[Int], Gen.posNum[Int]) { (a:Int, b: Int) =>
      assert( gcd(a,b) <= a.min(b) )
    }
  }

  property("gcd is >= 1 for positive Ints") {
    forAll (Gen.posNum[Int], Gen.posNum[Int]) { (a:Int, b:Int) =>
      assert( gcd(a,b) >= 1 )
    }
  }

  // some properties are more specific to the function
  property("positive inputs reduced by their gcd are coprime") {
    forAll (Gen.posNum[Int], Gen.posNum[Int]) { (a:Int, b: Int) =>
      val res = gcd(a,b)
      val a1 = a/res
      val b1 = b/res
      assert( gcd(a1,b1) == 1 )
    }
  }

}
