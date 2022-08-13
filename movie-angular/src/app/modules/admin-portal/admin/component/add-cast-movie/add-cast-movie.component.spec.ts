import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCastMovieComponent } from './add-cast-movie.component';

describe('AddCastMovieComponent', () => {
  let component: AddCastMovieComponent;
  let fixture: ComponentFixture<AddCastMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCastMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCastMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
