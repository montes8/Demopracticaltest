package pe.solera.demopracticaltest.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pe.solera.demopracticaltest.utils.AppUtils

open class BaseViewModel(val context: Context) : ViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Exception>()
    val attachInternet = MutableLiveData<Boolean>()
    val errorGenericLiveData = MutableLiveData<String>()

    fun validateInternet(): Boolean {
        if (AppUtils.isConnected(context)) {
            return true
        }
        attachInternet.postValue(false)
        return false
    }

    fun execute(loading : Boolean = true,func: () -> Unit) {
        GlobalScope.launch {
            try {
                loadingLiveData.postValue(loading)
                func()
                loadingLiveData.postValue(false)
            } catch (ex: Exception) {
                ex.printStackTrace()
                errorLiveData.postValue(ex)
                loadingLiveData.postValue(false)
            }
        }
    }

}