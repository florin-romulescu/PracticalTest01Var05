package ro.pub.cs.systems.eim.practicaltest01var05

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var05MainActivity : AppCompatActivity() {
    companion object {
        var totalPress = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val topRightBtn = findViewById<Button>(R.id.top_right_btn)
        val topLeftBtn = findViewById<Button>(R.id.top_left_btn)
        val centerBtn = findViewById<Button>(R.id.center_btn)
        val bottomRightBtn = findViewById<Button>(R.id.bottom_right_btn)
        val bottomLeftBtn = findViewById<Button>(R.id.bottom_left_btn)
        val textView = findViewById<TextView>(R.id.descTextView)
        val navigateToSecondaryActivity = findViewById<Button>(R.id.navigate_to_2nd_activity)

        topLeftBtn.setOnClickListener(ButtonListener("Top Left", textView))
        topRightBtn.setOnClickListener(ButtonListener("Top Right", textView))
        centerBtn.setOnClickListener(ButtonListener("Center", textView))
        bottomLeftBtn.setOnClickListener(ButtonListener("Bottom Left", textView))
        bottomRightBtn.setOnClickListener(ButtonListener("Bottom Right", textView))
        navigateToSecondaryActivity.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java).apply {
                putExtra("text", textView.text)
            }
            startActivityForResult(intent, 1)
        }

        Toast.makeText(this, totalPress.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("Total Press", totalPress)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val savedInt = savedInstanceState.getInt("Total Press", 0)
        totalPress = savedInt
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            when (resultCode) {
                PracticalTest01Var05SecondaryActivity.RESULT_VERIFY -> {
                    Toast.makeText(this, "Verify", Toast.LENGTH_SHORT).show()
                }
                PracticalTest01Var05SecondaryActivity.RESULT_CANCEL -> {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    class ButtonListener(private val name: String, private val text: TextView) : View.OnClickListener {
        @SuppressLint("SetTextI18n")
        override fun onClick(v: View?) {
            this.text.setText(this.text.text.toString() + ", " + name)
            totalPress += 1
        }

    }
}