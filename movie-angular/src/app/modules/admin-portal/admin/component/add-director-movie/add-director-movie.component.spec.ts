import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDirectorMovieComponent } from './add-director-movie.component';

describe('AddDirectorMovieComponent', () => {
  let component: AddDirectorMovieComponent;
  let fixture: ComponentFixture<AddDirectorMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddDirectorMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDirectorMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
