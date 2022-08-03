import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieBoxSlideComponent } from './movie-box-slide.component';

describe('MovieBoxSlideComponent', () => {
  let component: MovieBoxSlideComponent;
  let fixture: ComponentFixture<MovieBoxSlideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieBoxSlideComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieBoxSlideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
