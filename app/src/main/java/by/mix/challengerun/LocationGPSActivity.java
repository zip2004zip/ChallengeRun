package by.mix.challengerun;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocationGPSActivity extends AppCompatActivity {

    private TextView textViewLocGPS;
    private TextView textViewLocSpeed;
    private TextView textViewLocAltitude;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_gps);

        textViewLocGPS = (TextView) findViewById(R.id.textView_locationGPS);
        textViewLocSpeed = (TextView) findViewById(R.id.textView_LocationSpeed);
        textViewLocAltitude = (TextView) findViewById(R.id.textView_LocationAltitude);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // отслеживает изменение местоположения
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                location.getLatitude();
                location.getLongitude();
                location.getSpeed();
                location.getAltitude();
                String txt = "Текущее местоположение: " + "\nLatitud = " + location.getLatitude() + "\nLongitud = " + location.getLongitude();
                textViewLocGPS.setText(txt);
                textViewLocSpeed.setText("Текущая скорость: " + "\n" + location.getSpeed() + " км/ч");
                textViewLocAltitude.setText("Текущая высота: " + "\n" + location.getAltitude() + " м");
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                Toast.makeText(getApplicationContext(), "Gps включен", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(String s) {
                Toast.makeText(getApplicationContext(), "Gps выключен", Toast.LENGTH_LONG).show();
                // вызываем настройки для включения GPS
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        } else {

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
    }

    @Override
    protected void onResume() {
        // включаем отслеживание при GPS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);

        // при использовании сетей типа GSM
        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

        super.onResume();
    }

    @Override
    protected void onPause() {
        // выключаем отслеживание
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(locationListener);
        super.onPause();
    }
}


// https://youtu.be/QNb_3QKSmMk
// http://www.darkraha.com/rus/mobile/android/api/location.php