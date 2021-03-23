import { modifyColorLightness, isLightColor, sanitizeHex } from './colorHelpers';

const color = '#1b5b79';

describe('Color Helpers', () => {
  describe('sanitizeHex', () => {
    it('should work with 3 char hex', () => {
      expect(sanitizeHex('#a5dadd')).toBe('#a5dadd');
    });

    it('should work with 3 char hex', () => {
      expect(sanitizeHex('#a5d')).toBe('#aa55dd');
    });

    it('should work without # and 3 chars', () => {
      expect(sanitizeHex('a5d')).toBe('#aa55dd');
    });

    it('should work without # and 6 chars', () => {
      expect(sanitizeHex('a5dae4')).toBe('#a5dae4');
    });

    it('should error out when no correct hex', () => {
      expect(() => {
        sanitizeHex('#ad');
      }).toThrowError();
    });
  });

  describe('modifyColorLightness', () => {
    it('should return same color if l=0', () => {
      expect(modifyColorLightness(color, 0)).toBe(color);
    });

    it('should return lighter color', () => {
      expect(modifyColorLightness(color, 0.5)).toBe('#2989b6');
    });

    it('should return darker color', () => {
      expect(modifyColorLightness(color, -0.5)).toBe('#0e2e3d');
    });
  });

  describe('isLightColor', () => {
    it('should return true on light color', () => {
      expect(isLightColor('#13a605')).toBe(true);
    });

    it('should return false on dark color', () => {
      expect(isLightColor(color)).toBe(false);
    });
  });
});
