package ge.freeuni.exampleintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MySecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_second)

        var argName = intent.extras?.getString("KEY_NAME")
        if (argName == null) {
            argName = "No Name"
        }

        findViewById<TextView>(R.id.name).text = argName
    }
}