/* eslint-disable no-bitwise */

export const sanitizeHex = (originalHex: string) => {
  const stripNonHexChars = (inputHex: string) => String(inputHex.toLowerCase()).replace(/[^0-9a-f]/gi, '');

  let hex = stripNonHexChars(originalHex);
  if (hex.length < 6) {
    hex = hex[0] + hex[0] + hex[1] + hex[1] + hex[2] + hex[2];
  }

  if (stripNonHexChars(hex).length !== 6) {
    throw new Error('No valid hex color provided.');
  }

  return `#${hex}`;
};

export const modifyColorLightness = (originalHex: string, originalLum: number) => {
  const hex = sanitizeHex(originalHex).slice(1);
  const lum = originalLum || 0;

  let rgb = '#';
  let c;
  let i;

  for (i = 0; i < 3; i += 1) {
    c = parseInt(hex.substr(i * 2, 2), 16);
    c = Math.round(Math.min(Math.max(0, c + c * lum), 255)).toString(16);
    rgb += `00${c}`.substr(c.length);
  }

  return rgb;
};

export const isLightColor = (originalHex: string) => {
  const hex = sanitizeHex(originalHex).slice(1);

  // If hex --> Convert it to RGB: http://gist.github.com/983661
  const color = +`0x${hex.replace(hex.length < 5 ? /./g : '', '$&$&')}`;

  const r = color >> 16;
  const g = (color >> 8) & 255;
  const b = color & 255;

  // HSP (Highly Sensitive Perceived brightness) equation from http://alienryderflex.com/hsp.html
  const hsp = Math.sqrt(0.299 * (r * r) + 0.587 * (g * g) + 0.114 * (b * b));

  // Using the HSP value, determine whether the color is light or dark
  if (hsp > 127.5) {
    return true;
  }

  return false;
};
