package leonardomagno.com.searchproductsapplication.ui.dialogs


import android.content.Context
import androidx.appcompat.app.AlertDialog
import leonardomagno.com.searchproductsapplication.R

class ErrorDialog(
    private val context: Context
) {

    fun callErrorDialog() {
        setupErrorDialog()
    }

    private fun setupErrorDialog() {

        val title = R.string.error_dialog_title

        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(R.string.error_dialog_message)
            .setNegativeButton(R.string.error_dialog_positive_buttom, null)
            .show()
    }
}

