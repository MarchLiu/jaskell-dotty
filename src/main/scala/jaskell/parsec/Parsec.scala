package jaskell.parsec
import scala.util.{Try, Success, Failure}
import scala.language.implicitConversions
import jaskell.Monad

trait Parsec[A, B] {
    def apply(state: State[A]): Try[B]

    def ?(state: State[A]): Try[B] = this.apply(state)

    def !(state: State[A]): B = this.apply(state).get

    def attempt: Parsec[A, B] = new Attempt(this)

    def iterate(state: State[A]): Iterator[A, B] = new Iterator(this, state)

    def <|> (p: Parsec[A, B]): Parsec[A, B] = new Combine2(this, p)
}

given parsecConfig[E]: Monad[[T] =>> Parsec[E, T]] with {
    def pure[A](x: A): Parsec[E, A] = new Pack[E, A](x)

    extension [A, B](x: Parsec[E, A]) {
        def flatMap(f: A => Parsec[E, B]): Parsec[E, B] = new Binder(x, f)
    }

}

given textParserConfig[T]: Monad[[T] =>> Parsec[Char, T]] with {
  def pure[A](x: A): Parsec[Char, A] = new Pack[Char, A](x)

  extension [A, B](x: Parsec[Char, A]) {
    def flatMap(f: A => Parsec[Char, B]): Parsec[Char, B] = new Binder(x, f)
  }

  extension [A](x: Parsec[Char, A]) {
      def apply(content: String): Try[A] = x.apply(content.state)
      def ?(content: String): Try[A] = x ? content.state
  }
}
