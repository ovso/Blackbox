package io.github.ovso.blackbox.data.network

enum class ApiEndPoint private constructor(val url: String) {
  SEARCH("https://www.googleapis.com/")
}