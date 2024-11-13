package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {
    companion object {
        const val RESULT_KEY = "result"
        const val RESULT_VERIFY = Activity.RESULT_FIRST_USER + 1
        const val RESULT_CANCEL = Activity.RESULT_FIRST_USER + 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textValue = intent.getStringExtra("text")

        val textView = findViewById<TextView>(R.id.descTextView2)
        textView.text = textValue

        val verifyBtn = findViewById<Button>(R.id.verify)
        val cancelBtn = findViewById<Button>(R.id.cancel)

        verifyBtn.setOnClickListener {
            setResult(RESULT_VERIFY, Intent().apply {
                putExtra(RESULT_KEY, "Verify")
            })
            finish()
        }

        cancelBtn.setOnClickListener {
            setResult(RESULT_CANCEL, Intent().apply {
                putExtra(RESULT_KEY, "Cancel")
            })
            finish()
        }
    }

}