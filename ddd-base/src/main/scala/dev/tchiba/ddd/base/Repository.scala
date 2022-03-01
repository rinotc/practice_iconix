package dev.tchiba.ddd.base

/**
 * DDDのリポジトリであることを示すマーカートレイト
 *
 * @tparam A [[Aggregate]] を継承している [[Entity]]
 */
trait Repository[A <: Aggregate]
