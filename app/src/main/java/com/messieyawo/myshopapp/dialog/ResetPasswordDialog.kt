package com.messieyawo.myshopapp.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.messieyawo.myshopapp.R

fun Fragment.setupBottomSheetDialog(
    onSendClick:(String) -> Unit
){
    val dialog = BottomSheetDialog(requireContext(),R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.forgot_password_dialog,null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edEmail = view.findViewById<EditText>(R.id.edEmail)
    val btnSend = view.findViewById<Button>(R.id.btn_send)
    val btnCansel = view.findViewById<Button>(R.id.btn_cancel)

    btnSend.setOnClickListener {
        val email = edEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }
    btnCansel.setOnClickListener {
        dialog.dismiss()
    }
}