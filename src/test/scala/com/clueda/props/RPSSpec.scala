package com.clueda.props

import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks

import org.scalacheck._
import org.scalacheck.Gen

/**
 * Properties for the Rock Paper Scissors "game"
 */
class RPSSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  /** Generator for our Rock Paper Scissors data structure. */
  implicit val genRPS: Arbitrary[RockPaperScissors] = Arbitrary(Gen.oneOf(Rock,Paper,Scissors))

  // property("???") {
  //   forAll { rps: RockPaperScissors =>
  //     ???
  //   }
  // }

}
