function abrirMenu() {
  var menuContenedor = document.querySelector('.menu-contenedor');
  var menuTexto = menuContenedor.querySelectorAll('.menu-text');
    
  menuContenedor.style.width = '250px'; 
    
  menuTexto.forEach(function (text) {
    text.style.display = 'inline';
  });
}
    
function cerrarMenu() {
  var menuContenedor = document.querySelector('.menu-contenedor');
  var menuTexto = menuContenedor.querySelectorAll('.menu-text');
    
  menuContenedor.style.width = '100px'; 
    
  menuTexto.forEach(function (text) {
    text.style.display = 'none';
  });
}


document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);
document.getElementById('fetchButton').addEventListener('click', pillarDatos);

function pillarDatos() {
  fetch('https://randomuser.me/api/')
      .then(response => response.json())
      .then(data => {
          const user = data.results[0];
          const userDataDiv = document.getElementById('userFoto');
          const userName = document.getElementById('userName');
          const userEmail = document.getElementById('userEmail');
          const userTelefono = document.getElementById('userTelefono');
          const userUbi = document.getElementById('userUbi');
          const userTexto = document.getElementById('userTexto');

          userDataDiv.innerHTML = `
            <div class="card">
              <img src="${user.picture.large}" class="card-img-top" alt="Foto del usuario">
            </div>
          `;

          userTexto.innerHTML = `
            <h5>${user.name.first} ${user.name.last}</h5> <p>es un profesional del sector con amplia experiencia y formacion que hace que sea uno de los mejores en lo que hace.</p>
          `;

          userEmail.innerHTML = `
            <strong>Email:</strong> ${user.email}
          `;

          userTelefono.innerHTML = `
            <strong>Teléfono:</strong> ${user.phone}
          `;

          userUbi.innerHTML = `
            <strong>Ubicación:</strong> ${user.location.city}, ${user.location.country}
          `;
      })
      .catch(error => {
          console.error('Error al obtener los datos:', error);
      });
}