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
          const userTexto = document.getElementById('userText');

          userDataDiv.innerHTML = `
            <div class="card">
              <img src="${user.picture.large}" class="card-img-top" alt="Foto del usuario">
            </div>
          `;

          userName.innerHTML = `
            <h5 class="nombre">${user.name.first} ${user.name.last}</h5>
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