package com.leewc.kotlinexperiment

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {

    // store items
    private var todoListItems = ArrayList<String>()
    // reference to the instantiated list view
    private var listView: ListView? = null
    // make an Android adapter to display data: https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
    private var listAdapter: ArrayAdapter<String>? = null

    override fun onDialogNegativeClick(dialog: DialogFragment) {

    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        todoListItems.add(task)
        listAdapter?.notifyDataSetChanged()
        Snackbar.make(fab, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            showNewTaskUI()
        }

        listView = findViewById(R.id.list_view)
        populateListView()
    }

    private fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_new_task_dialog_title)
        //Different from the tutorial due to conflicting types.
        //        androidx.fragment.app.FragmentManager
        //        android.app.FragmentManager
        newFragment.show(supportFragmentManager, "newTask")

    }
    private fun populateListView() {
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoListItems)
        listView?.adapter = listAdapter
    }
}
