package props

import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck._

class ExampleSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("an integer is equal to itself") {
    forAll { (a:Int) =>
      assert( a == a )
    }
  }

}
