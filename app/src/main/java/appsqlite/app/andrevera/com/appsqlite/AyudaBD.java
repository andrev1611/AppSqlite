package appsqlite.app.andrevera.com.appsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Admin on 12/04/2016.
 */
public class AyudaBD extends SQLiteOpenHelper {

    public static abstract class DatosTabla implements BaseColumns{//ESTA CLASE ES PARA CREAR ... SE PUEDE HACER EN OTRA CLASE OTRO ARCHIVO
        //AQUI VAN LOS DATOS DE LA TABLA
        public static final String NOMBRE_TABLA = "Directorio";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRES = "nombres";
        public static final String COLUMNA_TELEFONOS = "telefono";
        //AQUI VA LA SENTENCIA PARA CREARLA
        private static final String TIPO_TEXTO = " TEXT";
        private static final String COMA_SEPARA = ",";
        private static final String CREAR_TABLA_1 =
                "CREATE TABLE "+DatosTabla.NOMBRE_TABLA+ " (" +
                    DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY,"+
                    DatosTabla.COLUMNA_NOMBRES+TIPO_TEXTO+COMA_SEPARA+
                    DatosTabla.COLUMNA_TELEFONOS+TIPO_TEXTO+
                        " )";
        private static final String SQL_DELETE_ENTRIES=
                "DROP TABLE IF EXISTS "+DatosTabla.NOMBRE_TABLA;
    }
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MiBasedeDatos.db";//asi quedará guardada en el teléfono

    public AyudaBD(Context context) {//AQUI BORRAMOS T ODO Y SOLO DEJAMOS EL CONTEXT
        super(context,DATABASE_NAME,null,DATABASE_VERSION);//aqui colocamos las variables de arriba
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTabla.CREAR_TABLA_1);//aqui ejecutamos la sentencia de la varibale crear tabla 1
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);//borramos la tabla para volver a crearla
        onCreate(db);
    }
}
