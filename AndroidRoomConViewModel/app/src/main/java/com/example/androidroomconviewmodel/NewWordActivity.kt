package com.example.androidroomconviewmodel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.androidroomconviewmodel.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewWordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityNewWordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(binding.editWord.text)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                val word = binding.editWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "com.example.adnroid.wordlistsql.REPLY"
    }

}