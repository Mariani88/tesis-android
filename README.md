# tesis-android

- Breve explicación del objetivo de esta app

Esta aplicación es parte de un prototipo de sistema de detección de incendios de tiempo real. Se encarga de recibir alertas, provenientes de una API Rest, mediante mensajes de Firebase.

Esta API recibe alertas desde una alarma y las envía a Firebase, para que este último las redirija a esta aplicación. Las alertas contienen información de la ubicación del incendio, de manera tal que el usuario pueda localizar el lugar del incendio. 


- Características técnicas

Esta aplicación corre a partir de Android 4.0.3. Requiere de acceso a Internet y de permisos de localización.


- Dependencias del proyecto

Java 8 
Gradle 4.1

El conjunto de aplicaciones se encuentra en los siguientes repositorios:

Software de la alarma: https://github.com/Mariani88/tesis-alarm
La aplicacion Android que configura la alarma: https://github.com/Mariani88/tesis-android-config
La API Rest que recibe las alertas: https://github.com/Mariani88/tesis-server
La aplicación mobile receptora de alertas: https://github.com/Mariani88/tesis-android
