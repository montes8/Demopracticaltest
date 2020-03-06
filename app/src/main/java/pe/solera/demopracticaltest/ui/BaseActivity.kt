package pe.solera.demopracticaltest.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import pe.solera.demopracticaltest.R
import pe.solera.demopracticaltest.component.DemoProgressBar
import java.net.SocketTimeoutException

abstract class BaseActivity : AppCompatActivity(){

    private var mProgressBar: DemoProgressBar? = null
    private var mViewModel: BaseViewModel? = null
    abstract fun getLayout(): Int
    abstract fun setUpView()
    abstract fun getViewModel(): BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        mViewModel = getViewModel()
        mProgressBar = DemoProgressBar(this, android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen)
        observeCommons()
        this.setUpView()
    }

    private fun observeCommons() {
        if (mViewModel != null) {
            mViewModel?.loadingLiveData?.observe(
                this,
                Observer { isLoading -> showLoading(isLoading) })
            mViewModel?.attachInternet?.observe(
                this,
                Observer { isConnect -> errorConnect(isConnect) })
            mViewModel?.errorLiveData?.observe(this, Observer { error -> showError(error) })
            mViewModel?.errorGenericLiveData?.observe(this, Observer { error -> showErrorToast(error) })
        }
    }

     fun showError(ex: Exception) {
        when(ex) {
          /*  is GenericException -> {
                showErrorToast(ex.msg)
            }
            is NullResponseApiException ->{
                showErrorToast( resources.getString(R.string.error_go))
            }*/
            is SocketTimeoutException->{
                errorMessage(resources.getString(R.string.error_timeout))
            }
            else -> {
                errorMessage(ex.message)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        mProgressBar.apply {
            if (isLoading) this?.show() else this?.dismiss()
        }
    }


    private fun errorConnect(connect: Boolean) {
        if (!connect){
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setMessage(R.string.exception_no_internet_error)
                .setPositiveButton(R.string.dialog_accept) { dialogInterface, _ -> dialogInterface.dismiss() }
                .show() }
    }


     fun errorMessage(string: String?) {
        val message = string ?: resources.getString(R.string.error_go)
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_accept) { dialogInterface, _ -> dialogInterface.dismiss() }
                .show()
    }

    fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}