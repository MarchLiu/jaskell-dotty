package jaskell

trait Functor[F[_]]:
  def pure[A](x: A): F[A]

  extension [A, B](x: F[A])
    /** The unit value for a monad */
    def map(f: A => B): F[B]
    def flatMap(f: A => F[B]): F[B]

given Functor[List] with
  def pure[A](x: A) = List(x)
  extension [A, B](xs: List[A])
    def map(f: A => B): List[B] =
      xs.map(f) // List already has a `map` method
    def flatMap(f: A => List[B]): List[B] = xs.flatMap(f)
