package io.github.ovso.blackbox.data.network

import io.github.ovso.blackbox.data.network.model.SearchItem

fun SearchRequest.getAdsAddedItem(items: List<SearchItem>): List<SearchItem> {
  val newItems = mutableListOf<SearchItem>()
  val originCount = items.count()
  val adsStep = 5
  for (i in 0 until originCount step adsStep) {
    val toIndex = if (i + adsStep > originCount) originCount else i + adsStep
    val subList = items.subList(fromIndex = i, toIndex = toIndex)
    with(newItems) {
      add(SearchItem())
      addAll(subList)
    }
  }
  return newItems
}
