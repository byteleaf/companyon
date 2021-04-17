// used in tests only - storybook doesn't provide good typings for extensions
/* eslint-disable import/no-extraneous-dependencies */
/* eslint-disable @typescript-eslint/no-explicit-any */

import { Stories2SnapsConverter } from '@storybook/addon-storyshots';

import { act } from 'react-test-renderer';

interface Args {
  story: any;
  context: any;
  renderTree: any;
  options?: any;
  done: () => void;
  stories2snapsConverter: Stories2SnapsConverter;
}

export default () => {
  return async (args: Args) => {
    const { story, context, done, renderTree, stories2snapsConverter } = args;
    // adapted multiSnapshotWithOptions to work with async data fetching
    const snapshotFilename = stories2snapsConverter.getSnapshotFileName(context);

    let result: any = null;

    await act(async () => {
      result = renderTree(story, context, {});
    });

    async function match(tree: any) {
      let target = tree;

      if (typeof tree.childAt === 'function') {
        target = tree.childAt(0);
      }
      if (Array.isArray(tree.children)) {
        [target] = tree.children;
      }

      if (snapshotFilename) {
        expect(target).toMatchSpecificSnapshot(snapshotFilename);
      }

      if (typeof tree.unmount === 'function') {
        tree.unmount();
      }

      done();
    }

    if (result && typeof result.then === 'function') {
      await result.then(match);
      return;
    }

    await match(result);
  };
};
