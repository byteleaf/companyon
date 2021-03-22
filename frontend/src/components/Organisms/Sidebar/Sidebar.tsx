import React from 'react';
import { useTranslation } from 'react-i18next';
import NavLink from '../../../lib/components/Molecules/NavLink/NavLink';
import ProfileLink from '../../../lib/components/Molecules/ProfileLink/ProfileLink';
import { useUser } from '../../../hooks/useUser';

interface SidebarProps {
  menuActive: boolean;
  setMenuActive: (menuActive: boolean) => void;
}

const Sidebar: React.FC<SidebarProps> = ({ menuActive, setMenuActive }) => {
  const { user, loading, isAdmin } = useUser();
  const { t } = useTranslation();

  return (
    <>
      {/* Off-canvas menu for mobile */}
      <div className={`md:hidden ${!menuActive ? 'hidden' : ''}`.trim()}>
        <div className="fixed inset-0 flex z-40">
          {/*
        Off-canvas menu overlay, show/hide based on off-canvas menu state.

        Entering: "transition-opacity ease-linear duration-300"
          From: "opacity-0"
          To: "opacity-100"
        Leaving: "transition-opacity ease-linear duration-300"
          From: "opacity-100"
          To: "opacity-0"
      */}
          <div className="fixed inset-0">
            <div className="absolute inset-0 bg-gray-600 opacity-75" />
          </div>
          {/*
        Off-canvas menu, show/hide based on off-canvas menu state.

        Entering: "transition ease-in-out duration-300 transform"
          From: "-translate-x-full"
          To: "translate-x-0"
        Leaving: "transition ease-in-out duration-300 transform"
          From: "translate-x-0"
          To: "-translate-x-full"
      */}
          <div className="relative flex-1 flex flex-col max-w-xs w-full bg-gray-800">
            <div className="absolute top-0 right-0 -mr-14 p-1">
              <button
                className="flex items-center justify-center h-12 w-12 rounded-full focus:outline-none focus:bg-gray-600"
                aria-label="Close sidebar"
                onClick={() => setMenuActive(false)}
              >
                <svg className="h-6 w-6 text-white" stroke="currentColor" fill="none" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
            <div className="flex-1 h-0 pt-5 pb-4 overflow-y-auto">
              <div className="flex-shrink-0 flex items-center px-4">
                <img className="h-12 w-auto" src="/img/byteleaf_logo.svg" alt="byteleaf logo" />
              </div>
              <nav className="mt-5 px-2">
                <NavLink href="/dashboard" icon="Home">
                  {t('components.organisms.sidebar.dashboard')}
                </NavLink>
                <NavLink href="/log-work" icon="Clock">
                  {t('components.organisms.sidebar.log-work')}
                </NavLink>
                <NavLink href="/companies" icon="OfficeBuilding">
                  {t('components.organisms.sidebar.companies')}
                </NavLink>
                <NavLink href="/projects" icon="Folder">
                  {t('components.organisms.sidebar.projects')}
                </NavLink>
                {isAdmin && (
                  <NavLink href="/users" icon="UserGroup">
                    {t('components.organisms.sidebar.team')}
                  </NavLink>
                )}
              </nav>
            </div>
            <ProfileLink loading={loading} currentUser={user} />
          </div>
          <div className="flex-shrink-0 w-14">{/* Force sidebar to shrink to fit close icon */}</div>
        </div>
      </div>

      {/* Static sidebar for desktop */}
      <div className="hidden md:flex md:flex-shrink-0">
        <div className="flex flex-col w-64 bg-gray-800">
          <div className="h-0 flex-1 flex flex-col pt-5 pb-4 overflow-y-auto">
            <div className="flex items-center flex-shrink-0 px-4">
              <img className="h-12 w-auto" src="/img/byteleaf_logo.svg" alt="byteleaf logo" />
            </div>
            {/* Sidebar component, swap this element with another sidebar if you like */}
            <nav className="mt-5 flex-1 px-2 bg-gray-800">
              <NavLink href="/dashboard" icon="Home">
                {t('components.organisms.sidebar.dashboard')}
              </NavLink>
              <NavLink href="/log-work" icon="Clock">
                {t('components.organisms.sidebar.log-work')}
              </NavLink>
              <NavLink href="/companies" icon="OfficeBuilding">
                {t('components.organisms.sidebar.companies')}
              </NavLink>
              <NavLink href="/projects" icon="Folder">
                {t('components.organisms.sidebar.projects')}
              </NavLink>
              {isAdmin && (
                <NavLink href="/users" icon="UserGroup">
                  {t('components.organisms.sidebar.team')}
                </NavLink>
              )}
            </nav>
          </div>
          <ProfileLink loading={loading} currentUser={user} />
        </div>
      </div>
    </>
  );
};

export default Sidebar;
