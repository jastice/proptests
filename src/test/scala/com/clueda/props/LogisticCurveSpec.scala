package com.clueda.props

import org.scalacheck.Gen
import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck.Arbitrary.arbitrary


class LogisticCurveSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("your favorite property") {
    forAll { (t:Double) =>
      ???
    }
  }
}
