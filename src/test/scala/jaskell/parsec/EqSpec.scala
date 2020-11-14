package jaskell.parsec

import java.io.EOFException

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success
import jaskell.parsec.Atom._

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 10:02
 */
class EqSpec extends AnyFlatSpec with Matchers{
  "Eq" should "Test" in {
    val state = "hello".state

    val eof = state.eof

    (Eq('h') ? state) should be (Success('h'))
    (Eq('e') apply state) should be (Success('e'))
    (Eq('l') apply state) should be (Success('l'))
    (Eq('l') apply state) should be (Success('l'))

    assert((eqs('l').attempt apply state).isFailure)

    (Eq('o') apply state) should be (Success('o'))
    
    assert(eof(state).isSuccess)

    assert((Eq('o') apply state).isFailure)

  }

}
