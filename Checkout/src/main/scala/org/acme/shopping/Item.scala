package org.acme.shopping

object Item extends Enumeration{
  type Item = Value
  val Apple,Orange = Value
}

import org.acme.shopping.Item._
case class ItemPriced(name:Item,price:Double)