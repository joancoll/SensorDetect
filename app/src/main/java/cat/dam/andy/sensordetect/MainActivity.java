package cat.dam.andy.sensordetect;

import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * SensorDetect utilitza SensorManager per a obtenir la llista
 * de sensors disponibles, i mostra la llista com a TextView.
 */
public class MainActivity extends AppCompatActivity {

    // Crea una instància SensorManager per accedir als sensors del sistema.
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvSensors = (TextView) findViewById(R.id.tv_Sensors);
        int nSensors=0; //quantitat de sensors

        //getSystemService retorna un handler (gestor) d'un servei a nivell de sistema pel seu nom
        //amb getSystemService podem accedir no només als sensors, també al gestor de bateria, vibració, alarma, ....
        //En aquest cas el handler serà del tipus SensorManager i connectarà amb el servei de sensors
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //cridem el mètode .getSensorList que obté la llista de tots els sensors disponibles (tipus Sensor)
        //podriem triar de quin tipus volem comprovar p.e. TYPE_AMBIENT_TEMPERATURE
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //Itera la llista de sensors, obté nom del sensor i l'afegeix a un string amb un salt de línia.
        //Utilitzem StringBuilder enlloc de concat o + per rendiment ja que el compilador crearà menys objectes
        StringBuilder textSensors = new StringBuilder();
        for (Sensor currentSensor : sensorList ) {
            nSensors++;
            textSensors.append(nSensors).append("-").append(currentSensor.getName()).append(
                    System.getProperty("line.separator")); //No depèn SO (\n Linux o \r\n Windows)
        }

        // Actualitza el textview amb la llista de sensors disponibles del dispositiu.
        tvSensors.setText(textSensors);
    }
}
