import dayjs from 'dayjs';

export interface IAssetType {
  id?: number;
  name?: string;
  description?: string | null;
  isActive?: boolean;
  createdBy?: string;
  createdDate?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
}

export const defaultValue: Readonly<IAssetType> = {
  isActive: false,
};
