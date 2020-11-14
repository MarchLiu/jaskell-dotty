package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Try, Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 21:40
 */
class SepBySpec extends AnyFlatSpec with Matchers {
  "Basic" should "Run some basic tests" in {
    val state = "hlhlhlhlhlhll".state
    val p = SepBy(Eq('h'), Eq('l'))

    val re = p apply state
    re.map(_.size) should be (Success(6))
    re.get foreach { item =>
      item should be ('h')
    }
  }
}
