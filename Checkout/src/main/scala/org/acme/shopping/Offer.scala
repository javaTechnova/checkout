package org.acme.shopping

trait Offer[ItemPriced] {
  def discountedItems(items: Seq[ItemPriced]): Seq[ItemPriced]
}

class DiscountedOffer(desc: String = "", buyQty: Int, getQty: Int) extends Offer[ItemPriced] {
  def discountedItems(items: Seq[ItemPriced]): Seq[ItemPriced] = {
    if (items.isEmpty) items
    else {
      val numOfItems = items.length
      val unitPrice = items.head.price
      val remainder = numOfItems % buyQty
      val l = remainder match {
        case 0 => numOfItems / buyQty * getQty
        case _ => (numOfItems - remainder) / buyQty * getQty + remainder
      }
      items.take(l)
    }
  }
}