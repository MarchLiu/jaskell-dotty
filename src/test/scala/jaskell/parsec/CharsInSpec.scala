package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 22:48
 */
class CharsInSpec extends AnyFlatSpec with Matchers {
  import jaskell.Monad.Instances.{given, *}
  import jaskell.parsec.Parsec.Instances.{given, *}
  import jaskell.parsec.State
  import jaskell.parsec.State.Instances.{given, *}

  "Simple" should "Run a simple test" in {
    val state = "23413214".state

    val parser = CharsIn("1234567890")

    val re = parser ! state

    re should be ("23413214")
  }

  "Part" should "Run a match part" in {
    val state = "234234abdef2342334".state

    val parser = CharsIn("1234567890")

    val re = parser ! state

    re should be ("234234")
  }

}
