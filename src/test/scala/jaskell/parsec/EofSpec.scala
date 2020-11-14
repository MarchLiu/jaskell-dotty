package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 10:02
 */
class EofSpec extends AnyFlatSpec with Matchers{
  import Txt._
  import Atom.eof
  "Eof" should "Test" in {
    val state = "hello".state

    val e = eof[Char]

    Text("hello") apply state
    assert(e(state).isSuccess)
  }

  "Eof Exception" should "Throw exception" in{
    val state = "hello".state

    val e = new Eof[Char]

    assert(e(state).isFailure)
  }
}
