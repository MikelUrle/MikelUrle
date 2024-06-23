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
          comida: [],
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
          this.comida = [];

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

          this.comida = this.datos;

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

        filtroCategorias(datoCat){
          this.comida = [];
          for (let index = 0; index < this.datos.length; index++) {
            if (this.datos[index].Categoria == datoCat) {
              this.comida.push(this.datos[index]);
            }
          }

        },

        validarNumeros(event) {
          const input = event.target.value;
          // Filtrar solo números (0-9) y eliminar todo lo demás
          const numeros = input.replace(/[^0-9]/g, '');
          event.target.value = numeros;
        },

        async añadirComida(){
          if (this.nombre==undefined || this.calorias==undefined || this.proteina==undefined || this.grasa==undefined || this.ch==undefined || this.fibra==undefined || this.extra==undefined || this.categoria==undefined) {
            alert("datuak falta dira");
          } else {
            var js = JSON.stringify({"Nombre": this.nombre, "Calorias": this.calorias, "Proteina": this.proteina, "Grasa": this.grasa, "CH": this.ch, "Fibra": this.fibra, "Extra": this.extra, "Categoria": this.categoria}); 
            fetch('http://localhost/MikelUrle/foodbalanceback/public/api/MeterComida', {
                method: 'POST',
                body: js,
                mode: 'cors'
            })
            .then(function (response) {
                    return response.text();
            })
            .then(data=>{
                console.log(data);
            })
            .catch(error => {
                // por si hay algun error por los constraints
                console.log("Erregistro hau beste taula batean erabiltzen ari da, beraz, ezin da ezabatu" + error);
            });
          }
          window.location.reload();
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
                <a class="dropdown-item" href="#" @click="this.filtroCategorias(item)">{{ item }}</a>
              </li>
            </ul>
          </div>

          <div class="table-responsive" id="tablaComida">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">/100gr</th>
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
                <tr v-for="(dato, index) in comida" :key="index">
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
              <textarea class="form-control" id="floatingTextarea" style="margin-top: 6.5%;" v-model="nombre"></textarea>
            </div>

            <p class="text-start" style="margin-left: 3%; margin-top: 5.5%;">Categoria</p>
            <div class="form-floating" style="margin-left: 3%;">
              <textarea class="form-control" id="floatingTextarea" style="margin-top: 6.5%;" v-model="categoria"></textarea>
            </div>
          </div>

          <div class="container text-center">
            <div class="row align-items-start">
              <div class="col">
                <p class="text-start" style="margin-top: 5%;">Calorias</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea" @input="validarNumeros" v-model="calorias"></textarea>
          </div>

          <p class="text-start">Proteina</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea" @input="validarNumeros" v-model="proteina"></textarea>
          </div>

          <p class="text-start">Grasa</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea" @input="validarNumeros" v-model="grasa"></textarea>
          </div>

          <p class="text-start">H.C.</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea" @input="validarNumeros" v-model="ch"></textarea>
          </div>

          <p class="text-start">Fibra</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea" @input="validarNumeros" v-model="fibra"></textarea>
          </div>

          <p class="text-start">Extra</p>
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea" v-model="extra"></textarea>
          </div>
              </div>
              <div class="col">
                <button type="button" class="btn btn" id="botonAñadirComida" @click="añadirComida">Añadir</button>
              </div>
            </div>
          </div>



        </div>
      </div>
    </div>
  </div>


</template>