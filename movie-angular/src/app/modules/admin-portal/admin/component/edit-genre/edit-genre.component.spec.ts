import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditGenreComponent } from './edit-genre.component';

describe('EditGenreComponent', () => {
  let component: EditGenreComponent;
  let fixture: ComponentFixture<EditGenreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditGenreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditGenreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
