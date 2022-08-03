import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieDetailOnSlideComponent } from './movie-detail-on-slide.component';

describe('MovieDetailOnSlideComponent', () => {
  let component: MovieDetailOnSlideComponent;
  let fixture: ComponentFixture<MovieDetailOnSlideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieDetailOnSlideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieDetailOnSlideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
