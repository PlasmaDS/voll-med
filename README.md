# VollMed API

La API VollMed es un sistema de gestión de usuarios y consultas diseñado para una institución médica. Esta API proporciona funcionalidades esenciales como el registro de usuarios, inicio de sesión y categorías de médicos y clientes.

## Características principales

- Registro de usuarios: Los usuarios pueden registrarse en la plataforma proporcionando la información necesaria.

- Inicio de sesión: Los usuarios registrados pueden iniciar sesión de forma segura utilizando sus credenciales.

- Categorías de médicos y clientes: Los usuarios se dividen en dos categorías principales: médicos y clientes, cada uno con sus respectivos roles y permisos.

- Gestión de consultas: La API permite a los médicos y clientes gestionar sus consultas médicas, programar citas y realizar seguimiento de su historial médico.

## Tecnologías utilizadas

- Java: El backend de la API está desarrollado en Java.

- Spring Boot: Se utiliza el framework Spring Boot para crear una aplicación Java robusta y escalable.

- API Rest: La API sigue los principios de arquitectura REST para una comunicación eficiente y segura.

- JSON: El intercambio de datos se realiza en formato JSON, lo que facilita la interoperabilidad con otras aplicaciones.

## Instalación

Para ejecutar la API VollMed en tu entorno local, sigue estos pasos:

1. Clona el repositorio de GitHub.

```bash
git clone https://github.com/PlasmaDS/voll-med.git
```

2. Configura la base de datos y las propiedades de la aplicación en `application.yml`.

3. Ejecuta la aplicación.

```bash
./mvnw spring-boot:run
```

## Uso

Una vez que la API está en funcionamiento, puedes comenzar a utilizarla a través de las rutas y endpoints proporcionados. Consulta la documentación de la API para obtener detalles sobre cómo interactuar con ella.
