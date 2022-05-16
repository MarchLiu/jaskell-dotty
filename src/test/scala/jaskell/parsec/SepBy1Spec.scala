package jaskell.parsec

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import jaskell.parsec.Txt.ch
import scala.util.{Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/05/12 21:31
 */
class SepBy1Spec extends AnyFlatSpec with Matchers {
  import State.Instances.{given, *}
  import Parsec.Instances.{given, *}
  "Basic" should "Run some basic tests" in {
    val state = "hlhlhlhlhlhll".state
    val p = SepBy1(ch('h'), ch('l'))

    val re = p(state)
    re.map(_.size) should be(Success(6))
    re.get.foreach(item => {
      item should be('h')
    })
  }

  "2 Times" should "Match 2 times sepBy1" in {
    val state = "hlh,h.hlhlhll".state
    val p = SepBy1(ch('h'), ch('l'))

    val re = p(state)
    re.map(_.size) should be(Success(2))
    re.get foreach { item =>
      item should be('h')
    }
  }

  "Fail" should "Failed at " in {
    val state = "hlh,h.hlhlhll".state
    val p = SepBy1(ch('h'), ch('l'))
    p(state)
    assert((p apply state).isFailure)
  }
}
