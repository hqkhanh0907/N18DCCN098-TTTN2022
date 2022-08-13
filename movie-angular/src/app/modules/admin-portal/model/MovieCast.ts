export class MovieCast {
  public id: number;
  public avatar: string;
  public name: string;
  public story: string;
  public birthday: Date;

  constructor(id: number, avatar: string, name: string, story: string, birthday: Date) {
    this.id = id;
    this.avatar = avatar;
    this.name = name;
    this.story = story;
    this.birthday = birthday;
  }
}
