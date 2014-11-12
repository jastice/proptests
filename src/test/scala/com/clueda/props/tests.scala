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

class GCDSpec extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "gcd" should "be <= input values" in {

  }

  it should "always be >= 1" in {

  }

  // gcd(Int,Int) seems to form a (commutative) Monoid:
  // http://en.wikipedia.org/wiki/Monoid

  // closure
  // For all a, b in A, the result of the operation a • b is also in A.
  // -- is guaranteed by the type gcd(Int,Int): Int



  // Associativity
  // For all a, b and c in A, the equation (a • b) • c = a • (b • c) holds.
  // this property means you can use this op in fold and map-reduce type algorithms
  // without much hassle
  it should "satisfy associativity" in {

  }


  // Commutativity
  // For all a, b in A, a • b = b • a.
  // this property means you can parallelize this operation even more generally
  it should "satisfy commutativity" in {


  }

  // Neutral Element
  // There exists an element e in G, such that for every element a in G, the equation e • a = a • e = a holds.
  // a neutral element makes it easy to fold
  it should "have neutral element: 0" in {

  }

  // Inverse element
  // For each a in A, there exists an element b in A such that a • b = b • a = e, where e is the identity element.
  // oops, gcd has no inverse!

  it should "ensure coprime-ness of arguments divided by their gcd" in {

  }

}