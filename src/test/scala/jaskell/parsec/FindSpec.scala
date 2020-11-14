package jaskell.parsec

import jaskell.parsec.Combinator._
import jaskell.parsec.Txt._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 18:56
 */
class FindSpec extends AnyFlatSpec with Matchers {
  "Simple" should " find token in content" in {
    val data = "It is a junit test case for find parsec.".state

    val parser = Find(text("find"))
    val re = parser ? data
    re should be (Success("find"))
  }

  "Failed" should "mismatch any content" in {
    val data = "It is a junit test case for find parsec.".state
    val parsec = find(text("Fail"))
    
    assert((parsec ? data).isFailure)
    
  }
}
