import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentMovieComponent } from './content-movie.component';

describe('ContentMovieComponent', () => {
  let component: ContentMovieComponent;
  let fixture: ComponentFixture<ContentMovieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContentMovieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContentMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
