package dev.tchiba.ddd.base

/**
 * DDDの集約であることを示すマーカートレイト
 */
trait Aggregate { self: Entity[_] => }
