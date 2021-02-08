# ChallengeBE - Operación Fuego de Quasar 

## Objetivo

Obtener la posición de la fuente del llamado de auxilio junto con el mensaje a partir de la información provista por los tres satelites: Sato, Kenobi y Skywalker.

#### Ejecución del programa



#### Documentación APIs con swagger

http://localhost:8080/swagger-ui.html

#### URL host










#### *Calculos auxiliares para realizar la triangulación.*

La triangulación consiste en buscar el punto de intersección entre tres esferas. 
Para este caso en particular se trabaja con 2D. Por lo tanto, se partirá con la ecuación de la circunsferencia:

<a href="https://www.codecogs.com/eqnedit.php?latex=r{_{1}}^{2}&space;=&space;(x-a)^{2}&space;&plus;&space;(y-b)^{2}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?r{_{1}}^{2}&space;=&space;(x-a)^{2}&space;&plus;&space;(y-b)^{2}" title="r{_{1}}^{2} = (x-a)^{2} + (y-b)^{2}" /></a> ,  siendo <a href="https://www.codecogs.com/eqnedit.php?latex=a" target="_blank"><img src="https://latex.codecogs.com/gif.latex?a" title="a" /></a> y <a href="https://www.codecogs.com/eqnedit.php?latex=b" target="_blank"><img src="https://latex.codecogs.com/gif.latex?b" title="b" /></a> desplazamientos en <a href="https://www.codecogs.com/eqnedit.php?latex=x" target="_blank"><img src="https://latex.codecogs.com/gif.latex?x" title="x" /></a> e <a href="https://www.codecogs.com/eqnedit.php?latex=y" target="_blank"><img src="https://latex.codecogs.com/gif.latex?y" title="y" /></a>, respectivamente y <a href="https://www.codecogs.com/eqnedit.php?latex=r{_{1}}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?r{_{1}}" title="r{_{1}}" /></a> el radio de la misma.

A continuación, se procede a despejar la ecuación separando los datos conocidos y las dos incognitas,

<a href="https://www.codecogs.com/eqnedit.php?latex=r{_{1}}^{2}&space;-&space;(a^{2}&space;&plus;&space;b^{2)})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2ax&space;-&space;2by" target="_blank"><img src="https://latex.codecogs.com/gif.latex?r{_{1}}^{2}&space;-&space;(a^{2}&space;&plus;&space;b^{2)})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2ax&space;-&space;2by" title="r{_{1}}^{2} - (a^{2} + b^{2)}) = x^{2} + y^{2} - 2ax - 2by" /></a>


Definiendo,

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;(x{_{k}},&space;y{_{k}})\rightarrow&space;\text{&space;posicion&space;de&space;Kenobi&space;}&space;\\&space;&&space;(x{_{sky}},&space;y{_{sky}})\rightarrow&space;\text{&space;posicion&space;de&space;Skywalker&space;}&space;\\&space;&&space;(x{_{s}},&space;y{_{s}})\rightarrow&space;\text{&space;posicion&space;de&space;Sato&space;}&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;(x{_{k}},&space;y{_{k}})\rightarrow&space;\text{&space;posicion&space;de&space;Kenobi&space;}&space;\\&space;&&space;(x{_{sky}},&space;y{_{sky}})\rightarrow&space;\text{&space;posicion&space;de&space;Skywalker&space;}&space;\\&space;&&space;(x{_{s}},&space;y{_{s}})\rightarrow&space;\text{&space;posicion&space;de&space;Sato&space;}&space;\end{cases}" title="\begin{cases} & (x{_{k}}, y{_{k}})\rightarrow \text{ posicion de Kenobi } \\ & (x{_{sky}}, y{_{sky}})\rightarrow \text{ posicion de Skywalker } \\ & (x{_{s}}, y{_{s}})\rightarrow \text{ posicion de Sato } \end{cases}" /></a>

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;r{_{k}}\rightarrow&space;\text{&space;distancia&space;de&space;Kenobi&space;a&space;la&space;nave&space;}&space;\\&space;&&space;r{_{sky}}\rightarrow&space;\text{&space;distancia&space;de&space;Skywalker&space;a&space;la&space;nave&space;}&space;\\&space;&&space;r{_{s}}\rightarrow&space;\text{&space;distancia&space;de&space;Sato&space;a&space;la&space;nave&space;}&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;r{_{k}}\rightarrow&space;\text{&space;distancia&space;de&space;Kenobi&space;a&space;la&space;nave&space;}&space;\\&space;&&space;r{_{sky}}\rightarrow&space;\text{&space;distancia&space;de&space;Skywalker&space;a&space;la&space;nave&space;}&space;\\&space;&&space;r{_{s}}\rightarrow&space;\text{&space;distancia&space;de&space;Sato&space;a&space;la&space;nave&space;}&space;\end{cases}" title="\begin{cases} & r{_{k}}\rightarrow \text{ distancia de Kenobi a la nave } \\ & r{_{sky}}\rightarrow \text{ distancia de Skywalker a la nave } \\ & r{_{s}}\rightarrow \text{ distancia de Sato a la nave } \end{cases}" /></a>
 
 
Se genera el siguientes sistema de tres ecuaciones con dos incognitas:

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;r{_{k}}^{2}&space;-&space;(x_{k}^{2}&space;&plus;&space;y_{k}^{2)})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2x_{k}x&space;-&space;2y_{k}y&space;\rightarrow&space;\text{&space;A&space;}&space;\\&space;&&space;r{_{sky}}^{2}&space;-&space;(x_{sky}^{2}&space;&plus;&space;y_{sky}^{2})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2x_{sky}x&space;-&space;2y_{sky}y&space;\rightarrow&space;\text{&space;B&space;}&space;\\&space;&&space;r{_{s}}^{2}&space;-&space;(x_{s}^{2}&space;&plus;&space;y_{s}^{2)})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2x_{s}x&space;-&space;2y_{s}y&space;\rightarrow&space;\text{&space;C&space;}&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;r{_{k}}^{2}&space;-&space;(x_{k}^{2}&space;&plus;&space;y_{k}^{2)})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2x_{k}x&space;-&space;2y_{k}y&space;\rightarrow&space;\text{&space;A&space;}&space;\\&space;&&space;r{_{sky}}^{2}&space;-&space;(x_{sky}^{2}&space;&plus;&space;y_{sky}^{2})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2x_{sky}x&space;-&space;2y_{sky}y&space;\rightarrow&space;\text{&space;B&space;}&space;\\&space;&&space;r{_{s}}^{2}&space;-&space;(x_{s}^{2}&space;&plus;&space;y_{s}^{2)})&space;=&space;x^{2}&space;&plus;&space;y^{2}&space;-&space;2x_{s}x&space;-&space;2y_{s}y&space;\rightarrow&space;\text{&space;C&space;}&space;\end{cases}" title="\begin{cases} & r{_{k}}^{2} - (x_{k}^{2} + y_{k}^{2)}) = x^{2} + y^{2} - 2x_{k}x - 2y_{k}y \rightarrow \text{ A } \\ & r{_{sky}}^{2} - (x_{sky}^{2} + y_{sky}^{2}) = x^{2} + y^{2} - 2x_{sky}x - 2y_{sky}y \rightarrow \text{ B } \\ & r{_{s}}^{2} - (x_{s}^{2} + y_{s}^{2)}) = x^{2} + y^{2} - 2x_{s}x - 2y_{s}y \rightarrow \text{ C } \end{cases}" /></a>

Para obtener la resolución, se optó realizar la resta entre las ecuaciones A y B, obteniendo:

<a href="https://www.codecogs.com/eqnedit.php?latex=r{_{k}}^{2}-r{_{sky}}^{2}\underbrace{-(x{_{k}}^{2}&plus;y{_{k}}^{2)})&plus;(x{_{sky}}^{2}&plus;y{_{sky}}^{2)})}=2x(\underbrace{x{_{sky}}-x{_{k}}})&plus;2y(\underbrace{y{_{sky}}-y{_{k}}})" target="_blank"><img src="https://latex.codecogs.com/gif.latex?r{_{k}}^{2}-r{_{sky}}^{2}\underbrace{-(x{_{k}}^{2}&plus;y{_{k}}^{2)})&plus;(x{_{sky}}^{2}&plus;y{_{sky}}^{2)})}=2x(\underbrace{x{_{sky}}-x{_{k}}})&plus;2y(\underbrace{y{_{sky}}-y{_{k}}})" title="r{_{k}}^{2}-r{_{sky}}^{2}\underbrace{-(x{_{k}}^{2}+y{_{k}}^{2)})+(x{_{sky}}^{2}+y{_{sky}}^{2)})}=2x(\underbrace{x{_{sky}}-x{_{k}}})+2y(\underbrace{y{_{sky}}-y{_{k}}})" /></a>

Definiendo, 

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;(x{_{sky}}-x{_{k}})&space;\text{&space;}&space;\rightarrow&space;\Delta&space;x_{1}&space;\\&space;&&space;(y{_{sky}}-y{_{k}})&space;\text{}&space;\rightarrow&space;\Delta&space;y_{1}&space;\\&space;&&space;-(x{_{k}^{2}}&plus;y{_{k}^{2}})&plus;(x{_{sky}^{2}}&plus;y{_{sky}^{2}})&space;\text{&space;}&space;\rightarrow&space;numerator1&space;\\&space;&&space;-(x{_{k}^{2}}&plus;y{_{k}^{2}})&plus;(x{_{s}^{2}}&plus;y{_{s}^{2}})&space;\text{&space;}&space;\rightarrow&space;numerator2&space;\\&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;(x{_{sky}}-x{_{k}})&space;\text{&space;}&space;\rightarrow&space;\Delta&space;x_{1}&space;\\&space;&&space;(y{_{sky}}-y{_{k}})&space;\text{}&space;\rightarrow&space;\Delta&space;y_{1}&space;\\&space;&&space;-(x{_{k}^{2}}&plus;y{_{k}^{2}})&plus;(x{_{sky}^{2}}&plus;y{_{sky}^{2}})&space;\text{&space;}&space;\rightarrow&space;numerator1&space;\\&space;&&space;-(x{_{k}^{2}}&plus;y{_{k}^{2}})&plus;(x{_{s}^{2}}&plus;y{_{s}^{2}})&space;\text{&space;}&space;\rightarrow&space;numerator2&space;\\&space;\end{cases}" title="\begin{cases} & (x{_{sky}}-x{_{k}}) \text{ } \rightarrow \Delta x_{1} \\ & (y{_{sky}}-y{_{k}}) \text{} \rightarrow \Delta y_{1} \\ & -(x{_{k}^{2}}+y{_{k}^{2}})+(x{_{sky}^{2}}+y{_{sky}^{2}}) \text{ } \rightarrow numerator1 \\ & -(x{_{k}^{2}}+y{_{k}^{2}})+(x{_{s}^{2}}+y{_{s}^{2}}) \text{ } \rightarrow numerator2 \\ \end{cases}" /></a>

Se procede a dividir la ecuación obtenida por <a href="https://www.codecogs.com/eqnedit.php?latex=\Delta&space;y_{1}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\Delta&space;y_{1}" title="\Delta y_{1}" /></a> obteniendo,

<a href="https://www.codecogs.com/eqnedit.php?latex=\frac{(A-B)}{\Delta&space;y_{1}}&space;\Rightarrow&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;=&space;2x\frac{\Delta&space;x_{1}}{\Delta&space;y_{1}}&space;&plus;&space;2y" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\frac{(A-B)}{\Delta&space;y_{1}}&space;\Rightarrow&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;=&space;2x\frac{\Delta&space;x_{1}}{\Delta&space;y_{1}}&space;&plus;&space;2y" title="\frac{(A-B)}{\Delta y_{1}} \Rightarrow \frac{ r{_{k}}^{2} - r{_{sky}}^{2} + numerator_{1} }{\Delta y_{1}} = 2x\frac{\Delta x_{1}}{\Delta y_{1}} + 2y" /></a>

Analogamente con las ecuaciones A y C,

<a href="https://www.codecogs.com/eqnedit.php?latex=\frac{(A-C)}{\Delta&space;y_{2}}&space;\Rightarrow&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}&space;=&space;2x\frac{\Delta&space;x_{2}}{\Delta&space;y_{2}}&space;&plus;&space;2y" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\frac{(A-C)}{\Delta&space;y_{2}}&space;\Rightarrow&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}&space;=&space;2x\frac{\Delta&space;x_{2}}{\Delta&space;y_{2}}&space;&plus;&space;2y" title="\frac{(A-C)}{\Delta y_{2}} \Rightarrow \frac{ r{_{k}}^{2} - r{_{s}}^{2} + numerator_{2} }{\Delta y_{2}} = 2x\frac{\Delta x_{2}}{\Delta y_{2}} + 2y" /></a>

A continuación, se definen lo siguiente:

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;\frac{\Delta&space;x_{1}}{\Delta&space;y_{1}}&space;\rightarrow&space;slope1&space;\\&space;&&space;\frac{\Delta&space;x_{2}}{\Delta&space;y_{2}}&space;\rightarrow&space;slope2&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;\frac{\Delta&space;x_{1}}{\Delta&space;y_{1}}&space;\rightarrow&space;slope1&space;\\&space;&&space;\frac{\Delta&space;x_{2}}{\Delta&space;y_{2}}&space;\rightarrow&space;slope2&space;\end{cases}" title="\begin{cases} & \frac{\Delta x_{1}}{\Delta y_{1}} \rightarrow slope1 \\ & \frac{\Delta x_{2}}{\Delta y_{2}} \rightarrow slope2 \end{cases}" /></a>

Reemplazando en las ecuaciones se obteniene,

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;=&space;2slope_{1}x&space;&plus;&space;2y&space;\rightarrow&space;\text{D}&space;\\&space;&&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}&space;=&space;2slope_{2}x&space;&plus;&space;2y&space;\rightarrow&space;\text{E}&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;=&space;2slope_{1}x&space;&plus;&space;2y&space;\rightarrow&space;\text{D}&space;\\&space;&&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}&space;=&space;2slope_{2}x&space;&plus;&space;2y&space;\rightarrow&space;\text{E}&space;\end{cases}" title="\begin{cases} & \frac{ r{_{k}}^{2} - r{_{sky}}^{2} + numerator_{1} }{\Delta y_{1}} = 2slope_{1}x + 2y \rightarrow \text{D} \\ & \frac{ r{_{k}}^{2} - r{_{s}}^{2} + numerator_{2} }{\Delta y_{2}} = 2slope_{2}x + 2y \rightarrow \text{E} \end{cases}" /></a>

A partir de este nuevo sistema, se restan las ecuaciones D y E, y se despeja la <a href="https://www.codecogs.com/eqnedit.php?latex=x" target="_blank"><img src="https://latex.codecogs.com/gif.latex?x" title="x" /></a>

<a href="https://www.codecogs.com/eqnedit.php?latex=x&space;=&space;\frac{&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;-&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}}{2(slope_{1}-slope_{2})}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?x&space;=&space;\frac{&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;-&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}}{2(slope_{1}-slope_{2})}" title="x = \frac{ \frac{ r{_{k}}^{2} - r{_{sky}}^{2} + numerator_{1} }{\Delta y_{1}} - \frac{ r{_{k}}^{2} - r{_{s}}^{2} + numerator_{2} }{\Delta y_{2}}}{2(slope_{1}-slope_{2})}" /></a>


Como resultado,

<a href="https://www.codecogs.com/eqnedit.php?latex=\begin{cases}&space;&&space;x&space;=&space;\frac{&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;-&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}}{2(slope_{1}-slope_{2})}&space;\\&space;&&space;y&space;=&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}&space;-&space;slope_{2}x&space;\end{cases}" target="_blank"><img src="https://latex.codecogs.com/gif.latex?\begin{cases}&space;&&space;x&space;=&space;\frac{&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{sky}}^{2}&space;&plus;&space;numerator_{1}&space;}{\Delta&space;y_{1}}&space;-&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}}{2(slope_{1}-slope_{2})}&space;\\&space;&&space;y&space;=&space;\frac{&space;r{_{k}}^{2}&space;-&space;r{_{s}}^{2}&space;&plus;&space;numerator_{2}&space;}{\Delta&space;y_{2}}&space;-&space;slope_{2}x&space;\end{cases}" title="\begin{cases} & x = \frac{ \frac{ r{_{k}}^{2} - r{_{sky}}^{2} + numerator_{1} }{\Delta y_{1}} - \frac{ r{_{k}}^{2} - r{_{s}}^{2} + numerator_{2} }{\Delta y_{2}}}{2(slope_{1}-slope_{2})} \\ & y = \frac{ r{_{k}}^{2} - r{_{s}}^{2} + numerator_{2} }{\Delta y_{2}} - slope_{2}x \end{cases}" /></a>

