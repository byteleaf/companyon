import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import I18NextHttpBackend from 'i18next-http-backend';
import I18nextBrowserLanguageDetector from 'i18next-browser-languagedetector';

i18n
  .use(I18NextHttpBackend)
  .use(I18nextBrowserLanguageDetector)
  .use(initReactI18next)
  .init({
    fallbackLng: 'en',

    ns: ['translation'],
    defaultNS: 'translation',

    backend: {
      loadPath: '/locales/{{ns}}-{{lng}}.json',
    },

    interpolation: {
      escapeValue: false,
    },
  });
