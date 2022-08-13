export class FKDirector {
  public id: {
    directorId: any,
    movieId: any
  };
  public movieDetail: any;
  public movieDirector: any;

  constructor(id: { directorId: any; movieId: any }, movieDetail: any, movieDirector: any) {
    this.id = id;
    this.movieDetail = movieDetail;
    this.movieDirector = movieDirector;
  }
}
