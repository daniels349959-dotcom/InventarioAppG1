# InventarioAppG1

Aplicación de inventario con JavaFX, FXML, JDBC y MySQL.
Cumple el ejercicio: login, main, create, navegación, consulta y guardado.

## Ejecutar en este ordenador

1. En Docker Desktop, inicia dam-inventario-mysql si está detenido.
2. Abre el proyecto en IntelliJ y ejecuta la configuración Launcher.
3. Pulsa Login para ver los equipos. Crear equipo abre el formulario.
4. Introduce un nombre, elige la categoría y pulsa Guardar.

La conexión es localhost:3307, base ad. USER y PASSWORD están configuradas
localmente en Launcher, fuera del código fuente. Los datos están en MySQL.
El login es el botón de entrada del original, sin autenticación de usuarios.

## Preparar en otro ordenador

- Importa pom.xml como proyecto Maven. Usa un JDK 17 o superior; se ha
  comprobado con Corretto 24.0.2 y el procesamiento de Lombok activado.
- Ejecuta esquema.sql en MySQL. Crea la base y la tabla sin borrar registros.
- Ajusta el puerto en DataSourceFactory si tu MySQL no utiliza 3307.
- En Run > Edit Configurations > Launcher > Environment variables,
  configura USER y PASSWORD con tus credenciales MySQL.
- Ejecuta Launcher.java.

En este equipo se utiliza Build Project de IntelliJ. La ejecución de Maven
por consola dio un error de ruta y no se da por verificada.

## Qué hace cada archivo

| Archivo | Responsabilidad |
| --- | --- |
| AppG1 y Launcher | Arrancar JavaFX y abrir login. |
| SceneNavigator | Cargar un FXML y cambiar la escena de la ventana. |
| LoginController | Abrir la vista principal. |
| MainController | Preparar las columnas, consultar equipos y navegar. |
| DetailsController | Preparar categorías, validar y guardar equipos. |
| Equipo | Representar los cuatro datos de un equipo. |
| DataSourceFactory | Preparar y reutilizar la configuración de MySQL. |
| DAO y EquipoJdbcDAO | Definir y ejecutar las operaciones de base de datos. |
| Los tres FXML | Definir controles, distribución y botones de cada vista. |

## Qué venía del proyecto original

- La separación entre controladores, modelos, datos y vistas.
- SceneNavigator y la conexión mediante DataSource.
- El DAO genérico con findById, findAll, save, update y deleteById.
- Optional para representar una búsqueda que puede no encontrar un equipo.
- Lombok @Data para generar getters y setters.
- Las lambdas que indican qué dato muestra cada columna de la tabla.
- JDBC con PreparedStatement y try con recursos para cerrar las conexiones.

Se conservan porque ya forman parte de la base del profesor y el enunciado
pide reutilizar el DAO. Su lógica SQL no se ha cambiado. PreparedStatement
separa el SQL de los valores introducidos. El cierre automático evita dejar
conexiones abiertas. No se han añadido patrones ni librerías nuevas.

## Qué se ha completado y simplificado

- Botones para navegar entre las tres vistas.
- create-view.fxml reutiliza el formulario y DetailsController originales.
- Guardado real llamando a EquipoJdbcDAO.save().
- Validación del nombre y la categoría y avisos de error o éxito.
- El identificador lo genera MySQL; se ha retirado su entrada manual.
- initialize() sencillo con @FXML, sin Initializable ni parámetros sin uso.
- Tipos explícitos, if/else y un for para cargar la tabla.
- Conexión con variables locales y un único método getDataSource().
- Comentarios breves y eliminación de imports, posiciones y código sin uso.

La navegación se realiza al terminar el guardado. Al abrir main, se consulta
otra vez la base de datos. Disponible puede estar marcado o desmarcado.

## Comprobaciones

Las pruebas se realizan desde la interfaz y con MySQL real. Se conservan los
registros de prueba para poder revisar el resultado. El XML de las tres
vistas y git diff --check se comprueban también antes de entregar.

Pruebas de esta revisión:
- Compilación y arranque en IntelliJ con Corretto 24.0.2.
- Navegación login -> main -> create, Volver a main y regreso a login.
- Consulta de los dos registros existentes en MySQL.
- Intento de guardar con nombre vacío: aviso de validación y sin inserción.
- Guardado de Equipo revision final: confirmación correcta e identificador 3.
- Consulta posterior: el registro aparece con categoría Keyboard y no disponible.
- Comprobación de que la lógica del DAO es igual a la del repositorio original.
- XML de las tres vistas válido y git diff --check sin errores.

La aplicación queda abierta en login.
