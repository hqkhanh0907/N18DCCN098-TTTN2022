import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListMovieDataComponent } from './list-movie-data.component';

describe('ListMovieDataComponent', () => {
  let component: ListMovieDataComponent;
  let fixture: ComponentFixture<ListMovieDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListMovieDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListMovieDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
