export default (keys: string[]) => {
  return {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    test(val: any) {
      return (
        val &&
        typeof val === 'object' &&
        Object.prototype.hasOwnProperty.call(val, 'props') &&
        Object.keys(val.props).some((prop) => keys.some((key) => key === prop))
      );
    },
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    print(val: any, serialize: (val: any) => unknown) {
      keys.forEach((key) => {
        // eslint-disable-next-line no-param-reassign
        delete val.props[key];
      });

      return serialize(val);
    },
  };
};
