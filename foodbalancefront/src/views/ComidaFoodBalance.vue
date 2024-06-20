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
          ]
        };
    },
    components: {
      MenuFoodBalance,
      HeaderFoodBalance
    },
    methods:{

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
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
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
                  <th scope="col">H.C.</th>
                  <th scope="col">Fibra</th>
                  <th scope="col">Extra</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(person, index) in people" :key="index">
                  <th scope="row">{{ index + 1 }}</th>
                  <td>{{ person.nombre }}</td>
                  <td>{{ person.calorias }}</td>
                  <td>{{ person.proteina }}</td>
                  <td>{{ person.grasa }}</td>
                  <td>{{ person.hc }}</td>
                  <td>{{ person.fibra }}</td>
                  <td>{{ person.extra }}</td>
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