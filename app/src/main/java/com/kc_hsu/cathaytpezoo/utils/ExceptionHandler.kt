package com.kc_hsu.cathaytpezoo.utils

import android.app.Application
import com.kc_hsu.cathaytpezoo.R
import com.kc_hsu.cathaytpezoo.TpeZooApplication
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.functions.Consumer
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.subjects.PublishSubject
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExceptionHandler(private val application: Application) : Consumer<Throwable> {

    val errorMessageToDisplay = PublishSubject.create<String>()
    val shouldShowUnsafeConnConfirmDialog = PublishSubject.create<Boolean>()

    init {
        RxJavaPlugins.setErrorHandler(this)
    }

    override fun accept(t: Throwable) {
        when (val cause = parseCause(t)) {
            is SocketTimeoutException, is ConnectException, is UnknownHostException, is SocketException -> {
                Timber.w("Network failed: $cause")
                errorMessageToDisplay.onNext(application.getString(R.string.error_network_fail))
            }
            is HttpException -> {
                val url = cause.response()?.raw()?.request?.url
                errorMessageToDisplay.onNext("HTTP error ${cause.code()} of $url")
            }
            else -> {
                // It's normally a fatal exception for the connection.
                // For the UX consideration, just notifying users.
                Timber.e(cause)
                cause.message.apply {
                    // avoid using !! condition.
                    if (this?.contains("Certificate expired") == true) {
                        Timber.d("Certificate expired found, showing a dialog to let user to confirm")
                        shouldShowUnsafeConnConfirmDialog.onNext(true)
                    } else {
                        val errString = String.format(
                            TpeZooApplication.applicationContext()
                                .getString(R.string.network_exception), this
                        )
                        errorMessageToDisplay.onNext(errString)
                    }
                }
            }
        }
    }

    private fun parseCause(t: Throwable): Throwable {
        when (t) {
            is OnErrorNotImplementedException, is UndeliverableException, is RuntimeException -> {
                t.cause?.run { return this } ?: throw ParseCauseFailException(t)
            }
        }
        return t
    }
}

class ParseCauseFailException(t: Throwable) : RuntimeException(t)