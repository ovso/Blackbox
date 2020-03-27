package io.github.ovso.blackbox.data.network.model

import kotlin.contracts.contract

class SearchItem {
  val kind: String? = null
  val etag: String? = null
  val id: SearchItemId? = null
  val snippet: Snippet? = null
}

public fun Snippet?.isNullOrEmpty() = this == null
