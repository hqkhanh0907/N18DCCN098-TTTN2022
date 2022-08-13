export class FKCast {
  public id: {
    castId: any,
    movieId: any
  };
  public castCharacter: string;
  public movieDetail: any;
  public movieCast: any;


  constructor(id: { castId: any; movieId: any }, castCharacter: string, movieDetail: any, movieCast: any) {
    this.id = id;
    this.castCharacter = castCharacter;
    this.movieDetail = movieDetail;
    this.movieCast = movieCast;
  }
}
