package appsqlite.app.andrevera.com.appsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etId,etNombres,etTelefono;
    Button btnGuardar,btnBuscar,btnEditar,btnEliminar;
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
        switch (v.getId()){
            case R.id.btnGuardar:

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
