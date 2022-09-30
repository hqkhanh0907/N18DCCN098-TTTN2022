import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTrailerMovieComponent } from './modal-trailer-movie.component';

describe('ModalTrailerMovieComponent', () => {
  let component: ModalTrailerMovieComponent;
  let fixture: ComponentFixture<ModalTrailerMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalTrailerMovieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalTrailerMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
