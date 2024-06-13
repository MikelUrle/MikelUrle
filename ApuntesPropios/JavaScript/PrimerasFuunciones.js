// Condicionales

// "IF"

let year = prompt('¿En que año fué publicada la especificación ECMAScript-2015?', '');

if (year == 2015) alert( '¡Estás en lo cierto!' );

// Para mas de una sentencia

if (year == 2015) {
    alert( "¡Es Correcto!" );
    alert( "¡Eres muy inteligente!" );
}

// "else"

let year1 = prompt('¿En qué año fue publicada la especificación ECMAScript-2015?', '');

if (year == 2015) {
  alert( '¡Lo adivinaste, correcto!' );
} else {
  alert( '¿Cómo puedes estar tan equivocado?' ); // cualquier valor excepto 2015
}



let year2 = prompt('¿En qué año fue publicada la especificación ECMAScript-2015?', '');

if (year < 2015) {
  alert( 'Muy poco...' );
} else if (year > 2015) {
  alert( 'Muy Tarde' );
} else {
  alert( '¡Exactamente!' );
}

// Operacion ternario o "?"

let accessAllowed;
let age = prompt('¿Qué edad tienes?', '');

if (age > 18) {
  accessAllowed = true;
} else {
  accessAllowed = false;
}

alert(accessAllowed);

// El “operador condicional” nos permite ejecutar esto en una forma más corta y simple.

// El operador está representado por el signo de cierre de interrogación ?. A veces es llamado “ternario” porque el operador tiene tres operandos, es el único operador de JavaScript que tiene esa cantidad.

let result = condition ? value1 : value2;

// Se evalúa condition: si es verdadera entonces devuelve value1 , de lo contrario value2.

let accessAllowed1 = (age > 18) ? true : false;

// Una secuencia de operadores de signos de interrogación ? puede devolver un valor que depende de más de una condición.

let age1 = prompt('¿edad?', 18);

let message = (age < 3) ? '¡Hola, bebé!' :
  (age < 18) ? '¡Hola!' :
  (age < 100) ? '¡Felicidades!' :
  '¡Qué edad tan inusual!';

alert( message );

// El primer signo de pregunta revisa si age < 3.
// Si es cierto, devuelve '¡Hola, bebé!'. De lo contrario, continúa a la expresión que está después de los dos puntos “:”, la cual revisa si age < 18.
// Si es cierto, devuelve '¡Hola!'. De lo contrario, continúa con la expresión que está después de los dos puntos siguientes “:”, la cual revisa si age < 100.
// Si es cierto, devuelve '¡Felicidades!'. De lo contrario, continúa a la expresión que está después de los dos puntos “:”, la cual devuelve '¡Qué edad tan inusual!'.

