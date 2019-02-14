package at.kkcoding.android.epoxypaginationsample.application.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import at.kkcoding.android.epoxypaginationsample.application.PaginationApp
import at.kkcoding.android.epoxypaginationsample.application.di.helpers.Injectable
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector

object AppInjector {

  fun init(app: PaginationApp) {
    app
      .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
          handleActivity(activity)
        }

        // region Unused lifecycles

        override fun onActivityStarted(a: Activity) = Unit
        override fun onActivityResumed(a: Activity) = Unit
        override fun onActivityPaused(a: Activity) = Unit
        override fun onActivityStopped(a: Activity) = Unit
        override fun onActivitySaveInstanceState(a: Activity, outState: Bundle?) = Unit
        override fun onActivityDestroyed(a: Activity) = Unit
        // endregion
      })
  }

  private fun handleActivity(activity: Activity) {
    if (activity is HasSupportFragmentInjector) {
      AndroidInjection.inject(activity)
    }
    if (activity is FragmentActivity) {
      activity.supportFragmentManager
        .registerFragmentLifecycleCallbacks(
          object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(
              fm: FragmentManager,
              f: Fragment,
              savedInstanceState: Bundle?
            ) {
              if (f is Injectable) {
//                AndroidSupportInjection.inject(f)
              }
            }
          }, true
        )
    }
  }
}