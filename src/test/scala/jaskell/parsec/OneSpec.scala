package jaskell.parsec

import java.io.EOFException

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Atom.one

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 20:48
 */
class OneSpec extends AnyFlatSpec with Matchers{
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Simple" should "Match one item" in {
    val state = "hello".state
    val parser = one[Char]
    (parser apply state) should be (Success('h'))
    (parser apply state) should be (Success('e'))
    (parser apply state) should be (Success('l'))
    (parser apply state) should be (Success('l'))
    (parser apply state) should be (Success('o'))
    assert(parser(state).isFailure)

  }
}
