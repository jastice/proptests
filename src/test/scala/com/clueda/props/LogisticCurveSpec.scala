package com.clueda.props

import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks


class LogisticCurveSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("logistic curve values are in range [-1,1]") {
    forAll { (t:Double) =>
      val x = logistic(t)
      assert( x >= -1 && x <= 1 )
    }
  }
}
