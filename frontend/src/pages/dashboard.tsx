import React from 'react';
import { useTranslation } from 'react-i18next';
import SidebarLayout from '../lib/components/Organisms/Layouts/SidebarLayout';

const Dashboard: React.FC = () => {
  const { t } = useTranslation();

  return <SidebarLayout title={t('pages.dashboard.title')} />;
};

export default Dashboard;
