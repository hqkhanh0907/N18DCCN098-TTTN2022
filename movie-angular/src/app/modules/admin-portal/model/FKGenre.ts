export class FKGenre {
  public id: {
    genreId: any,
    movieId: any
  };
  public movieDetail: any;
  public movieGenre: any;

  constructor(id: { genreId: any; movieId: any }, movieDetail: any, movieGenre: any) {
    this.id = id;
    this.movieDetail = movieDetail;
    this.movieGenre = movieGenre;
  }
}
