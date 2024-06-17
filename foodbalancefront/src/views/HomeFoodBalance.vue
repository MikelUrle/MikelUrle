<script>
import MenuFoodBalance from "../components/MenuFoodBalance.vue";
import HeaderFoodBalance from "../components/HeaderFoodBalance.vue";

export default {
    data() {
        return {

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

  <div id="medioHome">
    <select class="form-select" aria-label="Default select example" id="selectorHomeCategoria">
      <option selected>Open this select menu</option>
      <option value="1">One</option>
      <option value="2">Two</option>
      <option value="3">Three</option>
    </select>

    <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
  </div>

</template>