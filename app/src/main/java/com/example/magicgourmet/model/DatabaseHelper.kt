package com.example.magicgourmet.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "magicgourmet.db"
        private const val DATABASE_VERSION = 1

        // Tabla Usuario
        private const val TABLE_USUARIO = "Usuario"
        private const val COLUMN_USER = "User"
        private const val COLUMN_PASS = "Pass"
        private const val COLUMN_CORREO = "Correo"
        private const val COLUMN_TIPO_USER = "tipo_user"

        // Tabla Cliente
        private const val TABLE_CLIENTE = "Cliente"
        private const val COLUMN_CANT_FAVORITO = "Cant_favorito"

        // Tabla Administrador
        private const val TABLE_ADMINISTRADOR = "Administrador"

        // Tabla Receta
        private const val TABLE_RECETA = "Receta"
        private const val COLUMN_CODRECETA = "Codreceta"
        private const val COLUMN_NOMBRE = "Nombre"
        private const val COLUMN_DESCRIPCION = "Descripcion"
        private const val COLUMN_INGREDIENTES = "Ingredientes"
        private const val COLUMN_LINK = "Link"
        private const val COLUMN_IMAGEN = "Imagen"

        // Tabla Ingrediente
        private const val TABLE_INGREDIENTE = "Ingrediente"
        private const val COLUMN_INGREDIENTE_ID = "id"
        private const val COLUMN_INGREDIENTE_NOMBRE = "Nombre"

        // Tabla Paso
        private const val TABLE_PASO = "Paso"
        private const val COLUMN_PASO_DESCRIPCION = "Descripcion"

        // Tabla Comentario
        private const val TABLE_COMENTARIO = "Comentario"
        private const val COLUMN_COMENTARIO_DESCRIPCION = "Descripcion"

        // Tabla Filtro
        private const val TABLE_FILTRO = "Filtro"
        private const val COLUMN_FILTRO_DESCRIPCION = "Descripcion"

        // SQL para crear las tablas
        private const val SQL_CREATE_USUARIO_TABLE =
            "CREATE TABLE $TABLE_USUARIO (" +
                    "$COLUMN_USER TEXT PRIMARY KEY," +
                    "$COLUMN_PASS TEXT," +
                    "$COLUMN_CORREO TEXT," +
                    "$COLUMN_TIPO_USER TEXT)"

        private const val SQL_CREATE_CLIENTE_TABLE =
            "CREATE TABLE $TABLE_CLIENTE (" +
                    "$COLUMN_USER TEXT PRIMARY KEY," +
                    "$COLUMN_CANT_FAVORITO INTEGER," +
                    "FOREIGN KEY($COLUMN_USER) REFERENCES $TABLE_USUARIO($COLUMN_USER))"

        private const val SQL_CREATE_ADMINISTRADOR_TABLE =
            "CREATE TABLE $TABLE_ADMINISTRADOR (" +
                    "$COLUMN_USER TEXT PRIMARY KEY," +
                    "FOREIGN KEY($COLUMN_USER) REFERENCES $TABLE_USUARIO($COLUMN_USER))"

        private const val SQL_CREATE_RECETA_TABLE =
            "CREATE TABLE $TABLE_RECETA (" +
                    "$COLUMN_CODRECETA INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NOMBRE TEXT," +
                    "$COLUMN_DESCRIPCION TEXT," +
                    "$COLUMN_INGREDIENTES TEXT," +
                    "$COLUMN_LINK TEXT," +
                    "$COLUMN_IMAGEN TEXT)"

        private const val SQL_CREATE_INGREDIENTE_TABLE =
            "CREATE TABLE $TABLE_INGREDIENTE (" +
                    "$COLUMN_INGREDIENTE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_INGREDIENTE_NOMBRE TEXT)"

        private const val SQL_CREATE_PASO_TABLE =
            "CREATE TABLE $TABLE_PASO (" +
                    "$COLUMN_CODRECETA INTEGER," +
                    "$COLUMN_PASO_DESCRIPCION TEXT," +
                    "FOREIGN KEY($COLUMN_CODRECETA) REFERENCES $TABLE_RECETA($COLUMN_CODRECETA))"

        private const val SQL_CREATE_COMENTARIO_TABLE =
            "CREATE TABLE $TABLE_COMENTARIO (" +
                    "$COLUMN_CODRECETA INTEGER," +
                    "$COLUMN_COMENTARIO_DESCRIPCION TEXT," +
                    "FOREIGN KEY($COLUMN_CODRECETA) REFERENCES $TABLE_RECETA($COLUMN_CODRECETA))"

        private const val SQL_CREATE_FILTRO_TABLE =
            "CREATE TABLE $TABLE_FILTRO (" +
                    "$COLUMN_INGREDIENTE_ID INTEGER," +
                    "$COLUMN_FILTRO_DESCRIPCION TEXT," +
                    "FOREIGN KEY($COLUMN_INGREDIENTE_ID) REFERENCES $TABLE_INGREDIENTE($COLUMN_INGREDIENTE_ID))"

        // SQL para eliminar las tablas
        private const val SQL_DELETE_USUARIO_TABLE = "DROP TABLE IF EXISTS $TABLE_USUARIO"
        private const val SQL_DELETE_CLIENTE_TABLE = "DROP TABLE IF EXISTS $TABLE_CLIENTE"
        private const val SQL_DELETE_ADMINISTRADOR_TABLE = "DROP TABLE IF EXISTS $TABLE_ADMINISTRADOR"
        private const val SQL_DELETE_RECETA_TABLE = "DROP TABLE IF EXISTS $TABLE_RECETA"
        private const val SQL_DELETE_INGREDIENTE_TABLE = "DROP TABLE IF EXISTS $TABLE_INGREDIENTE"
        private const val SQL_DELETE_PASO_TABLE = "DROP TABLE IF EXISTS $TABLE_PASO"
        private const val SQL_DELETE_COMENTARIO_TABLE = "DROP TABLE IF EXISTS $TABLE_COMENTARIO"
        private const val SQL_DELETE_FILTRO_TABLE = "DROP TABLE IF EXISTS $TABLE_FILTRO"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_USUARIO_TABLE)
        db.execSQL(SQL_CREATE_CLIENTE_TABLE)
        db.execSQL(SQL_CREATE_ADMINISTRADOR_TABLE)
        db.execSQL(SQL_CREATE_RECETA_TABLE)
        db.execSQL(SQL_CREATE_INGREDIENTE_TABLE)
        db.execSQL(SQL_CREATE_PASO_TABLE)
        db.execSQL(SQL_CREATE_COMENTARIO_TABLE)
        db.execSQL(SQL_CREATE_FILTRO_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_USUARIO_TABLE)
        db.execSQL(SQL_DELETE_CLIENTE_TABLE)
        db.execSQL(SQL_DELETE_ADMINISTRADOR_TABLE)
        db.execSQL(SQL_DELETE_RECETA_TABLE)
        db.execSQL(SQL_DELETE_INGREDIENTE_TABLE)
        db.execSQL(SQL_DELETE_PASO_TABLE)
        db.execSQL(SQL_DELETE_COMENTARIO_TABLE)
        db.execSQL(SQL_DELETE_FILTRO_TABLE)
        onCreate(db)
    }
    fun crearReceta( receta: Receta, pasos: Paso): Long{
        val db = this.writableDatabase
        db.beginTransaction()
        var recetaId: Long = -1
        try {
            // Crear un ContentValues para la tabla Receta
            val recetaValues = ContentValues().apply {
                put("Nombre", receta.nombre)
                put("Descripcion", receta.descripcion)
                put("Ingredientes", receta.ingredientes)
                put("Link", receta.link)
                put("Imagen", receta.imagen)
            }

            // Insertar en la tabla Receta
            recetaId = db.insert("Receta", null, recetaValues)


            // Insertar pasos relacionados
            val pasoValues = ContentValues().apply {
                put("Codreceta", recetaId)
                put("Descripcion", pasos.descripcion)
            }
            db.insert("Paso", null, pasoValues)

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        return recetaId
    }
    fun modificarReceta(receta: Receta, pasos:Paso): Boolean {
        val db = this.writableDatabase
        var isSuccessful = false

        db.beginTransaction()
        try {
            // Actualizar la receta
            val recetaValues = ContentValues().apply {
                put(COLUMN_NOMBRE, receta.nombre)
                put(COLUMN_DESCRIPCION, receta.descripcion)
                put(COLUMN_INGREDIENTES, receta.ingredientes)
                put(COLUMN_LINK, receta.link)
                put(COLUMN_IMAGEN, receta.imagen)
            }
            val rowsUpdated = db.update(
                TABLE_RECETA,
                recetaValues,
                "$COLUMN_NOMBRE = ?",
                arrayOf(receta.nombre)
            )

            if (rowsUpdated > 0) {
                //
                // Logica para modifcar los pasos aqui
                //
                db.setTransactionSuccessful()
                isSuccessful = true
            }
        } finally {
            db.endTransaction()
        }

        return isSuccessful
    }
    fun eliminarReceta(nombre: String): Int {
        val db = this.writableDatabase
        var filasAfectadas = 0

        db.beginTransaction()
        try {
            val cursor = db.query(
                TABLE_RECETA,
                arrayOf(COLUMN_CODRECETA),
                "$COLUMN_NOMBRE = ?",
                arrayOf(nombre),
                null,
                null,
                null
            )
            var id: Long? = null
            cursor.use {
                if (it.moveToFirst()) {
                    id = it.getLong(it.getColumnIndexOrThrow(COLUMN_CODRECETA))
                }
            }
            if (id != null) {
                filasAfectadas += db.delete(TABLE_PASO, "$COLUMN_CODRECETA = ?", arrayOf(id.toString()))
                filasAfectadas += db.delete(TABLE_COMENTARIO, "$COLUMN_CODRECETA = ?", arrayOf(id.toString()))
                filasAfectadas += db.delete(TABLE_RECETA, "$COLUMN_CODRECETA = ?", arrayOf(id.toString()))

                db.setTransactionSuccessful()
            }
        } finally {
            db.endTransaction()
        }
        return filasAfectadas
    }
    fun buscarReceta(nombre: String): Receta? {
        val db = this.readableDatabase
        var recetaBuscada: Receta? = null

        val cursor = db.query(
            "Receta", // Nombre de la tabla
            null, // Columnas (null selecciona todas las columnas)
            "Nombre = ?", // Clausula WHERE
            arrayOf(nombre), // Argumentos de la clausula WHERE
            null, // Group by
            null, // Having
            null // Order by
        )

        cursor.use { // Esto cierra automáticamente el cursor después de usarlo
            if (it.moveToFirst()) {
                // Obtener los valores de la receta desde el cursor
                val descripcion = it.getString(it.getColumnIndexOrThrow("Descripcion"))
                val ingredientes = it.getString(it.getColumnIndexOrThrow("Ingredientes"))
                val link = it.getString(it.getColumnIndexOrThrow("Link"))
                val imagen = it.getString(it.getColumnIndexOrThrow("Imagen"))

                recetaBuscada = Receta(nombre, descripcion, ingredientes, link, imagen)
            }
        }
        if (cursor != null && cursor.count > 0) {
            Log.d("BuscarReceta", "Recetas encontradas: ${cursor.count}")
        } else {
            Log.d("BuscarReceta", "No se encontró ninguna receta con ese nombre")
        }
        return recetaBuscada
    }
    fun crearUsuario(usuario: Usuario): Long {
        val db = this.writableDatabase
        db.beginTransaction()
        var usuarioId: Long = -1
        try {
            val usuarioValues = ContentValues().apply {
                put(COLUMN_USER, usuario.user)
                put(COLUMN_PASS, usuario.pass)
                put(COLUMN_CORREO, usuario.correo)
                put(COLUMN_TIPO_USER, usuario.tipo_user)
            }
            usuarioId = db.insert(TABLE_USUARIO, null, usuarioValues)

            if (usuario.tipo_user == "Administrador") {
                val adminValues = ContentValues().apply {
                    put(COLUMN_USER, usuario.user)
                }
                db.insert(TABLE_ADMINISTRADOR, null, adminValues)
            } else {
                val clienteValues = ContentValues().apply {
                    put(COLUMN_USER, usuario.user)
                    put(COLUMN_CANT_FAVORITO, 0) // Usa un entero en lugar de cadena
                }
                db.insert(TABLE_CLIENTE, null, clienteValues)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        return usuarioId
    }

    fun getAllIngredients(): List<String> {
        val ingredients = mutableListOf<String>()
        val db = this.readableDatabase
        val query = "SELECT $COLUMN_INGREDIENTE_NOMBRE FROM $TABLE_INGREDIENTE"
        Log.d("DBHelper", "Executing query: $query")
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENTE_NOMBRE))
                Log.d("DBHelper", "Ingredient found: $nombre")
                ingredients.add(nombre)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return ingredients
    }

    fun anadirIng(nombre: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_INGREDIENTE_NOMBRE, nombre)
        }
        return db.insert(TABLE_INGREDIENTE, null, values)
    }

}