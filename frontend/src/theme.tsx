import tw from 'twin.macro';

export const theme = {
  colors: {
    primary: {
      default: tw`text-indigo-600`,
      inverse: tw`text-white`,
    },
    secondary: {
      default: tw`text-indigo-100`,
      inverse: tw`text-indigo-700`,
    },
    white: {
      default: tw`text-white`,
      inverse: tw`text-gray-700`,
    },
    gray: {
      default: tw`text-gray-400`,
      inverse: tw``,
    },
    alert: {
      default: tw`text-red-500`,
      inverse: tw``,
    },
  },
};

export type ThemeColors = keyof typeof theme.colors;

export type ThemeColorCharacters = keyof typeof theme.colors.primary;
