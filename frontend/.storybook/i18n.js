import React, { Suspense, useEffect } from 'react';
import { Story, StoryContext } from '@storybook/react';
import { I18nextProvider, initReactI18next } from 'react-i18next';
import i18n from 'i18next';

import I18NextHttpBackend from 'i18next-http-backend';

i18n
  .use(I18NextHttpBackend)
  .use(initReactI18next)
  .init({
    fallbackLng: 'en',

    debug: true,

    ns: ['translation'],
    defaultNS: 'translation',

    backend: {
      loadPath: '/locales/{{ns}}-{{lng}}.json',
    },

    interpolation: {
      escapeValue: false,
    },
  });

export const withI18nextProvider =
  /**
   * @param {Story} Story
   * @param {StoryContext} context
   */
  (Story, context) => {
    useEffect(() => {
      if (i18n.language !== context.globals.language) {
        i18n.changeLanguage(context.globals.language);
      }
    }, [context.globals.language]);

    return (
      <Suspense fallback="loading">
        <I18nextProvider i18n={i18n}>
          <Story {...context} />
        </I18nextProvider>
      </Suspense>
    );
  };
