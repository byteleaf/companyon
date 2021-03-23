import React from 'react';
import { useParams } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
import SidebarLayout from '../../lib/components/Organisms/Layouts/SidebarLayout';
import DetailView from '../../lib/components/Organisms/DetailView/DetailView';
import { useProjectQuery } from '../../graphql/queries/__generated__/project';
import Link from '../../lib/components/Atoms/Link/Link';

const Loading = () => <span>Loading...</span>;

const Project = () => {
  const { t } = useTranslation();

  const { id } = useParams<{ id: string }>();
  const { loading, data } = useProjectQuery({
    variables: { id: id as string },
    fetchPolicy: 'network-only',
  });

  const renderContent = () => {
    if (loading) {
      return <Loading />;
    }
    if (data?.project) {
      const { company } = data.project;

      const detailViewConfig = {
        header: {
          title: data.project.name,
          subtitle: data.project.id,
        },
        rows: [
          {
            key: t('pages.projects.project.name'),
            value: data.project.name,
          },
          {
            key: t('pages.projects.project.id'),
            value: data.project.id,
          },
          {
            key: t('pages.projects.project.state'),
            value: data.project.state,
          },
          {
            key: t('pages.projects.project.companyName'),
            value: company ? <Link to={`/companies/${company.id}`}>{company.name}</Link> : '-',
          },
        ],
      };

      return <DetailView config={detailViewConfig} />;
    }
    return null;
  };

  return <SidebarLayout title={t('pages.projects.project.title')}>{renderContent()}</SidebarLayout>;
};

export default Project;
