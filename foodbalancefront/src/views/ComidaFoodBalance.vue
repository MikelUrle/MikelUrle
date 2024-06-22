<script>
import MenuFoodBalance from "../components/MenuFoodBalance.vue";
import HeaderFoodBalance from "../components/HeaderFoodBalance.vue";

export default {
    data() {
        return {
          people: [
            { nombre: 'Mark', calorias: 'Otto', proteina: '@mdo', grasa: 'Otto', hc: '@mdo', fibra: '@mdo', extra: '@msddo',},
            { nombre: 'Mark', calorias: 'Otto', proteina: '@mdo', grasa: 'Otto', hc: '@mdo', fibra: '@mdo', extra: '@mdo',},
            { nombre: 'Mark', calorias: 'Otto', proteina: '@mdo', grasa: 'Otto', hc: '@mdo', fibra: '@mdo', extra: '@mdo',},
            { nombre: 'Mark', calorias: 'Otto', proteina: '@mdo', grasa: 'Otto', hc: '@mdo', fibra: '@mdo', extra: '@mdo',},
            { nombre: 'Mark', calorias: 'Otto', proteina: '@mdo', grasa: 'Otto', hc: '@mdo', fibra: '@mdo', extra: '@mdo',}
          ],
          datos: [],
          datosCategorias: []
        };
    },
    components: {
      MenuFoodBalance,
      HeaderFoodBalance
    },
    methods:{

      async fetchData(){

        try {
          // el fetch que hace la llamada al back para recoger los datos, usando el metodo 'GET' y el modo 'cors'
          const response = await fetch('http://localhost/MikelUrle/foodbalanceback/public/api/Comida', {
              method: 'GET',
              mode: 'cors'
          });
        
          const data = await response.json();
          console.log(data);
        
          this.datos = [];

          for (let i = 0; i < data.length; i++) {
              this.datos.push({
                  "id": data[i].id,
                  "Nombre": data[i].Nombre,
                  "Calorias": data[i].Calorias,
                  "Proteina": data[i].Proteina,
                  "Grasa": data[i].Grasa,
                  "CH": data[i].CH,
                  "Fibra": data[i].Fibra,
                  "Extra": data[i].Extra,
                  "Categoria": data[i].Categoria
              });
          }
          console.log(this.datos);
        } catch (error) {
          console.error('Error al obtener los datos:', error);
        }
      },


      async sacarCategorias(){

        try {

          const response = await fetch('http://localhost/MikelUrle/foodbalanceback/public/api/CategoriasComida', {
              method: 'GET',
              mode: 'cors'
          });

          const data = await response.json();
          console.log(data);

          this.datosCategorias = [];

          for (let i = 0; i < data.length; i++) {
              this.datosCategorias.push(data[i]);
          }


        } catch (error) {
          console.error('Error al obtener los datos:', error);
        }
        },

    },
    mounted: function() {
      this.fetchData();
      this.sacarCategorias();
    }};
</script>

<template>
  <MenuFoodBalance />
  <HeaderFoodBalance />

  <div id="medioComida">
    <div class="container text-center">
      <div class="row align-items-start">

        <!-- Primera columna -->
        <div class="col">

          <div class="dropdown" id="dropdownCategoriaComida">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              Selecciona Categoria
            </button>
            <ul class="dropdown-menu">
              <li v-for="item in datosCategorias" :key="item">
                <a class="dropdown-item" href="#">{{ item }}</a>
              </li>
            </ul>
          </div>

          <div class="table-responsive" id="tablaComida">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Nombre</th>
                  <th scope="col">Calorias</th>
                  <th scope="col">Proteina</th>
                  <th scope="col">Grasa</th>
                  <th scope="col">C.H.</th>
                  <th scope="col">Fibra</th>
                  <th scope="col">Extra</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(dato, index) in datos" :key="index">
                  <th scope="row">{{ index + 1 }}</th>
                  <td>{{ dato.Nombre }}</td>
                  <td>{{ dato.Calorias }}</td>
                  <td>{{ dato.Proteina }}</td>
                  <td>{{ dato.Grasa }}</td>
                  <td>{{ dato.CH }}</td>
                  <td>{{ dato.Fibra }}</td>
                  <td>{{ dato.Extra }}</td>
                </tr>
              </tbody>
            </table>
          </div>

        </div>

        <!-- Segunda columna -->
        <div class="col">
          <div style="display: flex;">
            <p class="text-start" style="margin-top: 5.5%;">Nombre</p>
            <div class="form-floating" style="margin-left: 3%;">
              <textarea class="form-control" id="floatingTextarea" style="margin-top: 6.5%;"></textarea>
            </div>

            <p class="text-start" style="margin-left: 3%; margin-top: 5.5%;">Categoria</p>
            <div class="form-floating" style="margin-left: 3%;">
              <textarea class="form-control" id="floatingTextarea" style="margin-top: 6.5%;"></textarea>
            </div>
          </div>

          <div class="container text-center">
            <div class="row align-items-start">
              <div class="col">
                <p class="text-start" style="margin-top: 5%;">Calorias</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea"></textarea>
          </div>

          <p class="text-start">Proteina</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea"></textarea>
          </div>

          <p class="text-start">Grasa</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea"></textarea>
          </div>

          <p class="text-start">H.C.</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea"></textarea>
          </div>

          <p class="text-start">Fibra</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea"></textarea>
          </div>

          <p class="text-start">Extra</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea"></textarea>
          </div>
              </div>
              <div class="col">
                <button type="button" class="btn btn" id="botonAñadirComida">Añadir</button>
              </div>
            </div>
          </div>



        </div>
      </div>
    </div>
  </div>


</template>