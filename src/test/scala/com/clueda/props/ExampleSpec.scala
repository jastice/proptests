package com.clueda.props

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck._

class ExampleSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("a number is equal to itself") {
    forAll { (a:Int) =>
      assert( a == a )
    }
  }

}
