import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvaluateMovieComponent } from './evaluate-movie.component';

describe('EvaluateMovieComponent', () => {
  let component: EvaluateMovieComponent;
  let fixture: ComponentFixture<EvaluateMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EvaluateMovieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EvaluateMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
