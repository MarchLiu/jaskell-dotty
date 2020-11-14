package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 20:02
 */
class NoneOfSpec extends AnyFlatSpec with Matchers {

  "Simple OK" should "Test success" in {
    val state = "hello".state
    val nof = NoneOf(Seq('k', 'o', 'f').toSet)
    val c = nof(state)
    c should be (Success('h'))
  }

  "Simple Failed" should "Test failed" in {
    val state = "sound".state
    val nof = NoneOf(Seq('k', 'f', 's').toSet)
    assert(nof(state).isFailure)
  }
}
