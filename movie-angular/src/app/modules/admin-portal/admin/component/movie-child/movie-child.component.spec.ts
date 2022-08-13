import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieChildComponent } from './movie-child.component';

describe('MovieChildComponent', () => {
  let component: MovieChildComponent;
  let fixture: ComponentFixture<MovieChildComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieChildComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieChildComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
