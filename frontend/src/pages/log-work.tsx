import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { sortBy } from 'lodash';
import SidebarLayout from '../lib/components/Organisms/Layouts/SidebarLayout';
import TimeLoggingList from '../components/Organisms/LogWork/TimeLoggingList';
import TimeLoggingBar from '../components/Organisms/LogWork/TimeLoggingBar';
import Button from '../lib/components/Atoms/Button/Button';
import Icon from '../lib/components/Atoms/Icons/Icon';
import { GqlTimeLogsQuery, useTimeLogsQuery } from '../graphql/queries/__generated__/timelogs';
import { getTimeString } from '../helpers/dates';
import {
  GqlTimeLogUpdateSubscription,
  TimeLogUpdateDocument,
} from '../graphql/subscriptions/__generated__/timeLogUpdate';
import { useUser } from '../hooks/useUser';

const LogWork = () => {
  const { t } = useTranslation();
  const { user } = useUser();

  const { data, subscribeToMore } = useTimeLogsQuery({ skip: !user?.id, variables: { userId: user?.id } });

  useEffect(() => {
    subscribeToMore<GqlTimeLogUpdateSubscription>({
      document: TimeLogUpdateDocument,
      updateQuery: (prev, { subscriptionData }): GqlTimeLogsQuery => {
        if (!subscriptionData?.data?.timeLogUpdate?.entity) return prev;

        const { type, entity } = subscriptionData.data.timeLogUpdate;

        const filteredTimeLogs = prev.timeLogs.filter((c) => c.id !== entity.id);

        switch (type) {
          case 'CREATED':
          case 'UPDATED':
            return { ...prev, timeLogs: [...filteredTimeLogs, entity] };
          case 'DELETED':
            return { ...prev, timeLogs: filteredTimeLogs };
          default:
            return prev;
        }
      },
    });
  }, [subscribeToMore]);

  const [willAdd, setWillAdd] = useState<boolean>(false);

  const timeLogs =
    data?.timeLogs.map((timeLog) => ({
      id: timeLog.id,
      title: timeLog.description || '',
      time: getTimeString(timeLog.start, timeLog.durationInMinutes, timeLog.breakInMinutes || 0),
      date: timeLog.start,
      project: timeLog.project?.name || '',
      company: timeLog.project?.company?.name || '',
      tags: [],
    })) || [];

  const sorted = sortBy(timeLogs, 'start').reverse();

  return (
    <SidebarLayout
      title={t('pages.log-work.title')}
      headerButton={
        <Button onClick={() => setWillAdd((p) => !p)}>
          <Icon icon={willAdd ? 'MinusCircle' : 'PlusCircle'} color="primary" colorCharacter="inverse" />
        </Button>
      }
    >
      {willAdd && <TimeLoggingBar callback={() => setWillAdd(false)} />}
      <TimeLoggingList entries={sorted} />
    </SidebarLayout>
  );
};

export default LogWork;
