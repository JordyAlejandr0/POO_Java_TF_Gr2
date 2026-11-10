# Evidencias de pruebas unitarias y depuración

## 1. Pruebas unitarias

Las pruebas están en la carpeta `test`, paquete `Modelo`, y usan JUnit 4.

| Clase de prueba | Qué verifica | Casos |
|---|---|---|
| `ProyectoTest` | Reglas de negocio de `Proyecto` | 25 |
| `ClienteTest` | Generalización y polimorfismo de `Cliente` | 5 |
| `EmpleadoTest` | Datos y `toString()` de `Empleado` | 2 |

Total: 32 casos de prueba.

### Qué cubre `ProyectoTest`

**Código del proyecto**
- Acepta el formato `aaaa-nnn`.
- Rechaza un código sin guión, por ejemplo `2026001`.
- Rechaza un consecutivo de dos dígitos, por ejemplo `2026-01`.

**Título**
- Rechaza títulos de menos de 20 caracteres.
- Acepta un título de exactamente 20 caracteres, que es el límite inferior.

**Duración según la unidad**
- Acepta 3 semanas, rechaza 4.
- Acepta 11 meses, rechaza 12 porque equivale a un año.
- Rechaza 24 meses por la misma razón.
- Acepta 35 meses, que es el límite superior.
- Rechaza 4 años.
- Rechaza una unidad que no sea semanas, meses o años.

**Estado**
- El estado por defecto es `INICIADO`.
- Se puede cambiar a `EN CURSO`.
- Rechaza un estado que no esté en la lista, por ejemplo `TERMINADO`.

**Fecha de finalización**
- En semanas suma semanas: 2026-02-15 más 2 semanas da 2026-03-01.
- En meses suma meses: 2026-02-15 más 6 meses da 2026-08-15.
- En años suma años: 2026-02-15 más 2 años da 2028-02-15.

**Equipo**
- Un proyecto se puede registrar sin equipo.
- Se agrega un integrante correctamente.
- No se agrega dos veces el mismo número de identificación.
- Se retira un integrante y el método devuelve el empleado retirado.
- Retirar un integrante que no existe devuelve `null`.
- El constructor completo recibe el equipo y el estado.

### Resultado de la ejecución

```
JUnit version 4.13.2
................................
Time: 0,049

OK (32 tests)
```

## 2. Depuración

Esta sección indica los tres puntos del código donde conviene hacer la
depuración y qué se debe observar en cada uno. Las capturas de pantalla
del depurador las toma el equipo al ejecutarlo en NetBeans.

Para depurar en NetBeans: clic en el número de la línea para poner el
punto de interrupción, después `Debug Project` en vez de `Run Project`.
La ventana `Variables` muestra el valor de cada atributo en ese momento.

### Punto 1: el orden de la unidad y la duración

**Dónde.** `Proyecto.java`, método `setDuracionEstimada`, en la línea del
primer `if`.

**Qué observar.** El valor de `unidadDuracion` en la ventana de variables.
Debe llegar ya asignado, porque la validación de la duración depende de
cuál sea la unidad: 11 es válido en meses pero no en semanas ni en años.

**Por qué importa.** En el constructor se llama primero a
`setUnidadDuracion` y después a `setDuracionEstimada`. Si se invierte ese
orden, `unidadDuracion` llega en `null` y el método lanza
`NullPointerException`. En el código quedó un comentario explicando el
porqué de ese orden.

**Caso sugerido para la captura.** Registrar un proyecto de 11 meses y
mostrar que `unidadDuracion` ya vale `meses` cuando se evalúa la condición.

### Punto 2: el fin del archivo de objetos

**Dónde.** `UsaProyecto.java`, método `leerArchivoDeObjetosProyectos`, en
la línea del `datos.add((Proyecto) entrada.readObject());`.

**Qué observar.** Cómo crece la lista `datos` en cada vuelta del ciclo, y
cómo en la última vuelta se entra al `catch` de `EOFException`.

**Por qué importa.** `ObjectInputStream.readObject()` no devuelve `null`
cuando se acaba el archivo, sino que lanza `EOFException`. Por eso el ciclo
usa esa excepción como señal de que ya no hay más registros, en lugar de
preguntar por `null`.

**Caso sugerido para la captura.** Registrar dos proyectos, generar el
archivo, cerrar el programa, abrirlo otra vez y leer el archivo.

### Punto 3: clientes repetidos

**Dónde.** `UsaProyecto.java`, método `leerArchivoDeObjetosClientes`, en la
línea del `if (!estaRepetido)`.

**Qué observar.** El valor de `estaRepetido` y el tamaño de la lista
`datos` en cada vuelta.

**Por qué importa.** El enunciado exige que al leer el archivo de clientes
no se repita ninguno. El método compara el número de identificación contra
los clientes que ya están en la lista antes de agregarlo.

**Caso sugerido para la captura.** Registrar dos proyectos del mismo
cliente, generar el archivo de clientes y leerlo. En el archivo quedan dos
registros pero la lista debe terminar con uno solo.

## 3. Verificación funcional adicional

Además de las pruebas unitarias se ejecutó una verificación de los métodos
de la vista con datos de prueba. Se comprobaron 26 situaciones: las ocho
consultas, el generador de códigos consecutivos, la escritura y lectura de
los dos archivos de objetos, la conservación del equipo y del contacto de
la empresa después de serializar, y el manejo de los casos en que la lista
está vacía o el código no existe. Las 26 pasaron.
