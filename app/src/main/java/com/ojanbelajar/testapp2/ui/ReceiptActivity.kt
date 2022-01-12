package com.ojanbelajar.testapp2.ui

import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ojanbelajar.testapp2.databinding.ActivityReceiptBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.*
import java.lang.Exception
import java.net.URLEncoder


@AndroidEntryPoint
class ReceiptActivity : AppCompatActivity() {

    lateinit var binding : ActivityReceiptBinding
    lateinit var model : ReceiptViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(ReceiptViewModel::class.java)

        val charge = intent.getIntExtra("charge",0)
        val total =  intent.getIntExtra("total",0)
        binding.charge!!.text = "Rp. $charge change"
        binding.text1!!.text = "Out of Rp. $total"

        binding.sendEmail!!.setOnClickListener {
            if (binding.email!!.text.toString().isNotEmpty()){
                val i = Intent(Intent.ACTION_SEND)
                i.type = "message/rfc822"
                i.putExtra(Intent.EXTRA_EMAIL, arrayOf(binding.email!!.text.toString()))
                i.putExtra(Intent.EXTRA_SUBJECT, "Receipt Email")
                i.putExtra(Intent.EXTRA_TEXT, "Testing Email")
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."))
                } catch (ex: ActivityNotFoundException) {
                    toast("There are no email clients installed.")
                }
            }
        }
        binding.sendWa!!.setOnClickListener {
            if (binding.wa!!.text.toString().isNotEmpty()){
                val packageManager: PackageManager = packageManager
                val i = Intent(Intent.ACTION_VIEW)

                try {
                    val url =
                        "https://api.whatsapp.com/send?phone=" + binding.wa!!.text.toString() + "&text=" + URLEncoder.encode(
                            "Receipt",
                            "UTF-8"
                        )
                    i.setPackage("com.whatsapp")
                    i.data = Uri.parse(url)
                    if (i.resolveActivity(packageManager) != null) {
                        startActivity(i)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        binding.newSale!!.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("New Sale")
                .setMessage("Apakah anda mau belanja kembali? cart akan kosong")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialogInterface, i ->
                    model.deleteCart()
                    val intent = Intent(this@ReceiptActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                })
                .show()


        }
    }
}