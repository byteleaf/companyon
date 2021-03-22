import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { TFunction } from 'i18next';
import styled from 'styled-components';
import tw from 'twin.macro';
import { GqlProjectsQuery, useProjectsQuery } from '../graphql/queries/__generated__/projects';
import SidebarLayout from '../lib/components/Organisms/Layouts/SidebarLayout';
import { TableConfig } from '../lib/components/Organisms/DataTable/types';
import DataTable from '../lib/components/Organisms/DataTable/DataTable';
import AddProjectBar from '../components/Organisms/AddProjectBar/AddProjectBar';
import {
  GqlProjectUpdateSubscription,
  ProjectUpdateDocument,
} from '../graphql/subscriptions/__generated__/projectUpdate';
import Button from '../lib/components/Atoms/Button/Button';
import Icon from '../lib/components/Atoms/Icons/Icon';

const MarginDataTable = styled(DataTable)`
  ${tw`mt-8`}
`;

const createEmptyTableConfig = (t: TFunction): TableConfig => ({
  rowActions: [
    {
      type: 'Link',
      src: 'projects',
      label: t('common.actions.view'),
    },
    {
      type: 'DeleteProject',
      src: 'projects',
      label: t('common.actions.delete'),
    },
  ],
  headers: [
    { key: 'name', value: t('pages.projects.header.name') as string },
    { key: 'company', value: t('pages.projects.header.company') as string },
  ],
});

const Loading = () => <span>Loading...</span>;

const Projects = () => {
  const { t } = useTranslation();

  const [willAdd, setWillAdd] = useState<boolean>(false);

  const [tableConfig] = React.useState<TableConfig>(createEmptyTableConfig(t));

  const { data, loading, subscribeToMore } = useProjectsQuery();

  useEffect(() => {
    subscribeToMore<GqlProjectUpdateSubscription>({
      document: ProjectUpdateDocument,
      updateQuery: (prev, { subscriptionData }): GqlProjectsQuery => {
        if (!subscriptionData?.data?.projectUpdate?.entity) return prev;

        const { type, entity } = subscriptionData.data.projectUpdate;

        const filteredProjects = prev.projects.filter((p) => p.id !== entity.id);

        switch (type) {
          case 'CREATED':
          case 'UPDATED':
            return { ...prev, projects: [...filteredProjects, entity] };
          case 'DELETED':
            return { ...prev, projects: filteredProjects };
          default:
            return prev;
        }
      },
    });
  }, [subscribeToMore]);

  const projectList = data?.projects || [];
  const tableData = {
    rows: projectList.map((project) => ({
      id: project.id,
      cells: [
        {
          key: 'name',
          value: project.name,
        },
        { key: 'company', value: project.company?.name || '-' },
      ],
    })),
  };

  return (
    <SidebarLayout
      title={t('pages.projects.title')}
      headerButton={
        <Button onClick={() => setWillAdd((p) => !p)}>
          <Icon icon={willAdd ? 'MinusCircle' : 'PlusCircle'} color="primary" colorCharacter="inverse" />
        </Button>
      }
    >
      {loading ? (
        <Loading />
      ) : (
        <>
          {willAdd && <AddProjectBar />}
          <MarginDataTable tableConfig={tableConfig} tableData={tableData} />
        </>
      )}
    </SidebarLayout>
  );
};

export default Projects;
