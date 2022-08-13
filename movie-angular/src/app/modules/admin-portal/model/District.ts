export class District {
  id: number;
  name: string;
  genre: string;
  cityId: number;

  constructor(id: number, name: string, genre: string, cityId: number) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.cityId = cityId;
  }
}
