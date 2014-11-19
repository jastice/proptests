package com.clueda.props

import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class UnionSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("set union is associative") {
    forAll { (a: Set[String], b: Set[String], c: Set[String]) =>
      assert( union(union(a,b),c) == union(a,union(b,c)) )
    }
  }

  property("set union is commutative") {
    forAll { (a: Set[String], b: Set[String]) =>
      assert( union(a,b) == union(b,a) )
    }
  }

  property("set union has neutral element: empty set") {
    forAll { (a: Set[Int]) =>
      assert( union(a,Set()) == a )
    }
  }

  property("set union of equal sets is equal to the sets") {
    forAll { (a: Set[Int]) =>
      assert( union(a,a) == a )
    }
  }

  property("sets are subsets of their union") {
    forAll { (a: Set[String], b: Set[String]) =>
      val u = union(a,b)
      assert( a.subsetOf(u) && b.subsetOf(u) )
    }
  }

}
