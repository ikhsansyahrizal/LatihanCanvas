package com.spe

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.spe.customview.MyButton
import com.spe.customview.MyEditTexg
import com.spe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var myButton: MyButton
    private lateinit var myEditText: MyEditTexg

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        binding.toLatihanCanvas.setOnClickListener {
            val intent = Intent(this, LatihanCanvasActivity::class.java)
            startActivity(intent)
        }

        myButton = findViewById(R.id.my_button)
        myEditText = findViewById(R.id.my_edit_text)
        setMyButtonEnable()

        myEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
        myButton.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                myEditText.text,
                Toast.LENGTH_SHORT
            ).show()
        }

        // canvas
        val mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        binding.ivCanvas.setImageBitmap(mBitmap)
        val mCanvas = Canvas(mBitmap)
        val mPaint = Paint()
        val mPaint2 = Paint()
        val mRect = Rect()
        val mPainText = Paint(Paint.FAKE_BOLD_TEXT_FLAG)
        mPainText.textSize = 12f
        mPainText.color = ResourcesCompat.getColor(resources, R.color.white, null)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.teal_200, null)
        mPaint2.color = ResourcesCompat.getColor(resources, R.color.purple_500, null)

        mCanvas.drawColor(ResourcesCompat.getColor(resources, R.color.pink_200, null))

        mCanvas.drawCircle(
            (mBitmap.width / 2).toFloat(),
            (mBitmap.height / 2).toFloat(),
            200f,
            mPaint
        )

        mRect.set(
            mBitmap.width / 2 - 100,
            mBitmap.height / 2 - 100,
            mBitmap.width / 2 + 100,
            mBitmap.height / 2 + 100
        )
        mCanvas.drawRect(mRect, mPaint2)

        val text = "Selamat Datang"
        val mBounds = Rect()
        mPainText.getTextBounds(text, 0, text.length, mBounds)

        val x: Int = mBitmap.width/2 - mBounds.centerX()
        val y: Int = mBitmap.height/2 - mBounds.centerY()
        mCanvas.drawText(text, x.toFloat(), y.toFloat(), mPainText)

    }


    private fun setMyButtonEnable() {
        val result = myEditText.text
        myButton.isEnabled = result != null && result.toString().isNotEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}