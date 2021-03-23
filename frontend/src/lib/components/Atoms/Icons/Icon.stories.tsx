import React from 'react';
import Icon from './Icon';
import Icons from './components';

export default {
  component: Icon,
  title: 'Atoms/Icons',
};

export const List = () => {
  return (
    <div className="flex flex-wrap">
      {Object.keys(Icons).map((key) => (
        <div key={key} className="flex flex-col items-center justify-center m-2 w-36">
          <Icon icon={key as keyof typeof Icons} />
          <div>{key}</div>
        </div>
      ))}
    </div>
  );
};

export const DefaultColor = () => {
  return <Icon icon="Annotation" />;
};

export const Colored = () => {
  return <Icon icon="Annotation" color="primary" />;
};

export const MatchingInverse = () => {
  return (
    <>
      <div className="bg-indigo-100 p-2">
        <Icon icon="Annotation" colorCharacter="inverse" />
      </div>
      <div className="bg-indigo-600 p-2">
        <Icon icon="Annotation" color="primary" colorCharacter="inverse" />
      </div>
    </>
  );
};
