- _**Autor**: Nicolás Esteban_
- _**Fecha**: mayo/2022_


# moviesApp [Android]
Este proyecto forma parte de una serie de proyectos que he realizado durante mi periodo de prácticas FCT.

El proyecto consiste en una aplicación móvil de películas en la cual el usuario puede encontrar informacion relacionada con los últimos estrenos, películas populares últimamente y una lista con las películas mejor valoradas.

## Información del Proyecto:
- Está desarrollado utilizando el lenguaje de programación **Kotlin** recomendado por Google para desarrollar aplicaciones Android nativas.
- El proyecto ha sido realizado con la finalidad de aprender a desarrollar aplicaciones nativas en Android. Por tanto, 
se trata de un proyecto básico con pocas funcionalidades pero en el cual he implementado conceptos y tecnologías recomendadas por Google como lo son 
el uso de arquitectura **MVVM**, **Retrofit** o **Room** entre otros.
- También he incluido el concepto de "**origen único de la información**" (**"Single source of truth"**). Esto lo he realizado implementando una base de datos local con Room en la cual se almacena la información recibida de la API. Por tanto, la información que se muestra al usuario es siempre la que está almacenada en la base de datos local. Como consecuencia uno de los beneficios que se obtienen es que el usuario puede consultar los últimos datos recibidos del servidor aunque no tenga acceso a internet.
- La información de las películas es obtenida de la API [TMDB](themoviedb.org).
- La gestión de la autenticación de los usuarios (inicio de sesión y registro) ha sido realizada con [Firebase Authentication](https://firebase.google.com).

## Screenshots:

![Captura de pantalla 2022-04-28 161010](https://user-images.githubusercontent.com/43449804/167816160-fd53b130-cacd-426c-80a4-8cd85ad33a5a.png)




