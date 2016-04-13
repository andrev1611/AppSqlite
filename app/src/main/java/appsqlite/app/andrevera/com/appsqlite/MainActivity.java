package appsqlite.app.andrevera.com.appsqlite;

import android.content.ContentValues;
import android.database.Cursor;
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
        //el código de cada botón deberia ir en un try catch para los errores que puedan ocurrir al agregar o buscar un id nulo o cosas asi
        switch (v.getId()){
            case R.id.btnGuardar:
                SQLiteDatabase db = ayudaBD.getWritableDatabase();//configuramos la bd para escribir en ella
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
                SQLiteDatabase db2 = ayudaBD.getReadableDatabase();//configuramos la bd para leerla
                String[] argsel={etId.getText().toString()};
                String[] traeColumnas = {AyudaBD.DatosTabla.COLUMNA_NOMBRES, AyudaBD.DatosTabla.COLUMNA_TELEFONOS};//campos que queremos que nos traiga
                //en este caso no nos traera toda la tabla sino un elemento que nosotros le indiquemos por medio del ID
                //sino tendriamos que crear una variable para el orden en que queremos mostrar y eso iria en lugar del null final
                Cursor c = db2.query(AyudaBD.DatosTabla.NOMBRE_TABLA,traeColumnas, AyudaBD.DatosTabla.COLUMNA_ID+"=?",argsel,null,null,null);
                //ahora ubicamos el cursor en la primera posicion porque ese es el único elemento que tendra la búsqueda
                c.moveToFirst();//de acuerdo a los campos que hemos puesto aqui para que traiga
                etNombres.setText(c.getString(0));//la columna 0 que en este caso es nombres
                etTelefono.setText(c.getString(1));//columna 1 telefonos
                break;
            case R.id.btnEditar:
                SQLiteDatabase db3 = ayudaBD.getWritableDatabase();

                ContentValues valores1 = new ContentValues();
                valores1.put(AyudaBD.DatosTabla.COLUMNA_NOMBRES,etNombres.getText().toString());
                valores1.put(AyudaBD.DatosTabla.COLUMNA_TELEFONOS,etTelefono.getText().toString());

                String[] argsel3={etId.getText().toString()};
                String seleccion1 = AyudaBD.DatosTabla.COLUMNA_ID+"=?";
                int count = db3.update(AyudaBD.DatosTabla.NOMBRE_TABLA,valores1,seleccion1,argsel3);//devuelve 1 si editó bien

                Toast.makeText(getApplicationContext(),"Se editó el registro: "+count,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnEliminar:
                SQLiteDatabase db4 = ayudaBD.getWritableDatabase();
                String seleccion = AyudaBD.DatosTabla.COLUMNA_ID+"=?";
                String[] argsel2={etId.getText().toString()};
                db4.delete(AyudaBD.DatosTabla.NOMBRE_TABLA,seleccion,argsel2);
                break;
        }
    }
}
