package com.clueda.props

import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks

import org.scalacheck._
import org.scalacheck.Gen

/**
 * Properties for the Rock Paper Scissors "game"
 */
class RPSSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  implicit val genRPS: Arbitrary[RockPaperScissors] = Arbitrary(Gen.oneOf(Rock,Paper,Scissors))

  property("RPS is commutative") {
    forAll { (rps1: RockPaperScissors, rps2: RockPaperScissors) =>
      assert( rockPaperScissors(rps1,rps2) == rockPaperScissors(rps2,rps1) )
    }
  }

  property("RPS is associative?") {
    // woops, this will fail
    forAll { (rps1: RockPaperScissors, rps2: RockPaperScissors, rps3: RockPaperScissors) =>
      assert( rockPaperScissors(rockPaperScissors(rps1, rps2), rps3) == rockPaperScissors(rps1, rockPaperScissors(rps2,rps3)))
    }
  }

}
