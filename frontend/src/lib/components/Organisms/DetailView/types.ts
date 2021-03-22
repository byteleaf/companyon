import { KeyValuePair } from '../../../../types';

export type DetailViewHeader = {
  title: string;
  subtitle?: string;
  image?: JSX.Element;
  actions?: JSX.Element[];
};

export type DetailViewConfig = {
  header: DetailViewHeader;
  rows: KeyValuePair<string | JSX.Element>[];
};
