import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCastMovieComponent } from './list-cast-movie.component';

describe('ListCastMovieComponent', () => {
  let component: ListCastMovieComponent;
  let fixture: ComponentFixture<ListCastMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListCastMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCastMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
