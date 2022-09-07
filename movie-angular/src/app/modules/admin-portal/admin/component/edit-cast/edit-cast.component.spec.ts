import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCastComponent } from './edit-cast.component';

describe('EditCastComponent', () => {
  let component: EditCastComponent;
  let fixture: ComponentFixture<EditCastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCastComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
