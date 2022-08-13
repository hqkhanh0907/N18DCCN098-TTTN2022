export class LoginForm {
  username: string;
  password: string;

  getUsername(): string {
    return this.username;
  }

  setUsername(value: string) {
    this.username = value;
  }

  getPassword(): string {
    return this.password;
  }

  setPassword(value: string) {
    this.password = value;
  }

  constructor(username: string, password: string) {
    this.username = username;
    this.password = password;
  }
}
