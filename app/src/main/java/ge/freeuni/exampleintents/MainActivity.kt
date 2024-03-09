package ge.freeuni.exampleintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val contactPickerResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { contactPickerResult ->
            val contactUri = contactPickerResult.data?.data
            if (contactUri != null) {
                val projection = arrayOf(
                    CommonDataKinds.Identity.DISPLAY_NAME,
                )
                val cursor = contentResolver.query(contactUri, projection, null, null, null)
                if (cursor != null && cursor.moveToFirst()) {
                    val nameColumnIndex =
                        cursor.getColumnIndex(CommonDataKinds.Identity.DISPLAY_NAME)

                    val name = cursor.getString(nameColumnIndex)
                    val phone = "number"//cursor.getString(phoneColumnIndex)

                    onContactSelected(name, phone)
                }

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.pick_contact).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE)
            contactPickerResult.launch(intent)
        }
    }

    private fun onContactSelected(name: String, phone: String) {
        findViewById<TextView>(R.id.picked_contact).text = "$name : $phone"
    }
}