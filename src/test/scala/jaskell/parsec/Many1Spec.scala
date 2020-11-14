package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import jaskell.parsec.Combinator._
import jaskell.parsec.Txt._

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 19:22
 */
class Many1Spec extends AnyFlatSpec with Matchers{
  "One" should "Get first char and return" in {
    val state = "hello".state
    val parser = many1(ch('h'))
    val re = parser(state)
    re.map(_.head) should be (Success('h'))
  }

  "All" should "Success all content" in {
    val state = "hello".state
    val parser = many1(text("hello"))
    val re = parser(state)
    re.map(_.head) should be (Success("hello"))
  }

  "Failed" should "Throw error " in {
    val state = "Hello".state
    val parser = many1(ch('h'))
    assert(parser(state).isFailure)
  }
}
