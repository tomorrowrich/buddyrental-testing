module.exports = {
  default: {
    require: ["src/test/typescript/**/*.ts"], // ชี้ไปที่ Step Definitions
    requireModule: ["ts-node/register"],
    format: ["progress-bar"],
    paths: ["src/test/resources/buddyrental/*.feature"], // ชี้ไปที่ไฟล์ Feature
    parallel: 1,
    publishQuiet: true,
  },
};
