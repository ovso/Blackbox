package io.github.ovso.blackbox.utils

object ObjectUtils {

  fun isEmpty(`object`: Any?): Boolean {
    return `object` == null
  }

  fun isEmpty(array: Array<Any>?): Boolean {
    return array == null || array.size == 0
  }

  fun isEmpty(str: CharSequence?): Boolean {
    return str == null || str.length == 0
  }

  fun isEmpty(collection: Collection<*>?): Boolean {
    return collection == null || collection.isEmpty()
  }

  fun isEmpty(map: Map<*, *>?): Boolean {
    return map == null || map.isEmpty()
  }

  fun isEmpty(list: List<*>?): Boolean {
    return list == null || list.isEmpty()
  }
}
