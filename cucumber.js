module.exports = {
  default: {
    require: ["src/test/typescript/**/*.ts"],
    requireModule: ["ts-node/register"],
    format: ["progress-bar"],
    paths: ["src/test/resources/buddyrental/*.feature"],
    parallel: 3,
  },
};
