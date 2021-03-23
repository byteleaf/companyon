import initStoryshots from '@storybook/addon-storyshots';
import { styleSheetSerializer } from 'jest-styled-components';
import { addSerializer } from 'jest-specific-snapshot';

import removePropsSerializer from './helpers/test/removePropsSerializer';
import asyncStorybookTestWithSnapshot from './helpers/test/asyncStorybookTestWithSnapshot';

// since we use a separate snapshot file for each story, we have to inject the serialize by ourselves
addSerializer(styleSheetSerializer);

// @react/combobox adds props that might be different every time the test is run
addSerializer(removePropsSerializer(['aria-controls']));

initStoryshots({
  integrityOptions: { cwd: __dirname },
  asyncJest: true,
  test: asyncStorybookTestWithSnapshot(),
});
