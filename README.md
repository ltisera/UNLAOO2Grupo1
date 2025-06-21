UNLA OO2 Grupo 01 - Sistema de Turnos.
Proyecto web desarrollado en Eclipse IDE (JavaSE - 17), utilizando Spring Boot y MySQL Workbench.


Instalación y ejecución:
1_ Clonar y abrir el proyecto en Eclipse; El programa debería debería resolver automáticamente las dependencias definidas en pom.xml.

2_ Importar el script bd-turnat-spring.sql usando MySQL Workbench (u otra herramienta similar) para crear la base de datos y sus tablas.

3_ Configurar el archivo application.properties ubicado en src/main/resources, este archivo debe tener los datos de conexión a la base de datos y otras configuraciones. Por defecto, el usuario y contraseña son root:
spring.datasource.username=root
spring.datasource.password=root

4_ Configurar variables de entorno en Eclipse yendo a Run --> Run configurations... --> Seleccionar TurnAtApplication (bajo "Java Application") --> Ir a la pestaña Environment --> Agregar las siguientes variables:
MAIL_USERNAME = oo2grupo01@gmail.com
MAIL_PASSWORD = wula ylog ixft iyhu

5_ Antes de ejecutar por primera vez el TurnAtApplication, algunos modulos requieren que existan roles básicos (Admin, empleado, cliente), por lo que hay que ir al archivo TestDataRunner.java y descomentar desde la linea 180 hasta la linea 185.
Luego de descomentar las lineas de TestDataRunner.java, ejecutar una vez TurnAtApplication para que se creen los roles, y luego volver a comentar dichas lineas para evitar duplicados.

6_ Ejecutar TurnAtApplication y acceder desde la aplicación a http://localhost:8080
