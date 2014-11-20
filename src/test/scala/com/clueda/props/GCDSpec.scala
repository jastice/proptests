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

  property("gcd(a,b) <= a and b") {
    forAll { (a:Int, b: Int) =>
      ???
    }
  }

  // property("gcd is >= 1") {
  //   forAll { (a:Int, b:Int) =>
  //     ???
  //   }
  // }

  // gcd(Int,Int): Int seems to form a (commutative) Monoid:
  // http://en.wikipedia.org/wiki/Monoid

  // Closure
  // For all a, b in A, the result of the operation a • b is also in A.
  // --> is guaranteed by the type gcd(Int,Int): Int
  // types are proofs!


  // Associativity
  // For all a, b and c in A, the equation (a • b) • c = a • (b • c) holds.
  // this property means you can use this op in fold and map-reduce type algorithms
  // without much hassle

  // property("gcd on positive Ints satisfies associativity") {
  //   forAll { (a: Int, b: Int, c: Int) =>
  //     ???
  //   }
  // }


  // Commutativity
  // For all a, b in A, a • b = b • a.
  // this property means you can parallelize this operation even more generally
  
  // property("gcd on positive Ints satisfies commutativity") {
  //   forAll (Gen.posNum[Int], Gen.posNum[Int]) { (a: Int, b: Int) =>
  //     ???
  //   }
  // }


  // Neutral Element
  // There exists an element e in G, such that for every element a in G, the equation e • a = a • e = a holds.
  // a neutral element makes it easy to fold

  // property("gcd on all Ints should have neutral element 0") {
  //   forAll { (a:Int) =>
  //     ???
  //   }
  // }


  // Idempotency
  // A binary operation is idempotent if, whenever it is applied to two equal values, it gives that value as the result.
  // super practical property for distributed computations

  // property("gcd is idempotent") {
  //   forAll { (a: Int) =>
  //     ???
  //   }
  // }


  // some properties are more specific to the function
  // property("positive inputs reduced by their gcd are coprime") {
  //   forAll { (a:Int, b: Int) =>
  //     ???
  //   }
  // }

}
