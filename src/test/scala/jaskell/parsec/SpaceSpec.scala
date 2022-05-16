package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Txt.space

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 22:29
 */
class SpaceSpec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Run a simple test" in {
    val state = " ".state
    val s = space
    val a = s(state)
    a should be (Success(' '))
  }

  "Fail" should "Failed when test" in {
    val state = "\t".state;
    val s = space
    assert((s apply state).isFailure)

  }
}
