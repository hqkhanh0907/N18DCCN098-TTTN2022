import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CastChildComponent } from './cast-child.component';

describe('CastChildComponent', () => {
  let component: CastChildComponent;
  let fixture: ComponentFixture<CastChildComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CastChildComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CastChildComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
