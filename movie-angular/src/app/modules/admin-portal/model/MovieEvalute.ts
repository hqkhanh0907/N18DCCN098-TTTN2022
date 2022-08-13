export class MovieEvalute {
  public userId: number;
  public movieId: number;
  public evaluateTime: Date;
  public evaluateContent: string;
  public evaluateRate: number;


  constructor(userId: number, movieId: number, evaluateTime: Date, evaluateContent: string, evaluateRate: number) {
    this.userId = userId;
    this.movieId = movieId;
    this.evaluateTime = evaluateTime;
    this.evaluateContent = evaluateContent;
    this.evaluateRate = evaluateRate;
  }
}
