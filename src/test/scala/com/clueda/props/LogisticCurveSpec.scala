package com.clueda.props

import org.scalacheck.Gen
import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck.Arbitrary.arbitrary


class LogisticCurveSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("logistic curve values are in range [-1,1]") {
    forAll { (t:Double) =>
      val x = logistic(t)
      assert( x >= -1 && x <= 1 )
    }
  }

  property("logistic curve is monotonic increasing") {
    forAll (arbitrary[Double],Gen.posNum[Double]) { (a,b) =>
      val x1 = logistic(a)
      val x2 = logistic(a+b)
      assert( x1 <= x2 )
    }
  }

  property("logistic curve is dot-symmetric") {
    forAll { (t:Double) =>
      val x1 = logistic(t) - 0.5
      val x2 = logistic(-t) - 0.5
      assert( x1 == -x2 )
    }
  }

}
