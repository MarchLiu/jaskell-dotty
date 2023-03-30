package jaskell.croupier

import scala.util.Random
import scala.util.control.NonLocalReturns.{returning, throwReturn}

/**
 * Rand select by scale weight.
 *
 * @param scale  the trait that get item's weight
 * @param random random object, auto new one if no given
 * @tparam T type of card
 */
class Scaled[T](scale: Scale[T], val random: Random = new Random()) extends Poker[T] {
  override def select(cards: Seq[T]): Option[Int] = returning {
    if (cards == null || cards.isEmpty) {
      throwReturn(None)
    }
    if (cards.size == 1) {
      throwReturn(Some(0))
    }

    val steps: Seq[Int] = cards
      .map(scale.weight)
      .foldLeft(Seq[Int](0)) { (result: Seq[Int], r: Int) =>
        result :+ (result.last + r)
      }
    val top = steps.last
    val score = random.nextInt(top)
    for (idx <- cards.indices) {
      if (steps(idx) <= score && steps(idx + 1) > score) {
        throwReturn(Some(idx))
      }
    }
    Some(cards.size - 1)
  }
}
