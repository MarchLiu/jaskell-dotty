package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/11 10:50
 */
class AheadSpec extends AnyFlatSpec with Matchers {

  import Txt.{text, space}
  import Combinator.ahead

  val content: String = "this is a string data."
  "Simple" should "Expect status stop after this" in {
    val state = content.state
    val parser = text("this") <* ahead(text(" is"))
    
    parser(state) should be (Success("this"))
    state.status should be (4)
  }

  "Then" should "Check status get result and stop at is" in {
    val state = content.state
    val parser = text("this") *> space *> ahead(text("is"))

    val re = parser(state)
    re should be (Success("is"))
    state.status should be(5)
  }

  "Fail" should "throw parsec exception from parser" in {
    val state = content.state
    val parser = text("this") *> space *> ahead(text(" is"))

    assert((parser ? state).isFailure)

  }
}
