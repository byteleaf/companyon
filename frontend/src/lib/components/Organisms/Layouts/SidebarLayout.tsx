import React, { useState } from 'react';
import Sidebar from '../../../../components/Organisms/Sidebar/Sidebar';
import PageContentPlaceholder from '../../Atoms/PageContentPlaceholder/PageContentPlaceholder';

interface SidebarLayoutProps {
  title?: string;
  headerButton?: JSX.Element | null;
}

const SidebarLayout: React.FC<SidebarLayoutProps> = ({ children, title, headerButton }) => {
  const [menuActive, setMenuActive] = useState<boolean>(false);

  return (
    <div className="h-screen flex overflow-hidden bg-gray-100">
      <Sidebar menuActive={menuActive} setMenuActive={setMenuActive} />
      <div className="flex flex-col w-0 flex-1 overflow-hidden">
        <div className="md:hidden pl-1 pt-1 sm:pl-3 sm:pt-3">
          <button
            className="-ml-0.5 -mt-0.5 h-12 w-12 inline-flex items-center justify-center rounded-md text-gray-500 hover:text-gray-900 focus:outline-none focus:bg-gray-200 transition ease-in-out duration-150"
            aria-label="Open sidebar"
            onClick={() => setMenuActive(true)}
          >
            <svg className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
        </div>
        <main className="flex-1 relative z-0 overflow-y-auto pt-2 pb-6 focus:outline-none md:py-6">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 mb-6 w-full flex justify-between items-center">
            <h1 className="text-2xl font-semibold text-gray-900">{title}</h1>
            {headerButton && headerButton}
          </div>
          <div className="max-w-7xl mx-auto px-4 sm:px-6 md:px-8">{children ?? <PageContentPlaceholder />}</div>
        </main>
      </div>
    </div>
  );
};

export default SidebarLayout;
