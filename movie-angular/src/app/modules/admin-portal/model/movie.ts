import {FKGenre} from './FKGenre';
import {FKCast} from './FKCast';
import {FKDirector} from './FKDirector';
import {MovieEvalute} from './MovieEvalute';

export class Movie {
  id: number;
  title: string;
  poster: string;
  detail: string;
  movieStatus: boolean;
  linkTrailer: string;
  linkMovie: string;
  releaseDate: Date;
  movieDuration: string;
  viewNumber: number;
  fkGenres: FKGenre[];
  fkCasts: FKCast[];
  fkDirectors: FKDirector[];
  movieEvaluates: MovieEvalute[];

  constructor(
    id: number,
    title: string,
    poster: string,
    detail: string,
    movieStatus: boolean,
    linkTrailer: string,
    linkMovie: string,
    releaseDate: Date,
    movieDuration: string,
    viewNumber: number,
    fkGenre: FKGenre[],
    fkCast: FKCast[],
    fkDirector: FKDirector[],
    movieEvaluates: MovieEvalute[]) {
    this.id = id;
    this.title = title;
    this.poster = poster;
    this.detail = detail;
    this.movieStatus = movieStatus;
    this.linkTrailer = linkTrailer;
    this.linkMovie = linkMovie;
    this.releaseDate = releaseDate;
    this.movieDuration = movieDuration;
    this.viewNumber = viewNumber;
    this.fkGenres = fkGenre;
    this.fkCasts = fkCast;
    this.fkDirectors = fkDirector;
    this.movieEvaluates = movieEvaluates;
  }
}
