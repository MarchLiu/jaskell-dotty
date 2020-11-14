package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import jaskell.parsec.Combinator.skip1
import jaskell.parsec.Txt.text

import scala.util.{Try, Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 22:13
 */
class Skip1Spec extends AnyFlatSpec with Matchers{
  "Simple" should "Just run a simple test" in {
    val state = "left right left right".state
    val parser = skip1(text("left"))
    parser ? state
    state.status  should be (4)
  }

  "Status More" should "Test status after twice matches" in {
    val state = "left left right left right".state
    val parser = skip1(text("left "))
    parser ? state
    state.status  should be (10)
  }

  "Fail" should "Failed after twice matches" in {
    val state = "right left right left right".state
    val parser = skip1(text("left "))
    assert((parser ? state).isFailure)
  }
}
