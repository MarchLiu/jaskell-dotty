# Jaskell Dotty

[![Maven Central](https://img.shields.io/maven-central/v/io.github.marchliu/jaskell-dotty_3.0.0-M3.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.marchliu%22%20AND%20a:%22jaskell-dotty_3.0.0-M3%22)

Jaskell Dotty is a utils library fork from [Jaskell Core](https://github.com/MarchLiu/jaskell-core).

It implemented functor/applicative/monad and parsec combinators in scala 3 (AKA dotty).

Jaskell Dotty project should be next jaskell core for scala 3.

Document in [wiki](https://github.com/MarchLiu/jaskell-dotty/wiki) .

There is a demo show how to [write a tiny lisp parser](https://github.com/MarchLiu/sisp-dotty) .

## Installation

```xml
<dependency>
    <groupId>io.github.marchliu</groupId>
    <artifactId>jaskell-dotty_3.0.0-RC1</artifactId>
    <version>0.2.6</version>
</dependency>
```

## What's new

 - 0.2.6 upgrade to scala 3.0.0 rc1
 - 0.2.5 add <:> operator as synonym for fmap/map
 - 0.2.4 add future monad
 
### 0.3.0

 - add skip1 spaces
 - add skip1 whitespaces

### 0.4.0

 - add built in combinators as typeclasses style
 - bugs fixed

### 0.5.0

 - uniform given instances
 - add chars in and may in

### 0.6.0

 - add croupier types for random select

### 0.6.1

 - local return upgrade to returning

### 0.6.2

 - add binary scale
 - add lite scale
 - add binary rank

### 0.6.3

 - fixed edge condition mistake in damping
 - make invert smoothness. now it just be a negative damping