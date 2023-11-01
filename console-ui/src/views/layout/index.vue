<template>
  <div>
    111
  </div>
</template>

<script>
import {getInstances} from "@/api/instance/instance";
export default {
  name: "index",
  components: {

  },
  data() {
    return {
      list: []
    }
  },
  created() {
    this.getInstance()
  },
  methods: {
    getInstance () {
      getInstances().then((res) => {
        if (res.code === 200) {
          this.list = res.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
