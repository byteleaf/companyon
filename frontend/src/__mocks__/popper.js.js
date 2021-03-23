// eslint-disable-next-line import/no-extraneous-dependencies
import PopperJs from 'popper.js';

export default class Popper {
  static placements = PopperJs.placements;

  constructor() {
    return {
      destroy: () => {
        // just a mock
      },
      scheduleUpdate: () => {
        // just a mock
      },
    };
  }
}
