package c14220280.codelab.roomdb

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import c14220280.codelab.roomdb.database.daftarBelanja
import c14220280.codelab.roomdb.database.daftarBelanjaDB
import c14220280.codelab.roomdb.helper.DateHelper.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TambahDaftar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var DB = daftarBelanjaDB.getDatabase(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var tanggal = getCurrentDate()
        var _etItem = findViewById<EditText>(R.id.etItem)
        var _etJumlah = findViewById<EditText>(R.id.etJumlah)
        CoroutineScope(Dispatchers.IO).async {
            DB.funDaftarBelanjaDAO().insert(
                daftarBelanja(
                    tanggal = tanggal,
                    item = _etItem.text.toString(),
                    jumlah = _etJumlah.text.toString(),
                )
            )
        }
    }
}