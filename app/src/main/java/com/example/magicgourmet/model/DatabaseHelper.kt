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

        // Tabla Favorito
        private const val TABLE_FAVORITOS = "Favorito"

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

        private const val SQL_CREATE_FAVORITOS_TABLE =
            "CREATE TABLE $TABLE_FAVORITOS (" +
                    "$COLUMN_CODRECETA INTEGER," +
                    "$COLUMN_USER TEXT," +
                    "FOREIGN KEY($COLUMN_CODRECETA) REFERENCES $TABLE_RECETA($COLUMN_CODRECETA)," +
                    "FOREIGN KEY($COLUMN_USER) REFERENCES $TABLE_USUARIO($COLUMN_USER))"


        // SQL para insertar un usuario predeterminado
        private val SQL_INSERTAR_USUARIO = """
            INSERT INTO $TABLE_USUARIO ($COLUMN_USER, $COLUMN_PASS, $COLUMN_CORREO, $COLUMN_TIPO_USER) 
            VALUES ('Gabriel', 'adm123', 'ga.admin@gmail.com', 'Administrador')
        """.trimIndent()

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
        db.execSQL(SQL_CREATE_FAVORITOS_TABLE)
        db.execSQL(SQL_INSERTAR_USUARIO)

        // Insertar 10 recetas
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Tacos de Pollo', 'Tacos rellenos de pollo desmenuzado', 'Pollo, Tortillas, Queso, Salsa', 'https://linktacos.com', 'https://www.hojasanta.es/wp-content/uploads/2024/04/receta-mexicana-de-tacos-de-pollo-1.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Ensalada César', 'Ensalada con lechuga romana, crutones y aderezo César', 'Lechuga, Pollo, Crutones, Queso parmesano, Aderezo César', 'https://linkensalada.com', 'https://www.gastrolabweb.com/u/fotografias/m/2023/9/21/f638x638-52578_110745_5050.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Pasta Alfredo', 'Pasta con salsa Alfredo y pollo', 'Pasta, Crema, Mantequilla, Pollo, Queso parmesano', 'https://linkpasta.com', 'https://4.bp.blogspot.com/-CAZwMGubmwc/VDGGF6z6IeI/AAAAAAAAB3U/Ex0s3u_fWCI/s1600/Chicken%2BAlfredo%2BSyS2.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Pizza Margarita', 'Pizza con salsa de tomate, mozzarella y albahaca', 'Masa, Salsa de tomate, Queso mozzarella, Albahaca', 'https://linkpizza.com', 'https://recetinas.com/wp-content/uploads/2017/09/pizza-de-albahaca-y-tomatitos-cherry-receta.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Sushi Roll', 'Rollos de sushi con salmón y aguacate', 'Arroz, Alga nori, Salmón, Aguacate, Salsa de soya', 'https://linksushi.com', 'https://www.ahumadosdominguez.es/wp-content/uploads/2023/10/makis-de-salmon-ahumado-y-aguacate.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Bistec a la Parrilla', 'Bistec de res cocido a la parrilla', 'Bistec, Sal, Pimienta, Aceite de oliva', 'https://linkbistec.com', 'https://www.wikihow.com/images_en/thumb/e/eb/Grill-Steak-Step-7-preview-Version-2.jpg/550px-nowatermark-Grill-Steak-Step-7-preview-Version-2.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Hamburguesa Clásica', 'Hamburguesa con carne, lechuga, cebolla, tomate y queso', 'Carne molida, Pan, Lechuga, Tomate, Queso cheddar, Cebolla', 'https://linkhamburguesa.com', 'https://img.freepik.com/fotos-premium/hamburguesa-casera-dos-carnes-queso-lechuga-tomate-cebolla_255669-2809.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Paella Valenciana', 'Paella tradicional con mariscos y pollo', 'Arroz, Pollo, Mariscos, Pimiento, Azafrán', 'https://linkpaella.com', 'https://www.demoslavueltaaldia.com/sites/default/files/paella-marisco-pollo-mi-amigo-botiquitas.jpg')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Lasagna Boloñesa', 'Lasagna con carne boloñesa y salsa bechamel', 'Pasta de lasagna, Carne molida, Salsa bechamel, Queso parmesano', 'https://linklasagna.com', 'https://www.cronica.com.ar/img/2022/03/01/lasagna_jpg_1_crop1646142972363.jpg?__scale=w:720,h:406,t:2,fpx:603,fpy:339')")
        db.execSQL("INSERT INTO Receta (Nombre, Descripcion, Ingredientes, Link, Imagen) VALUES ('Tarta de Manzana', 'Tarta dulce con relleno de manzana y canela', 'Masa para tarta, Manzanas, Azúcar, Canela', 'https://linktarta.com', 'https://media.mykaramelli.com/galeria/recetas/tarta-de-manzana-con-crumble-de-canela_100_1.jpg')")

        // Insertar los ingrediente
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Pollo')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Tortillas')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Queso cheddar')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Salsa')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Lechuga')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Crutones')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Queso parmesano')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Aderezo César')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Fideos')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Crema')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Mantequilla')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Masa')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Salsa de tomate')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Queso mozzarella')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Albahaca')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Arroz')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Alga nori')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Salmón')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Aguacate')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Salsa de soya')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Bistec')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Sal')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Pimienta')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Aceite de oliva')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Carne molida')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Pan')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Tomate')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Queso cheddar')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Cebolla')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Mariscos')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Pimiento')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Azafrán')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Pasta de lasagna')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Salsa bechamel')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Masa para tarta')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Manzanas')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Azúcar')")
        db.execSQL("INSERT INTO Ingrediente (Nombre) VALUES('Canela')")

        // Insert Pasos de las recetas creadas
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES('1','Cocinar el pollo hasta que esté completamente cocido y desmenuzar.\nCalentar las tortillas en un sartén.\nColocar el pollo desmenuzado en las tortillas.\nAgregar queso y salsa al gusto.\nServir caliente y disfrutar.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES('2','Lavar y cortar la lechuga en trozos.\nColocar la lechuga en un bol grande.\nAgregar los crutones sobre la lechuga.\nRallar el queso parmesano y espolvorearlo sobre la ensalada.\nAñadir el aderezo César al gusto y mezclar bien.\n Servir inmediatamente y disfrutar.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('3', 'Cocer la pasta según las instrucciones del paquete.\nDerretir la mantequilla en una sartén grande.\nAgregar la crema a la sartén y calentar a fuego lento.\nIncorporar el queso parmesano y mezclar hasta que se derrita.\nMezclar la pasta cocida con la salsa Alfredo y servir caliente.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('4', 'Precalentar el horno a 220°C.\nExtender la masa para pizza sobre una bandeja de horno.\nCubrir la masa con salsa de tomate.\nAgregar el queso mozzarella y hornear durante 15-20 minutos.\nDecorar con hojas de albahaca fresca antes de servir.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('5', 'Cocer el arroz para sushi y enfriarlo.\nColocar una hoja de alga nori sobre una esterilla de bambú.\nExtender el arroz sobre el alga nori, dejando un borde.\nAgregar tiras de salmón y aguacate en el centro.\nEnrollar el sushi con la ayuda de la esterilla y cortar en piezas.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('6', 'Sazonar el bistec con sal y pimienta.\nCalentar una parrilla o sartén con aceite de oliva.\nCocinar el bistec a fuego alto durante 4-5 minutos por cada lado.\nDejar reposar el bistec durante unos minutos antes de servir.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('7', 'Formar la carne molida en hamburguesas.\nCocinar las hamburguesas en una sartén o parrilla.\nTostar el pan en la sartén.\nMontar la hamburguesa con lechuga, tomate y queso cheddar.\nServir caliente.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('8', 'Sofreír el pollo en una paellera.\nAgregar los mariscos y pimientos y cocinar un poco más.\nIncorporar el arroz y el azafrán, y añadir caldo.\nCocinar a fuego medio hasta que el arroz esté listo.\nDejar reposar antes de servir.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('9', 'Preparar la salsa boloñesa con carne molida y salsa de tomate.\nCocinar la pasta de lasagna según las instrucciones del paquete.\nAlternar capas de pasta, salsa boloñesa y salsa bechamel en una fuente para horno.\nCubrir con queso parmesano y hornear durante 30-40 minutos.\nDejar reposar antes de servir.')")
        db.execSQL("INSERT INTO Paso (Codreceta, Descripcion) VALUES ('10', 'Preparar la masa y colocarla en un molde para tarta.\nPelar y cortar las manzanas en rodajas.\nMezclar las manzanas con azúcar y canela.\nColocar las manzanas sobre la masa y cubrir con más masa si se desea.\nHornear a 180°C durante 45-50 minutos.\nDejar enfriar antes de servir.')")


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
        db.execSQL(SQL_CREATE_FAVORITOS_TABLE)
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
    fun modificarReceta(receta: Receta, pasos: Paso, nombre: String): Int {
        val db = this.writableDatabase
        db.beginTransaction()
        var rowsAffected = 0
        try {
            // Obtener el ID de la receta usando el nombre
            val cursor = db.query(
                TABLE_RECETA,
                arrayOf(COLUMN_CODRECETA),
                "$COLUMN_NOMBRE = ?",
                arrayOf(nombre), // Corregido: usar receta.nombre
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
            cursor.close()

            // Verificar si se encontró la receta
            if (id == null) {
                Log.e("ModificarReceta", "Receta no encontrada")
                return 0
            }

            // Crear un ContentValues para actualizar la tabla Receta
            val recetaValues = ContentValues().apply {
                put(COLUMN_NOMBRE, receta.nombre)
                put(COLUMN_DESCRIPCION, receta.descripcion)
                put(COLUMN_INGREDIENTES, receta.ingredientes)
                put(COLUMN_LINK, receta.link)
                put(COLUMN_IMAGEN, receta.imagen)
            }

            // Actualizar la tabla Receta
            rowsAffected = db.update(
                TABLE_RECETA,
                recetaValues,
                "$COLUMN_CODRECETA = ?", // Corregido: usar el ID de la receta
                arrayOf(id.toString())
            )

            // Crear un ContentValues para actualizar los pasos
            val pasoValues = ContentValues().apply {
                put(COLUMN_CODRECETA, id)
                put(COLUMN_DESCRIPCION, pasos.descripcion)
            }

            // Actualizar el paso. Aquí solo se actualiza un paso; si hay varios, necesitarías manejar eso.
            db.update(
                TABLE_PASO,
                pasoValues,
                "$COLUMN_CODRECETA = ?", // Corregido: usar el ID de la receta
                arrayOf(id.toString())
            )

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        return rowsAffected
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
    fun buscarReceta(nombre: String): Pair<Receta?, Paso?> {
        val db = this.readableDatabase
        var recetaBuscada: Receta? = null
        var listaPasos: Paso? = null

        var cursor = db.query(
            "Receta", // Nombre de la tabla
            null, // Columnas (null selecciona todas las columnas)
            "Nombre = ?", // Clausula WHERE
            arrayOf(nombre), // Argumentos de la clausula WHERE
            null, // Group by
            null, // Having
            null // Order by
        )

        if (cursor.count == 0) { // Si no se encontró ningún resultado
            cursor.close() // Cerrar el cursor anterior
            cursor = db.query(
                "Receta", // Nombre de la tabla
                null, // Columnas (null selecciona todas las columnas)
                "Ingredientes LIKE ?", // Clausula WHERE para buscar en ingredientes
                arrayOf("%$nombre%"), // Argumentos de la clausula WHERE con comodines
                null, // Group by
                null, // Having
                null // Order by
            )
        }

        cursor.use { // Esto cierra automáticamente el cursor después de usarlo
            if (it.moveToFirst()) {
                // Obtener los valores de la receta desde el cursor
                val codreceta = it.getString(it.getColumnIndexOrThrow("Codreceta"))
                val nombrereceta = it.getString(it.getColumnIndexOrThrow("Nombre"))
                val descripcion = it.getString(it.getColumnIndexOrThrow("Descripcion"))
                val ingredientes = it.getString(it.getColumnIndexOrThrow("Ingredientes"))
                val link = it.getString(it.getColumnIndexOrThrow("Link"))
                val imagen = it.getString(it.getColumnIndexOrThrow("Imagen"))

                recetaBuscada = Receta(nombrereceta, descripcion, ingredientes, link, imagen)

                // Obtener los pasos relacionados
                val pasosCursor = db.rawQuery("SELECT * FROM Paso WHERE Codreceta = ?", arrayOf(codreceta))

                pasosCursor.use { pasos ->
                    if (pasos.moveToFirst()) {
                        val pasoDescripcion = pasos.getString(pasos.getColumnIndexOrThrow("Descripcion"))
                        listaPasos = Paso(pasoDescripcion)
                    }
                }
            }
        }

        if (cursor.count > 0) {
            Log.d("BuscarReceta", "Recetas encontradas: ${cursor.count}")
        } else {
            Log.d("BuscarReceta", "No se encontró ninguna receta con ese nombre")
        }

        return Pair(recetaBuscada, listaPasos)
    }
    fun obtenerRecetasConPasos(): List<Pair<Receta, List<Paso>>> {
        val db = this.readableDatabase
        val recetasConPasos = mutableListOf<Pair<Receta, List<Paso>>>()

        val cursorRecetas = db.rawQuery("SELECT * FROM Receta", null)

        if (cursorRecetas.moveToFirst()) {
            do {
                val recetaId = cursorRecetas.getLong(cursorRecetas.getColumnIndexOrThrow("Codreceta"))
                val nombre = cursorRecetas.getString(cursorRecetas.getColumnIndexOrThrow("Nombre"))
                val descripcion = cursorRecetas.getString(cursorRecetas.getColumnIndexOrThrow("Descripcion"))
                val ingredientes = cursorRecetas.getString(cursorRecetas.getColumnIndexOrThrow("Ingredientes"))
                val link = cursorRecetas.getString(cursorRecetas.getColumnIndexOrThrow("Link"))
                val imagen = cursorRecetas.getString(cursorRecetas.getColumnIndexOrThrow("Imagen"))

                // Crear la instancia de Receta
                val receta = Receta(nombre, descripcion, ingredientes, link, imagen)

                // Consultar los pasos asociados a la receta actual
                val pasosCursor = db.rawQuery("SELECT * FROM Paso WHERE Codreceta = ?", arrayOf(recetaId.toString()))
                val pasos = mutableListOf<Paso>()

                if (pasosCursor.moveToFirst()) {
                    do {
                        val pasoDescripcion = pasosCursor.getString(pasosCursor.getColumnIndexOrThrow("Descripcion"))
                        pasos.add(Paso(pasoDescripcion))
                    } while (pasosCursor.moveToNext())
                }
                pasosCursor.close()

                // Añadir la receta junto con sus pasos a la lista
                recetasConPasos.add(Pair(receta, pasos))

            } while (cursorRecetas.moveToNext())
        }
        cursorRecetas.close()

        return recetasConPasos
    }
    fun agregarFavorito(nombreReceta: String, usuario: String) {
        val db = this.writableDatabase
        db.beginTransaction()
        try {
            // Buscar el Codreceta de la receta por su nombre
            val cursor = db.query(
                "Receta", // Nombre de la tabla
                arrayOf("CodReceta"), // Columnas a seleccionar
                "Nombre = ?", // Clausula WHERE
                arrayOf(nombreReceta), // Argumentos de la clausula WHERE
                null, // Group by
                null, // Having
                null // Order by
            )
            if (cursor.moveToFirst()) {
                val codReceta = cursor.getLong(cursor.getColumnIndexOrThrow("CodReceta"))

                // Insertar en la tabla Favoritos
                val values = ContentValues().apply {
                    put("CodReceta", codReceta)
                    put("User", usuario)
                }
                db.insert("Favoritos", null, values)
                db.setTransactionSuccessful()
            } else {
                Log.d("AgregarFavorito", "Receta no encontrada: $nombreReceta")
            }
            cursor.close()
        } finally {
            db.endTransaction()
        }
    }
    fun accesoUsuario(nombre: String, pass: String): Usuario? {
        val db = this.readableDatabase
        var buscarusuario: Usuario? = null

        val cursor = db.query(
            "Usuario", // Nombre de la tabla
            null, // Columnas (null selecciona todas las columnas)
            "User = ? AND pass = ?", // Clausula WHERE
            arrayOf(nombre, pass), // Argumentos de la clausula WHERE
            null, // Group by
            null, // Having
            null // Order by
        )

        cursor.use { // Esto cierra automáticamente el cursor después de usarlo
            if (it.moveToFirst()) {
                // Obtener los valores de la receta desde el cursor
                val correo = it.getString(it.getColumnIndexOrThrow("Correo"))
                val tipouser = it.getString(it.getColumnIndexOrThrow("tipo_user"))


                buscarusuario = Usuario(nombre, pass, correo, tipouser)
            }
        }
        return buscarusuario
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
        val cursor = db.query(
            TABLE_INGREDIENTE,  // Tabla
            arrayOf(COLUMN_INGREDIENTE_NOMBRE),  // Columnas a devolver
            "$COLUMN_INGREDIENTE_NOMBRE = ?",  // Clausula WHERE
            arrayOf(nombre),  // Valores para la clausula WHERE
            null,  // Group by
            null,  // Having
            null   // Order by
        )
        return if (cursor.count > 0) {
            // Si el cursor tiene al menos una fila, el ingrediente ya existe
            cursor.close()
            -1  // Retornamos -1 para indicar que no se realizó la inserción
        } else {
            // Si no existe, lo insertamos
            cursor.close()
            val values = ContentValues().apply {
                put(COLUMN_INGREDIENTE_NOMBRE, nombre)
            }
            db.insert(TABLE_INGREDIENTE, null, values)
        }
    }
    fun eliminarIng(nombre: String): Int {
        val db = writableDatabase

        // Verificar si el ingrediente está en alguna receta
        val cursor = db.query(
            TABLE_RECETA,  // Tabla de recetas
            arrayOf(COLUMN_INGREDIENTES),  // Columna de los ingredientes en las recetas
            "$COLUMN_INGREDIENTES LIKE ?",  // Cláusula WHERE con LIKE para buscar coincidencias parciales
            arrayOf("%$nombre%"),  // Valores para la clausula WHERE
            null,  // Group by
            null,  // Having
            null   // Order by
        )
        return if (cursor.count > 0) {
            // Si el cursor tiene al menos una fila, el ingrediente está en una receta
            cursor.close()
            -1  // Retornamos -1 para indicar que no se realizó la eliminación
        } else {
            // Si no está en ninguna receta, eliminamos el ingrediente
            cursor.close()
            db.delete(
                TABLE_INGREDIENTE,  // Tabla
                "$COLUMN_INGREDIENTE_NOMBRE = ?",  // Clausula WHERE
                arrayOf(nombre)  // Valores para la clausula WHERE
            )
        }
    }


    fun getAllFiltros(): List<String> {
        val filtros = mutableListOf<String>()
        val db = this.readableDatabase
        val query = "SELECT $COLUMN_INGREDIENTE_NOMBRE FROM $TABLE_INGREDIENTE"
        Log.d("DBHelper", "Executing query: $query")
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENTE_NOMBRE))
                Log.d("DBHelper", "Filtro encontrado: $nombre")
                filtros.add(nombre)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return filtros
    }

    fun anadirFiltro(nombre: String): Long {
        val db = writableDatabase
        val cursor = db.query(
            COLUMN_FILTRO_DESCRIPCION,  // Tabla
            arrayOf(COLUMN_FILTRO_DESCRIPCION),  // Columnas a devolver
            "$COLUMN_FILTRO_DESCRIPCION = ?",  // Clausula WHERE
            arrayOf(nombre),  // Valores para la clausula WHERE
            null,  // Group by
            null,  // Having
            null   // Order by
        )
        return if (cursor.count > 0) {
            cursor.close()
            -1  // Retornamos -1 para indicar que no se realizó la inserción
        } else {
            // Si no existe, lo insertamos
            cursor.close()
            val values = ContentValues().apply {
                put(COLUMN_FILTRO_DESCRIPCION, nombre)
            }
            db.insert(COLUMN_FILTRO_DESCRIPCION, null, values)
        }
    }
}