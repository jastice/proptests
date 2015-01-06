package props

import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks

/**
 * We have a weirdly behaved object with internal side-effects.
 */
class WeirdlyBehavedObjectSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("Weirdly Behaved Object accumulates numbers (hopefully!)") {
    forAll { (n: Int) =>
      val before = WeirdlyBehavedObject.acc
      WeirdlyBehavedObject.accumulate(n)
      val after = WeirdlyBehavedObject.acc

      assert( after == before+n )
    }
  }
}
