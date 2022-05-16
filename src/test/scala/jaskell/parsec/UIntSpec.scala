package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Txt.uInteger

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 22:48
 */
class UIntSpec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Run a simple test" in {
    val state = "23413214".state

    val parser = uInteger

    val re = parser apply state

    re should be (Success("23413214"))
  }

  "Stop" should "Match digits until a letter" in {
    val state = "23413a214".state

    val parser = uInteger

    val re = parser apply state

    re should be (Success("23413"))
  }

  "Fail" should "Failed" in {
    val state = "x2344".state
    val parser = uInteger

    assert((parser apply state).isFailure)

  }

  "Auto" should "Match digits in string directed" in {
    val content = "23413a214"

    val parser = uInteger

    val re = parser(content)

    re should be (Success("23413"))
  }
}
