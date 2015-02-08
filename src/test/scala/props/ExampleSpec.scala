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

  // property("adding 1 to a double increases it") {
  //   forAll { (a:Double) =>
  //     assert( a < a+1 )
  //   }
  // }  

}
