<template>
  <div>
    <vue-particles
      id="particles"
      :particlesInit="particlesInit"
      :options="data.options"
    />
  </div>
</template>

<script setup>
import { loadFull } from "tsparticles";
import { reactive } from "vue";

const data = reactive({
  options: {
    fpsLimit: 60,
    interactivity: {
      detectsOn: "canvas",
      events: {
        onHover: {
          enable: true,
          mode: "grab",
        },
        resize: true,
      },
      modes: {
        bubble: {
          distance: 400,
          duration: 2,
          opacity: 0.8,
          size: 40,
        },
        push: {
          quantity: 4,
        },
        grab: {
          distance: 200,
          duration: 0.4,
        },
        attract: {
          distance: 200,
          duration: 0.4,
          factor: 5,
        },
      },
    },
    particles: {
      // color: {
      //   value: "#FFFFFF",
      // },
      links: {
        color: "#C0C0C0",
        distance: 150,
        enable: true,
        opacity: 0.5,
        width: 1.2,
      },
      collisions: {
        enable: true,
      },
      move: {
        attract: { enable: false, rotateX: 600, rotateY: 1200 },
        bounce: false,
        direction: "none",
        enable: true,
        out_mode: "out",
        random: false,
        speed: 0.7,
        straight: false,
      },
      number: {
        density: {
          enable: true,
          value_area: 800,
        },
        value: 80,
      },
      opacity: {
        value: 0.7,
      },
      shape: {
        type: "star",
      },
      size: {
        random: true,
        value: 3,
      },
    },
    detectRetina: true,
  },
});

const particlesInit = async (engine) => {
  await loadFull(engine);
};
</script>

<style scoped></style>
