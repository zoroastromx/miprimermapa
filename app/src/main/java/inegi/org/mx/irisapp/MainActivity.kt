package inegi.org.mx.irisapp

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.preference.PreferenceManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.osmdroid.bonuspack.kml.KmlDocument
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView


class MainActivity : AppCompatActivity() {
 //class MainActivity extends Activity implements MapEventsReceiver, MapView.OnFirstLayoutListener {

    private var map: MapView? = null
    var mKmlDocument: KmlDocument? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        //Disable StrictMode.ThreadPolicy to perform network calls in the UI thread.
        //Yes, it's not the good practice, but this is just a tutorial!
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(
            this, PreferenceManager.getDefaultSharedPreferences(
                this
            )
        )
        enableEdgeToEdge()


        setContentView(R.layout.activity_main)

        map = findViewById(R.id.map)
        map!!.setMultiTouchControls(true)
        val startPoint = GeoPoint(21.8567, -102.2852)
        val mapController = map!!.controller
        mapController.setZoom(10.0)
        mapController.setCenter(startPoint)


        //map!!.setOnFirstLayoutListener(this)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}