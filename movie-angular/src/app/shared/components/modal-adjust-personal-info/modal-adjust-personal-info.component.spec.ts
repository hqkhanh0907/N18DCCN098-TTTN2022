import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAdjustPersonalInfoComponent } from './modal-adjust-personal-info.component';

describe('ModalAdjustPersonalInfoComponent', () => {
  let component: ModalAdjustPersonalInfoComponent;
  let fixture: ComponentFixture<ModalAdjustPersonalInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalAdjustPersonalInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalAdjustPersonalInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
