package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.mobiledevelopmentcourselabapp.App
import com.example.mobiledevelopmentcourselabapp.core.presentation.BaseFragment
import com.example.mobiledevelopmentcourselabapp.databinding.FragmentNotificationAddBinding
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter.NotificationAddPresenter
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class NotificationAddFragment: BaseFragment(), NotificationAddView {
    private var binding: FragmentNotificationAddBinding? = null

    @Inject
    lateinit var presenterProvider: Provider<NotificationAddPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FragmentNotificationAddBinding.inflate(inflater, container, false).apply {
            binding = this
            return this.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            date.setOnClickListener { presenter.onDateClick() }
            time.setOnClickListener { presenter.onTimeClick() }
            done.setOnClickListener { presenter.onDoneClick() }
            noteText.addTextChangedListener { presenter.onTextChanged(it) }
        }
    }

    override fun showDatePicker() {
        val dialog = DatePickerDialog(requireContext())
        dialog.setOnDateSetListener { _, year, month, day ->
            presenter.onDateSelected(year, month, day)
        }
        dialog.show()
    }

    override fun showTimePicker() {
        TimePickerDialog(
            requireContext(),
            { _, hour, minute -> presenter.onTimeSelected(hour, minute) },
            0, 0, true
        ).show()
    }

    override fun setDate(date: String) {
        binding?.date?.text = date
    }

    override fun showTime(time: String) {
        binding?.time?.text = time
    }
}