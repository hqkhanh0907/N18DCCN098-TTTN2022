import {AccRole} from './AccRole';

export class AuthenticationResponse {
  private authenticationToken: string;
  private username: string;
  private accountRoleDTO: AccRole[];
}
