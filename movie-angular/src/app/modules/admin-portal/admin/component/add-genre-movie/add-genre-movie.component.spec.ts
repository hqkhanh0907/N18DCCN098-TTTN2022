import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddGenreMovieComponent } from './add-genre-movie.component';

describe('AddGenreMovieComponent', () => {
  let component: AddGenreMovieComponent;
  let fixture: ComponentFixture<AddGenreMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddGenreMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddGenreMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
