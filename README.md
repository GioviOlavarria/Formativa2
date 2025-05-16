# Formativa2

Stack técnico:

Framework  Spring 3.4.5
Conectividad a base de datos   Spring Data Jpa
API  Rest (Spring Web)
Base de Datos   MySQL
Herramienta de creación   Maven
Simplificación de código   Lombok


Este es un sistema de librería el cual nos permite la creación de usuarios, el agregado de libros, peticiones de libros por parte del usuario, evita que los usuarios pidan un libro que ya está en uso, permite borrar y actualizar libros y usuario. También cuenta con un sistema de fechas reales para préstamos y devoluciones de libros, el cual considera la fecha actual del dispositivo en el que se esté ejecutando.


# Paso a paso para ejecutar el programa:

Primero, utilizaremos Laragon para prender nuestro MySQL, seguido de esto, abriremos la base de datos de MySQL y utilizando Heidi crearemos una base de datos la cual utilice MySQL en el usuario root, sin contraseña. 
Una vez creada, se hará una consulta en la cual se cree una base de datos llamada "dv_formativa2" (Está bien escrito)
Una vez creada esta base de datos, es posible ejecutar el programa sin problemas y nuestros datos se comenzarán a guardar dentro de esta. Se puede probar con el Postman!
