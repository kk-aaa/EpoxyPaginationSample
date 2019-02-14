package at.kkcoding.android.epoxypaginationsample.classes

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

  // region Dagger
  @Inject
  lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
  // endregion

  fun <T : ViewModel> activityViewModel(clazz: Class<T>): T =
    ViewModelProviders.of(this, viewModelFactory).get<T>(clazz)

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  fun <T : ViewModel> viewModel(clazz: Class<T>): T =
    ViewModelProviders.of(this, viewModelFactory).get<T>(clazz)

  protected fun <T> Single<T>.observe(action: (T) -> Unit): Disposable =
    takeUntil(
      RxLifecycleObserver.create(this@BaseActivity)
        .filter { it == Lifecycle.State.DESTROYED }
        .firstOrError()
    )
      .subscribe(action, Timber::e)

  protected fun <T> Observable<T>.observe(action: (T) -> Unit): Disposable =
    takeUntil(
      RxLifecycleObserver.create(this@BaseActivity).filter { it == Lifecycle.State.DESTROYED }
    )
      .subscribe(action, Timber::e)

}

object RxLifecycleObserver {

  fun create(owner: LifecycleOwner): Observable<Lifecycle.State> =
    Observable.create<Lifecycle.State> { emitter ->
      val lifecycle = owner.lifecycle
      if (!emitter.isDisposed) {
        emitter.onNext(lifecycle.currentState)
      }
      val observer = GenericLifecycleObserver { _, _ ->
        if (!emitter.isDisposed) {
          emitter.onNext(lifecycle.currentState)
        }
      }
      lifecycle.addObserver(observer)
      emitter.setDisposable(Disposables.fromAction { lifecycle.removeObserver(observer) })
    }


}