import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NgbdTooltipCustomclassComponent } from './ngbd-tooltip-customclass.component';

describe('NgbdTooltipCustomclassComponent', () => {
  let component: NgbdTooltipCustomclassComponent;
  let fixture: ComponentFixture<NgbdTooltipCustomclassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NgbdTooltipCustomclassComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NgbdTooltipCustomclassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
