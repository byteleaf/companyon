import '../src/styles.css';

import { withI18nextProvider } from './i18n';

export const backgrounds = {
  default: 'light',
  values: [
    { name: 'light', value: '#fff' },
    { name: 'dark', value: '#252f3f' },
  ],
};

export const globalTypes = {
  language: {
    name: 'Language',
    description: 'Global language for components',
    defaultValue: 'en',
    toolbar: {
      icon: 'globe',
      items: [
        { title: 'English', value: 'en', left: '🇺🇸' },
        { title: 'Deutsch', value: 'de', left: '🇩🇪' },
      ],
    },
  },
};

export const decorators = [withI18nextProvider];
