export type TagType = {
  id: string;
  value: string;
  hexColor?: string;
  outline?: boolean;
};

export type TimeLoggingEntryType = {
  id: string;
  title: string;
  time: string;
  date: string;
  project: string;
  company: string;
  tags: TagType[];
};
