# SearchMovies

1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué clases pertenecen a cual.
Se creó una interfaz donde se definen las APIs a consumir
Se creó un modelo para poder serializar los resultados obtenidos por Retrofit
Se crearon 4 layouts para manejar las vistas
Hay un Adapatador que manipula la data obtenida de las APIs para ser mostrada en el RecyclerView
Se usa la libreria CircleTransformation para darle una forma diferente a las imagenes

2. La responsabilidad de cada clase creada.
MainActivity es la clase principal que maneja la vista y la búsqueda extendiendo de la clase SearchView
La clase ImageActivity maneja las fotos ampliadas
DetailActivity controla toda la información cuando se detalla un película

3. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
Es asignar o delegar a cada clase, interfaz o elemento, etc una determinada funcionalidad
Por ejemplo, si deseo crear y eliminar un elemento lo recomendable es crear una función con la responsabilidad de crear y otra funcón
con la responsabilidad de eliminar

4. Qué características tiene, según su opinión, un “buen” código o código limpio?
Es aquel código que maneja buenas prácticas y es mantenible en tiempo. También suficientemente claro de leer
