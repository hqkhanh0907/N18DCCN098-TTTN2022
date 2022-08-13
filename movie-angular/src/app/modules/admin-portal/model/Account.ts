export class Account {
  id: number;
  username: string;
  password: string;
  enabled: string;
  email: string;
  avatar: string;
  firstname: string;
  lastname: string;
  birthday: number;
  town: number;
  address: string;
  phoneNumber: string;
  gender: boolean;

  // tslint:disable-next-line:max-line-length
  constructor(id: number, username: string, password: string, enabled: string, email: string, avatar: string, firstname: string, lastname: string, birthday: number, town: number, address: string, phoneNumber: string, gender: boolean) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.email = email;
    this.avatar = avatar;
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday;
    this.town = town;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.gender = gender;
  }
}
