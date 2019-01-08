package io.github.ovso.blackbox.data.network.model

import io.github.ovso.blackbox.data.network.SearchRequest
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TestOn {
  private val compositeDisposable = CompositeDisposable()

  fun on() {
    val request = SearchRequest()
    request.getResult("", "")
        .observeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : SingleObserver<Search> {
          override fun onSubscribe(d: Disposable) {
            compositeDisposable.add(d)
          }

          override fun onSuccess(search: Search) {

          }

          override fun onError(e: Throwable) {

          }
        })
  }
}
