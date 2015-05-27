package props

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck._

class Rot13Spec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("rot13 is its own inverse (involution)") {
    forAll { (s: String) =>
      println(s)
      assert( s == rot13(rot13(s)) )
    }
  }

}
