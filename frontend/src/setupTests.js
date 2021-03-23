import '@testing-library/jest-dom';

import i18next from 'i18next';
import { initReactI18next } from 'react-i18next';

import enTranslations from '../public/locales/translation-en.json';

i18next.use(initReactI18next).init({
  lng: 'en',
  fallbackLng: 'en',

  ns: ['translation'],
  defaultNS: 'translation',

  interpolation: {
    escapeValue: false,
  },

  resources: { en: { translation: enTranslations } },
});
