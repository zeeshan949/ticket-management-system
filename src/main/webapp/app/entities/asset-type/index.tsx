import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AssetType from './asset-type';
import AssetTypeDetail from './asset-type-detail';
import AssetTypeUpdate from './asset-type-update';
import AssetTypeDeleteDialog from './asset-type-delete-dialog';

const AssetTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AssetType />} />
    <Route path="new" element={<AssetTypeUpdate />} />
    <Route path=":id">
      <Route index element={<AssetTypeDetail />} />
      <Route path="edit" element={<AssetTypeUpdate />} />
      <Route path="delete" element={<AssetTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AssetTypeRoutes;
