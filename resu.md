# Seguridad Informática: Práctica 1

## 1. Cookies y web bugs

En este apartado repasaremos el funcionamiento de las cookies propuestas. Las cookies son unas variables que se alojan en tu ordenador y que recuperan los servidores, de manera que pueden conocer información que trasciende de una página a otra.

La primera cookie (contador) se aprecia en el ```alert``` que salta al acceder a la página. Esta cookie almacena e incrementa la cantidad de veces que al sitio y personaliza su mensaje de saludo. La cookie se mantiene incluso si cerramos el navegador.

La segunda cookie (cookies_usuario) pide que te identifiques con un nombre y te lo asigna si cambias de página. La cookie se pierde cuando cambias de sitio web (o en ese caso no evita el volver a pedir que te identifiques).

La tercera cookie (mostrar_mensaje_inicio) te muestra un mensaje de advertencia la primera vez que la abres y se vuelve a mostrar cuando el día de la semana no coincide con el último en el que accediste. La cookie se pierde cuando cambias de sitio web.

La última cookie a analizar (prueba_poner_quitar) primero crea una cookie y te muestra un alert. Cuando aceptas borra la cookie y te muestra un mensaje de que ya no está la cookie.

Esta tecnología se encuentra en la mayoría de sitios web con diversidad de usos. Un ejemplo son las cookies del Campus Virtual (cv.usc.es), ```MoodleSession``` y ```PHPSESSIONID```, que almacenan tu sesión con la plataforma y con el servidor. Otro ejemplo es el conjunto de cookies ```uData``` de PcComponentes (www.pccomponentes.com), que guarda el estado de las páginas anteriores del sitio con información como, por ejemplo, los filtros que aplicaste a su catálogo.

Los web bugs tienen un funcionamiento diferente a las cookie pero se utilizan para motivos muy similares a las cookies de terceros. Cargan información de otro servidor en la página de manera que llega una solicitud a ese servidor y obtiene información relativa a tu actividad.

En la página de inicio de www.megadede.com encontramos un web bug que proviene de www.googletagmanager.com. En la mayoría de casos, como se puede ver a continuación, es un ```<iframe>``` de tamaño 0x0 o 1x1 píxeles. En este caso, ese dominio pertenece a Google y se dedica a recopilar información.

``` HTML
<!-- Google Tag Manager (noscript) -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-P4LSJPZ"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->
```

Y aquí vemos otro caso de dos web bugs en la página web del periódico The Guardian (www.theguardian.com).

```HTML
<img src="//phar.gu-web.net/count/pv.gif" alt="" style="display : none ;" rel="nofollow"/>
<img src="https://www.facebook.com/tr?id=279880532344561&ev=PageView&noscript=1" height="1" width="1" style="display:none" rel="nofollow" alt=""/>
```

## 2. Políticas de privacidad

## 3. Mejorar la privacidad de un dispositivo

En este apartado se analizará un dispositivo desde el punto de vista de la privacidad y se aportarán modificaciones para mejorarla.

El dispositivo elegido es mi propio portátil, un HP Pavilion Power 15 con Windows 10 Pro como SO. Al ser mi ordenador portátil personal su localización es, casi siempre, la misma que la mía. Esto hace que siempre lo tenga más o menos a la vista; pero también expone mi información al sacarlo constantemente de mi casa.

El único usuario del ordenador soy yo. Excepcionalmente podrían utilizarlo otras personas con mi permiso y accediendo con mi usuario. Debería crear un usuario para "invitados" o usuarios ocasionales para que no tengan acceso a mi información más sensible. Sin embargo, no es muy preocupante ya que esos usuarios, por lo general, son de confianza y suelo estar presente cuando esto ocurre.

Este es el dispositivo que, junto con el móvil, utilizo todos los días. En él hago todos los trabajos de la universidad, entro en algunas (pero pocas) redes sociales, compro online y, en general, navego por Internet. Por tanto, el portátil almacena y envía mucha información personal cada día.

Teniendo en cuenta todo esto pasaré a analizar determinados aspectos del uso y la configuración del dispositivo para tratar de mejorar mi privacidad.

* 
