import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieSlideComponent } from './movie-slide.component';

describe('MovieSlideComponent', () => {
  let component: MovieSlideComponent;
  let fixture: ComponentFixture<MovieSlideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieSlideComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieSlideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
