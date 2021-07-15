package BBDD;

import android.provider.BaseColumns;

public class EstructuraBBDD {


    private EstructuraBBDD(){}

            public static final String TABLE_NAME = "Cuentas";
            public static final String COLUMNA1 = "ID";
            public static final String COLUMNA2 = "Cuenta";
            public static final String COLUMNA3 = "Password";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBBDD.TABLE_NAME + " (" +
                    EstructuraBBDD.COLUMNA1 + " INTEGER PRIMARY KEY," +
                    EstructuraBBDD.COLUMNA2 + " TEXT," +
                    EstructuraBBDD.COLUMNA3 + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBBDD.TABLE_NAME;

}
