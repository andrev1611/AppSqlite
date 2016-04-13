package appsqlite.app.andrevera.com.appsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etId,etNombres,etTelefono;
    Button btnGuardar,btnBuscar,btnEditar,btnEliminar;
    AyudaBD ayudaBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText)findViewById(R.id.etId);
        etNombres = (EditText)findViewById(R.id.etNombres);
        etTelefono = (EditText)findViewById(R.id.etTelefono);

        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnEditar = (Button)findViewById(R.id.btnEditar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        btnGuardar.setOnClickListener(this);
        btnBuscar.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        ayudaBD = new AyudaBD(getApplicationContext());//podria ser solo declarado aqui pero lo hice al inicio como global
        switch (v.getId()){
            case R.id.btnGuardar:
                SQLiteDatabase db = ayudaBD.getWritableDatabase();//indicamos que se pueda escribir en la bd
                ContentValues valores = new ContentValues();//variable que contendra los valores
                //asignamos los valores de los elementos
                valores.put(AyudaBD.DatosTabla.COLUMNA_ID,etId.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_NOMBRES,etNombres.getText().toString());
                valores.put(AyudaBD.DatosTabla.COLUMNA_TELEFONOS,etTelefono.getText().toString());
                //enviamos la tabla, la columna que nos devolvera q en este caso sera COLUMNA_ID y los valores para que agregue
                Long IdGuardado = db.insert(AyudaBD.DatosTabla.NOMBRE_TABLA,AyudaBD.DatosTabla.COLUMNA_ID,valores);
                Toast.makeText(getApplicationContext(),"Se guardo el registro: "+IdGuardado,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnBuscar:

                break;
            case R.id.btnEditar:

                break;
            case R.id.btnEliminar:

                break;
        }
    }
}
