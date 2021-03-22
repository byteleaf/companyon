// eslint-disable-next-line @typescript-eslint/no-var-requires
const { join } = require('path');

module.exports = {
  locales: ['en', 'de'],
  output: join(__dirname, 'public/locales/$NAMESPACE-$LOCALE.json'),
  keepRemoved: true,
};
