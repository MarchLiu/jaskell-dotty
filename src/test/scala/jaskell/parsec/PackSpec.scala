package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 21:18
 */
class PackSpec extends AnyFlatSpec with Matchers{
  "Simple" should "Just pack a value" in {
    val state = "Hello World".state
    val returns: Parsec[Char, BigDecimal] = Pack(BigDecimal("3.1415926"))
    val status = state.status
    val re = returns(state)
    re should be (Success(BigDecimal("3.1415926")))
    state.status should be (status)
  }

  "Pack" should "Pack a value by state" in {
    val state = "Hello World".state
    val returns: Parsec[Char, BigDecimal] = state.pack(BigDecimal("3.1415926"))
    val status = state.status
    val re = returns(state)
    re should be (Success(BigDecimal("3.1415926")))
    state.status should be (status)
  }
}
