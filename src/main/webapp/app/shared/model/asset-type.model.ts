export interface IAssetType {
  id?: number;
  name?: string;
  description?: string | null;
  isActive?: boolean;
}

export const defaultValue: Readonly<IAssetType> = {
  isActive: false,
};
