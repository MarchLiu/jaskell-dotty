package jaskell.parsec

import jaskell.parsec.Txt.uInteger
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 22:48
 */
class IntSpec extends AnyFlatSpec with Matchers {
  import Txt.integer
  import scala.util.Success
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}

  "Simple" should "Run a simple test" in {
    val state = "23413214".state

    val parser = integer

    val re = parser apply state

    re should be (Success("23413214"))
  }

  "Negative Simple" should "Run a simple test" in {
    val state = "-23413214".state

    val parser = integer

    val re = parser ? state

    re should be (Success("-23413214"))
  }

  "Stop" should "Match digits until a letter" in {
    val state = "23413a214".state

    val parser = integer

    val re = parser apply state

    re should be (Success("23413"))
  }

  "Negative Stop" should "Match negative digits until a letter" in {
    val state = "-23413a214".state

    val parser = integer

    val re = parser apply state

    re should be (Success("-23413"))
  }

  "Negative Fail" should "Failed" in {
    val state = "-x2344".state
    val parser = integer
    assert(parser(state).isFailure)
  }
}
