<script>
import MenuFoodBalance from "../components/MenuFoodBalance.vue";
import HeaderFoodBalance from "../components/HeaderFoodBalance.vue";

export default {
    data() {
        return {
          eleccion: '',
          cantidadG: '',
          textareaContent: '',
          datos: [],
          comida: [],
          datosCategorias: [],
          datoCalculo: [],
          calculo: [],
          total: [],
        };
    },
    components: {
      MenuFoodBalance,
      HeaderFoodBalance
    },
    methods:{
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

      filtroCategorias(){
        this.comida = [];
        for (let index = 0; index < this.datos.length; index++) {
          if (this.datos[index].Categoria == this.selectedOption) {
            this.comida.push(this.datos[index]);
          }
        }
        console.log(this.comida);
      },

      cambioEleccion(texto){
        this.eleccion = texto;
      },

      calculoDeEleccion(dato, cantidad){
        console.log(this.datos);
        console.log(dato);
        console.log(cantidad);

        var multiplicador;
        multiplicador = cantidad/100;
        console.log(multiplicador);


        for (let index = 0; index < this.datos.length; index++) {
          if (this.datos[index].Nombre==dato) {
            this.datoCalculo = this.datos[index];
          }
        }

        this.datoCalculo.CH = Math.ceil(this.datoCalculo.CH * multiplicador);
        this.datoCalculo.Calorias = Math.ceil(this.datoCalculo.Calorias * multiplicador);
        this.datoCalculo.Fibra = Math.ceil(this.datoCalculo.Fibra * multiplicador);
        this.datoCalculo.Grasa = Math.ceil(this.datoCalculo.Grasa * multiplicador);
        this.datoCalculo.Proteina = Math.ceil(this.datoCalculo.Proteina * multiplicador);

        this.calculo.push(this.datoCalculo);


        this.textareaContent = this.calculo.join('\n');

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
  <div id="medioHome">
    <select class="form-select" aria-label="Default select example" id="selectorHomeCategoria" v-model="selectedOption" @change="filtroCategorias">
      <option v-for="(categoria, index) in datosCategorias" :key="index" :value="categoria">
        {{ categoria }}
      </option>
    </select>

    <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2" id="eleccionHome" v-model="eleccion">

    <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2" id="gramosHome" v-model="cantidadG">
  
    <button type="button" class="btn btn" id="botonAñadirHome" @click="calculoDeEleccion(this.eleccion, cantidadG)">Añadir</button>

    <table class="table table-hover" id="tablaHome">
      <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Selecciona una Comida</th>
        </tr>
      </thead>
      <tbody>
        <!-- @click="" -->
        <tr v-for="(dato, index) in comida" :key="index" @click="cambioEleccion(dato.Nombre)">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ dato.Nombre }}</td>
        </tr>
      </tbody>
    </table>

    <div class="form-floating" id="textAreaHome">
      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" v-model="textareaContent" style="height: 300px" readonly></textarea>
    </div>

    <button type="button" class="btn btn" id="botonQuitarHome">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-file-minus" viewBox="0 0 16 16">
        <path d="M5.5 8a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1H6a.5.5 0 0 1-.5-.5"/>
        <path d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1"/>
      </svg>  
    </button>

    <button type="button" class="btn btn" id="botonQuitarTodoHome">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash3-fill" viewBox="0 0 16 16">
        <path d="M11 1.5v1h3.5a.5.5 0 0 1 0 1h-.538l-.853 10.66A2 2 0 0 1 11.115 16h-6.23a2 2 0 0 1-1.994-1.84L2.038 3.5H1.5a.5.5 0 0 1 0-1H5v-1A1.5 1.5 0 0 1 6.5 0h3A1.5 1.5 0 0 1 11 1.5m-5 0v1h4v-1a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5M4.5 5.029l.5 8.5a.5.5 0 1 0 .998-.06l-.5-8.5a.5.5 0 1 0-.998.06m6.53-.528a.5.5 0 0 0-.528.47l-.5 8.5a.5.5 0 0 0 .998.058l.5-8.5a.5.5 0 0 0-.47-.528M8 4.5a.5.5 0 0 0-.5.5v8.5a.5.5 0 0 0 1 0V5a.5.5 0 0 0-.5-.5"/>
      </svg>
    </button>

    <div class="form-floating" id="textTotalHome">
      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 70px" readonly></textarea>
    </div>

  </div>

  

</template>