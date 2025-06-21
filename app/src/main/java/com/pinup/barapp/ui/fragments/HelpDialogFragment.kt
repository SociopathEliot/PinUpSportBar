package com.pinup.barapp.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.pinup.barapp.databinding.DialogHelpBinding
import com.pinup.barapp.ui.MainActivity
import com.pinup.barapp.R

class HelpDialogFragment : DialogFragment() {
    private var _binding: DialogHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogHelpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.85).toInt(),
            WindowManager.LayoutParams.WRAP_CONTENT,
        )
        dialog?.window?.setBackgroundDrawableResource(R.drawable.bg_help_dialog)
        dialog?.window?.setGravity(Gravity.CENTER)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        (activity as? MainActivity)?.hideBlur()
    }
}

