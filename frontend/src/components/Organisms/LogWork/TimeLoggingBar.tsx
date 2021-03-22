import React, { FC, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { differenceInMinutes } from 'date-fns';
import styled from 'styled-components';
import tw from 'twin.macro';
import TextInput from '../../../lib/components/Molecules/Input/TextInput/TextInput';
import Button, { ButtonTypes } from '../../../lib/components/Atoms/Button/Button';
import Icon from '../../../lib/components/Atoms/Icons/Icon';
import Dropdown from '../../../lib/components/Molecules/Input/Dropdown/Dropdown';
import DateTimePicker from '../../../lib/components/Molecules/Input/DateTimePicker/DateTimePicker';
import { useProjectsQuery } from '../../../graphql/queries/__generated__/projects';
import { GqlProject } from '../../../graphql/types';
import { useUser } from '../../../hooks/useUser';
import { useCreateTimeLogMutation } from '../../../graphql/mutations/__generated__/createTimeLog';
import { minutesToHours } from '../../../helpers/dates';

const Label = styled.label`
  ${tw`block text-sm font-medium leading-5 text-gray-700`}
`;

type TimeLoggingBarProps = {
  callback: () => void;
};

const TimeLoggingBar: FC<TimeLoggingBarProps> = ({ callback }) => {
  const { t } = useTranslation();

  const { user } = useUser();
  const { data: projectData, loading: projectLoading } = useProjectsQuery();

  const [create, { loading }] = useCreateTimeLogMutation({
    onError: (error) => console.error(error),
    onCompleted: callback,
  });

  const projects = projectData?.projects?.map((p) => ({ key: p.id, value: `${p.name} (${p.state})` })) || [];

  const [what, setWhat] = useState('');
  const [where, setWhere] = useState<GqlProject | null>();
  const [from, setFrom] = useState<Date | null>(null);
  const [to, setTo] = useState<Date | null>(null);
  const [breakTime, setBreakTime] = useState<number>(1);

  const disabled = !user || !what || !where || !from || !to || loading || projectLoading;

  const breakInMinutes = (breakTime || 0) * 60;

  const getWorkDuration = () => {
    if (!from || !to) return 0;

    return differenceInMinutes(to, from) - breakInMinutes;
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!user || !what || !where || !from || !to) return;

    const durationInMinutes = getWorkDuration();

    if (durationInMinutes < 0) {
      console.warn('Ending time has to be after starting time.');
      return;
    }

    create({
      variables: {
        input: {
          user: user.id,
          project: where.id,
          start: from.toISOString(),
          durationInMinutes,
          breakInMinutes,
          description: what,
        },
      },
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="bg-white shadow overflow-hidden sm:rounded-md mb-8">
        <div className="px-4 py-4">
          <TextInput id="log-what" label={t('components.organisms.logWork.what')} value={what} onChange={setWhat} />
          <div className="py-4">
            <Dropdown
              id="log-where"
              label={t('components.organisms.logWork.where')}
              value={where?.id || ''}
              items={projects}
              onChange={(id) => {
                const selected = projectData?.projects.find((p) => p.id === id);
                if (!selected) return;
                setWhere(selected);
              }}
            />
          </div>
          <div className="flex w-full pb-4 justify-between">
            <DateTimePicker
              id="log-from"
              className="w-full"
              label={t('components.organisms.logWork.from')}
              value={from}
              onChange={setFrom}
            />
            <DateTimePicker
              id="log-to"
              className="w-full px-4"
              label={t('components.organisms.logWork.to')}
              value={to}
              onChange={setTo}
            />
            <TextInput
              id="log-break"
              className="w-full"
              label={t('components.organisms.logWork.break')}
              value={`${breakTime}`}
              type="number"
              onChange={(v) => setBreakTime(Number(v))}
            />
          </div>
          <div className="w-full flex justify-between items-center">
            <Label>{`Work time: ${minutesToHours(getWorkDuration())}`}</Label>
            <Label>{`Full time: ${minutesToHours(getWorkDuration() + breakInMinutes)}`}</Label>
            <Button type={ButtonTypes.Submit} disabled={disabled}>
              <Icon icon="Check" color="primary" colorCharacter="inverse" />
            </Button>
          </div>
        </div>
      </div>
    </form>
  );
};

export default TimeLoggingBar;
