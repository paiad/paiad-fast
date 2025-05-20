<template>
  <div class="container" @click="goHome">
    <div class="top">
      <div class="square">
        <div class="square">
          <div class="square">
            <div class="square">
              <div class="square"><div class="square"></div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="bottom">
      <div class="square">
        <div class="square">
          <div class="square">
            <div class="square">
              <div class="square"><div class="square"></div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="left">
      <div class="square">
        <div class="square">
          <div class="square">
            <div class="square">
              <div class="square"><div class="square"></div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="square">
        <div class="square">
          <div class="square">
            <div class="square">
              <div class="square"><div class="square"></div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FullScreenLoader',
  methods: {
    goHome() {
      this.$router.push('/');
    }
  }
}
</script>

<style scoped>
.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
}

.square {
  width: 8px;
  height: 30px;
  background: rgb(71, 195, 248);
  border-radius: 10px;
  display: block;
  animation: turn 2.5s ease infinite;
  box-shadow: rgb(71, 195, 248) 0px 1px 15px 0px;
}

.top {
  position: absolute;
  transform: rotate(90deg);
}

.bottom {
  position: absolute;
  transform: rotate(-90deg);
}

.left {
  position: absolute;
}

.right {
  position: absolute;
  transform: rotate(-180deg);
}

@keyframes turn {
  0% {
    transform: translateX(0) translateY(0) rotate(0);
  }

  70% {
    transform: translateX(400%) translateY(100%) rotate(90deg);
  }

  100% {
    transform: translateX(0) translateY(0) rotate(0);
  }
}
</style>
