import { addMinutes, format } from 'date-fns';

export const getTimeString = (date: string, duration: number, breakAmount: number) => {
  const startingDate = new Date(date);
  const finalDate = addMinutes(startingDate, duration + breakAmount);

  const start = format(startingDate, 'HH:mm');
  const end = format(finalDate, 'HH:mm');

  return `${start} - ${end}`;
};

export const minutesToHours = (minutes: number) => {
  const hours = Math.floor(minutes / 60);
  const finalMinutes = minutes % 60;

  if (finalMinutes) {
    return `${hours}h ${finalMinutes}m`;
  }

  return `${hours}h`;
};
