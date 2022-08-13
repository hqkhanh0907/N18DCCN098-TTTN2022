import {AccRole} from './AccRole';

export class LoginResponse {
  accId: number;
  username: string;
  authenticationToken: string;
  accountRoleDtos: AccRole[];

  constructor(accId: number, username: string, authenticationToken: string, accountRoleDTO: AccRole[]) {
    this.accId = accId;
    this.username = username;
    this.authenticationToken = authenticationToken;
    this.accountRoleDtos = accountRoleDTO;
  }
}
