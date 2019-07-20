import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.leewc.kotlinexperiment.R

class NewTaskDialogFragment: DialogFragment() {

    interface NewTaskDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, task: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    var newTaskDialogListener: NewTaskDialogListener? = null

    companion object {
        fun newInstance(title: Int): NewTaskDialogFragment {
            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments!!.getInt("dialog_title")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        val dialogView = activity?.layoutInflater?.inflate(R.layout.dialog_new_task, null)
        val task = dialogView!!.findViewById<TextView>(R.id.task)

        builder.setView(dialogView)
            .setPositiveButton(R.string.task_save) { dialog, id ->
                newTaskDialogListener?.onDialogPositiveClick(this, task.text.toString())
            }
            .setNegativeButton(android.R.string.cancel) { dialog, id ->
                newTaskDialogListener?.onDialogNegativeClick(this)
            }

        return builder.create()
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            newTaskDialogListener = activity as NewTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement NewTaskDialogListener")
        }
    }
}