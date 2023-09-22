import React from 'react';
import NewNavbar from './NewNavbar'
import { Outlet } from 'react-router';

export default () => {
  return (
    <>
      <NewNavbar />
      <Outlet />
    </>
  );
};