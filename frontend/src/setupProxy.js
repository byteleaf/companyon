/* eslint-disable func-names */
/* eslint-disable @typescript-eslint/no-var-requires */

const { createProxyMiddleware } = require('http-proxy-middleware');

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
module.exports = function (app) {
  app.use(
    createProxyMiddleware('/graphql', {
      target: 'http://localhost:8080',

      changeOrigin: true,
    }),
  );

  app.use(
    createProxyMiddleware('/subscription', {
      target: 'http://localhost:8080',

      changeOrigin: true,
      ws: true,
    }),
  );
};
