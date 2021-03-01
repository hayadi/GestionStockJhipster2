import { IParamWilaya } from 'app/shared/model/param-wilaya.model';

export interface IParamCommune {
  id?: number;
  code?: string;
  libelle?: string;
  wilaya?: IParamWilaya;
}

export class ParamCommune implements IParamCommune {
  constructor(public id?: number, public code?: string, public libelle?: string, public wilaya?: IParamWilaya) {}
}
