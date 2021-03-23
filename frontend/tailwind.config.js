/* eslint-disable @typescript-eslint/no-var-requires */
/* eslint-disable global-require */
const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
  theme: {
    extend: {
      fontFamily: {
        sans: ['Inter var', ...defaultTheme.fontFamily.sans],
      },
      height: {
        '9_5': '2.375rem',
      },
    },
    pseudo: {
      before: 'before',
      after: 'after',
      'not-first': 'not(:first-child)',
      'not-last': 'not(:last-child)',
    },
  },
  variants: {
    cursor: ['pointer'],
    borderStyle: ['responsive', 'hover', 'focus'],
    borderWidth: ['responsive', 'hover', 'focus'],
    fontSize: ['responsive', 'hover', 'focus'],
    padding: ['responsive', 'first', 'last', 'not-first', 'not-last'],
  },
  plugins: [
    require('@tailwindcss/ui')({
      layout: 'sidebar',
    }),
    require('tailwindcss-pseudo')({
      empty: true, // defaults to true
    }),
  ],
};
