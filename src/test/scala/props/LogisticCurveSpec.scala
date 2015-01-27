package props

import org.scalacheck.Gen
import org.scalatest.PropSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck.Arbitrary.arbitrary


class LogisticCurveSpec extends PropSpec with GeneratorDrivenPropertyChecks {

  property("is between 0 and 1") {
    forAll { (a:Double) =>
      val logi = logistic(a)
      assert( logi > 0 && logi < 1 )
    }
  }
  property("logistic curve values are in range [-1,1]") {
    forAll { (t:Double) =>
      val x = logistic(t)
      assert( x > -1 && x < 1 )
    }
  }

  property("logistic curve is strictly monotonically increasing") {
    forAll { (a:Double, b:Double) =>
      whenever (b>0) {
        val x1 = logistic(a)
        val x2 = logistic(a+b)
        assert( x1 <= x2 )
      }
    }
  }

  property("logistic curve is dot-symmetric") {
    forAll { (t:Double) =>
      val a = 0.5-logistic(t)
      val b = logistic(-t)-0.5
      assert ( a == b )
    }
  }

}
