import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreCastComponent } from './pre-cast.component';

describe('PreCastComponent', () => {
  let component: PreCastComponent;
  let fixture: ComponentFixture<PreCastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreCastComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreCastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
